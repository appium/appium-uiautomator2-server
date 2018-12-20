/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.appium.uiautomator2.utils;

import android.view.accessibility.AccessibilityNodeInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.annotation.Nullable;

public class NodeInfoList {

    private final List<AccessibilityNodeInfo> nodeList = new ArrayList<>();

    public void add(AccessibilityNodeInfo node) {
        nodeList.add(node);
    }

    public List<AccessibilityNodeInfo> getAll() {
        return Collections.unmodifiableList(nodeList);
    }

    @Nullable
    public AccessibilityNodeInfo getFirst() {
        return isEmpty() ? null : nodeList.get(0);
    }

    public boolean isEmpty() {
        return nodeList.isEmpty();
    }

    public int size() {
        return nodeList.size();
    }
}
