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

package io.appium.uiautomator2.utils.w3c;

import android.util.LongSparseArray;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class ActionTokens {
    private final LongSparseArray<List<InputEventParams>> tokenizedActions = new LongSparseArray<>();

    public void addEventAt(long timeDelta, @Nullable InputEventParams event) {
        List<InputEventParams> allParams = tokenizedActions.get(timeDelta);
        if (allParams == null) {
            final List<InputEventParams> param = new ArrayList<>();
            if (event != null) {
                param.add(event);
            }
            tokenizedActions.put(timeDelta, param);
        } else if (event != null) {
            allParams.add(event);
        }
    }

    public void putEventsAt(long timeDelta, List<InputEventParams> events) {
        tokenizedActions.put(timeDelta, events);
    }

    public int size() {
        return tokenizedActions.size();
    }

    public long maxTimeDelta() {
        return isEmpty() ? -1 : timeDeltaAt(size() - 1);
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    @Nullable
    public List<InputEventParams> eventsAt(long timeDelta) {
        return tokenizedActions.get(timeDelta);
    }

    public List<InputEventParams> eventsAtIndex(int index) {
        return tokenizedActions.valueAt(index);
    }

    public long timeDeltaAt(int index) {
        return tokenizedActions.keyAt(index);
    }
}
