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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Map;
import java.util.UUID;

import io.appium.uiautomator2.common.exceptions.NoSuchDriverException;

public class AppiumUIA2Driver {
    private static AppiumUIA2Driver instance;
    private Session session;

    private AppiumUIA2Driver() {
    }

    public static synchronized AppiumUIA2Driver getInstance() {
        if (instance == null) {
            instance = new AppiumUIA2Driver();
        }
        return instance;
    }

    public String initializeSession(Map<String, Object> capabilities) {
        this.session = new Session(UUID.randomUUID().toString(), capabilities);
        return this.session.getSessionId();
    }

    @Nullable
    public Session getSession() {
        return this.session;
    }

    @NonNull
    public Session getSessionOrThrow() {
        Session session = getSession();
        if (session == null) {
            throw new NoSuchDriverException("A session is either terminated or not started");
        }
        return session;
    }
}

