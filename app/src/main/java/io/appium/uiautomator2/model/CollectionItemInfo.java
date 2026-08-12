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

package io.appium.uiautomator2.model;

import android.os.Build;
import android.view.accessibility.AccessibilityNodeInfo;

import androidx.annotation.Nullable;

public class CollectionItemInfo {
    public final int rowIndex;
    public final int columnIndex;
    public final int rowSpan;
    public final int columnSpan;
    public final boolean isHeading;
    public final boolean isSelected;
    @Nullable
    public final String rowTitle;
    @Nullable
    public final String columnTitle;

    private CollectionItemInfo(AccessibilityNodeInfo.CollectionItemInfo info) {
        this.rowIndex = info.getRowIndex();
        this.columnIndex = info.getColumnIndex();
        this.rowSpan = info.getRowSpan();
        this.columnSpan = info.getColumnSpan();
        this.isHeading = info.isHeading();
        this.isSelected = info.isSelected();
        this.rowTitle = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU ? info.getRowTitle() : null;
        this.columnTitle = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU ? info.getColumnTitle() : null;
    }

    @Nullable
    public static CollectionItemInfo from(@Nullable AccessibilityNodeInfo nodeInfo) {
        AccessibilityNodeInfo.CollectionItemInfo info = nodeInfo == null ? null : nodeInfo.getCollectionItemInfo();
        return info == null ? null : new CollectionItemInfo(info);
    }
}
