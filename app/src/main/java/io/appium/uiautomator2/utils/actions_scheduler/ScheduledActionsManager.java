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

import static io.appium.uiautomator2.utils.ModelUtils.toJsonString;
import static io.appium.uiautomator2.utils.StringHelpers.abbreviate;
import static io.appium.uiautomator2.utils.StringHelpers.isBlank;

import android.os.Handler;
import android.os.Looper;

import java.util.ArrayList;
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
    private final Map<String, PassFailCounter> actionResultCounts = new HashMap<>();

    private static class PassFailCounter {
        public int passCount = 0;
        public int failCount = 0;
    }

    private ScheduledActionsManager() {}

    public ScheduledActionsManager add(ScheduledActionModel actionToSchedule) {
        if (isBlank(actionToSchedule.name)) {
            throw new InvalidArgumentException("Action name must not be blank");
        }
        if (actionToSchedule.intervalMs < 0) {
            throw new InvalidArgumentException(String.format(
                    "The scheduled action interval must not be negative. You have provided %s",
                    actionToSchedule.intervalMs
            ));
        }
        if (actionToSchedule.steps.isEmpty()) {
            throw new InvalidArgumentException(
                    "The amount of provided action steps must be greater than zero"
            );
        }
        if (actionToSchedule.maxHistoryItems < 1) {
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
        actionResultCounts.remove(name);
        return this;
    }

    public ScheduledActionsManager clear() {
        activeActionNames.clear();
        scheduledActions.clear();
        scheduledActionsHistory.clear();
        actionResultCounts.clear();
        return this;
    }

    private ScheduledActionStepResultModel runActionStep(
            ScheduledActionStepModel step
    ) throws UnknownStepTypeException {
        final BaseActionStep actionStep;
        if (GestureStep.TYPE.equals(step.type)) {
            actionStep = new GestureStep(step);
        } else if (SourceStep.TYPE.equals(step.type)) {
            actionStep = new SourceStep(step);
        } else if (ScreenshotStep.TYPE.equals(step.type)) {
            actionStep = new ScreenshotStep(step);
        } else {
            throw new UnknownStepTypeException(step, new String[]{GestureStep.TYPE, SourceStep.TYPE});
        }
        return actionStep.run();
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
        if (history.stepResults.size() >= info.maxHistoryItems) {
            // Remove the oldest step, so we still have the space for the current one
            history.stepResults.remove(history.stepResults.size() - 1);
        }
        List<ScheduledActionStepResultModel> stepResults = new ArrayList<>();
        int stepIndex = 1;
        boolean didFail = false;
        for (ScheduledActionStepModel step: info.steps) {
            Logger.info(String.format(
                    "About to run the step '%s (%s)' (%s of %s) belonging to the scheduled action '%s'",
                    step.name, step.type, stepIndex, info.steps.size(), info.name
            ));
            ScheduledActionStepResultModel stepResult;
            try {
                stepResult = runActionStep(step);
            } catch (UnknownStepTypeException e) {
                stepResult = new ScheduledActionStepResultModel(
                        step.name, step.type, System.currentTimeMillis(), e
                );
            }
            stepResults.add(stepResult);
            Logger.info(String.format(
                    "Finished running the step '%s' (type '%s') (%s of %s) belonging to the " +
                            "scheduled action '%s'. Step result: %s",
                    step.name, step.type, stepIndex, info.steps.size(), info.name,
                    abbreviate(toJsonString(stepResult), 200)
            ));
            if (!didFail && !stepResult.passed) {
                didFail = true;
            }
            stepIndex++;
        }
        // Newest steps go first
        if (history.stepResults.isEmpty()) {
            history.stepResults.add(stepResults);
        } else {
            history.stepResults.add(0, stepResults);
        }
        history.repeats++;
        PassFailCounter resultCounts;
        if (actionResultCounts.containsKey(info.name)) {
            resultCounts = Objects.requireNonNull(actionResultCounts.get(info.name));
        } else {
            resultCounts = new PassFailCounter();
            actionResultCounts.put(info.name, resultCounts);
        }
        if (didFail) {
            resultCounts.failCount++;
        } else {
            resultCounts.passCount++;
        }
        boolean shouldUnschedule = false;
        if (info.maxPass > 0 && resultCounts.passCount >= info.maxPass) {
            Logger.info(String.format(
                    "The scheduled action '%s' has passed %s time(s) and will be unscheduled as requested",
                    info.name, resultCounts.passCount
            ));
            shouldUnschedule = true;
        } else if (info.maxFail > 0 && resultCounts.failCount >= info.maxFail) {
            Logger.info(String.format(
                    "The scheduled action '%s' has failed %s time(s) and will be unscheduled as requested",
                    info.name, resultCounts.failCount
            ));
            shouldUnschedule = true;
        } else if (info.times >= history.repeats) {
            Logger.info(String.format(
                    "The scheduled action '%s' has been executed %s times in total", info.name, info.times
            ));
            shouldUnschedule = true;
        }
        if (shouldUnschedule) {
            activeActionNames.remove(info.name);
            return;
        }

        Logger.info(String.format(
                "Will run the repeatable scheduled action '%s' again in %s milliseconds " +
                "(completed %s of %s repeats)", info.name, info.intervalMs, history.repeats, info.times
        ));
        new Handler(Looper.getMainLooper()).postDelayed(() -> runActionSteps(info), info.intervalMs);
    }

    private ScheduledActionsManager scheduleAction(ScheduledActionModel info) {
        activeActionNames.add(info.name);
        // No concurrency here. Everything is still being executed on the main thread
        new Handler(Looper.getMainLooper()).post(() -> runActionSteps(info));
        return this;
    }
}
