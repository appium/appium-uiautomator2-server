/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.appium.uiautomator2.utils.actions_scheduler;

import static io.appium.uiautomator2.utils.StringHelpers.isBlank;

import android.os.Handler;
import android.os.Looper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.annotation.Nullable;

import io.appium.uiautomator2.common.exceptions.InvalidArgumentException;
import io.appium.uiautomator2.model.api.scheduled.ScheduledActionModel;
import io.appium.uiautomator2.model.api.scheduled.ScheduledActionStepModel;
import io.appium.uiautomator2.model.api.scheduled.ScheduledActionStepResultModel;
import io.appium.uiautomator2.model.api.scheduled.ScheduledActionStepsHistoryModel;
import io.appium.uiautomator2.utils.Logger;

public class ScheduledActionsManager {
    private static ScheduledActionsManager INSTANCE;

    public static synchronized ScheduledActionsManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ScheduledActionsManager();
        }
        return INSTANCE;
    }

    private final Map<String, ScheduledActionModel> scheduledActions = new HashMap<>();
    private final Map<String, ScheduledActionStepsHistoryModel> scheduledActionsHistory = new HashMap<>();
    private final Set<String> activeActionNames = new HashSet<>();

    private ScheduledActionsManager() {}

    public ScheduledActionsManager add(ScheduledActionModel actionToSchedule) {
        if (isBlank(actionToSchedule.name)) {
            throw new InvalidArgumentException("Action name must not be blank");
        }
        if (actionToSchedule.interval < 0) {
            throw new InvalidArgumentException(String.format(
                    "The scheduled action interval must not be negative. You have provided %s",
                    actionToSchedule.interval
            ));
        }
        if (actionToSchedule.steps.isEmpty()) {
            throw new InvalidArgumentException(
                    "The amount of provided action steps must be greater than zero"
            );
        }
        if (actionToSchedule.maxHistory < 1) {
            throw new InvalidArgumentException(
                    "The amount of maximum action history items must be greater than zero"
            );
        }
        if (scheduledActions.containsKey(actionToSchedule.name)) {
            throw new InvalidArgumentException(String.format(
                    "The action with the same name '%s' has been already scheduled. Please remove it first",
                    actionToSchedule.name
            ));
        }
        scheduledActions.put(actionToSchedule.name, actionToSchedule);
        scheduledActionsHistory.put(actionToSchedule.name, new ScheduledActionStepsHistoryModel());
        return scheduleAction(actionToSchedule);
    }

    @Nullable
    public ScheduledActionStepsHistoryModel getHistory(String name) {
        return scheduledActionsHistory.get(name);
    }

    public ScheduledActionsManager remove(String name) {
        activeActionNames.remove(name);
        scheduledActions.remove(name);
        scheduledActionsHistory.remove(name);
        return this;
    }

    public ScheduledActionsManager clear() {
        activeActionNames.clear();
        scheduledActions.clear();
        scheduledActionsHistory.clear();
        return this;
    }

    private ScheduledActionStepResultModel runActionStep(ScheduledActionStepModel step) {
        if (GestureStep.TYPE.equals(step.type)) {
            return new GestureStep(step).run();
        }

        throw new InvalidArgumentException(String.format(
                "The step type '%s' is not known. Only the following step types are supported: %s",
                step.type, Arrays.toString(new String[]{GestureStep.TYPE})
        ));
    }

    private void runActionSteps(ScheduledActionModel info) {
        if (!activeActionNames.contains(info.name)) {
            return;
        }

        ScheduledActionStepsHistoryModel history = new ScheduledActionStepsHistoryModel();
        if (scheduledActionsHistory.containsKey(info.name)) {
            history = Objects.requireNonNull(scheduledActionsHistory.get(info.name));
        } else {
            scheduledActionsHistory.put(info.name, history);
        }
        Logger.info(String.format(
                "About to run steps of the scheduled action '%s' (execution %s of %s)",
                info.name, history.repeats + 1, info.times
        ));
        if (history.stepResults.size() >= info.maxHistory) {
            // Remove the oldest step, so we still have the space for the current one
            history.stepResults.remove(history.stepResults.size() - 1);
        }
        List<ScheduledActionStepResultModel> stepResults = new ArrayList<>();
        int stepIndex = 1;
        for (ScheduledActionStepModel step: info.steps) {
            Logger.info(String.format(
                    "About to run step '%s (%s)' (%s of %s) belonging to the scheduled action '%s'",
                    step.name, step.type, stepIndex, info.steps.size(), info.name
            ));
            stepResults.add(runActionStep(step));
            stepIndex++;
        }
        // Newest steps go first
        if (history.stepResults.isEmpty()) {
            history.stepResults.add(stepResults);
        } else {
            history.stepResults.add(0, stepResults);
        }
        history.repeats++;
        if (info.times < history.repeats) {
            Logger.info(String.format(
                    "Will run the repeatable scheduled action '%s' again in %s milliseconds " +
                    "(completed %s of %s repeats)", info.name, info.interval, history.repeats, info.times
            ));
            new Handler(Looper.getMainLooper()).postDelayed(() -> runActionSteps(info), info.interval);
        } else {
            Logger.info(String.format(
                    "The scheduled action '%s' has been executed %s times in total", info.name, info.times
            ));
            activeActionNames.remove(info.name);
        }
    }

    private ScheduledActionsManager scheduleAction(ScheduledActionModel info) {
        activeActionNames.add(info.name);
        // No concurrency here. Everything is still being executed on the main thread
        new Handler(Looper.getMainLooper()).post(() -> runActionSteps(info));
        return this;
    }
}
