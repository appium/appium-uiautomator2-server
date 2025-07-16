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

package io.appium.uiautomator2.model.settings;

import java.util.ArrayList;

import io.appium.uiautomator2.utils.GlobMatcher;

/**
 * Allows to continue the tree traversal for user defined classes even though the node itself is invisible
 * The default logic for the UI tree traversal is to stop recursing when an invisible node is found.
 * However, with certain Jetpack Compose classes (e.g. androidx.compose.ui.viewinterop.ViewFactoryHolder),
 * it is possible that invisible parent nodes have visible children.
 *
 * You can provide a comma separated list of glob patterns that will be used as an exemption list:
 * e.g. "androidx.compose.ui.viewinterop.*,android.widget.ImageButton"
 *
 * @see <a href="https://issuetracker.google.com/issues/354958193">https://issuetracker.google.com/issues/354958193</a>
 * @see <a href="https://github.com/appium/appium-uiautomator2-server/issues/709">https://github.com/appium/appium-uiautomator2-server/issues/709</a>
 * @see GlobMatcher
 */
public class AlwaysTraversableViewClasses extends AbstractSetting<String> {
    private static final String SETTING_NAME = "alwaysTraversableViewClasses";
    private static final String DEFAULT_CLASSNAMES = "";
    private String value = DEFAULT_CLASSNAMES;

    private String[] patternArray = new String[] {};

    public AlwaysTraversableViewClasses() {
        super(String.class, SETTING_NAME);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String getDefaultValue() {
        return DEFAULT_CLASSNAMES;
    }

    public String[] asArray() {
        return patternArray;
    }

    @Override
    protected void apply(String traversableViewClasses) {
        value = traversableViewClasses;
        ArrayList<String> patterns = new ArrayList<String>();
        for (String name : value.split(",")) {
            patterns.add(GlobMatcher.globToRegex(name.trim()));
        }
        patternArray = patterns.toArray(new String[]{});
    }
}
