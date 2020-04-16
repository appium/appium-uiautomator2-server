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

import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.api.KeyCodeModel;
import io.appium.uiautomator2.model.api.touch.w3c.W3CActionsModel;
import io.appium.uiautomator2.model.api.touch.w3c.W3CItemModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import static io.appium.uiautomator2.utils.ModelUtils.toModel;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
public class SerializationTests {
    @Mock
    private IHttpRequest req;

    @Test
    public void shouldSerializeProperlyFormattedJson() {
        when(req.body()).thenReturn("{ \"actions\": [ {" +
                "\"type\": \"key\"," +
                "\"id\": \"keyboard\"," +
                "\"actions\": [" +
                "{\"type\": \"keyDown\", \"value\": \"A\"}," +
                "{\"type\": \"pause\", \"duration\": 500}," +
                "{\"type\": \"keyUp\", \"value\": \"A\"}]" +
                "} ]" +
                "}");
        W3CActionsModel model = toModel(req, W3CActionsModel.class);
        assertThat(model.actions.size(), equalTo(1));
        W3CItemModel actionItem = model.actions.get(0);
        assertThat(actionItem.id, equalTo("keyboard"));
        assertThat(actionItem.type, equalTo("key"));
        assertThat(actionItem.actions.size(), equalTo(3));
        assertThat(actionItem.actions.get(1).type, equalTo("pause"));
        assertThat(actionItem.actions.get(1).duration, equalTo(500L));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailIfListItemDoesNotHaveRequiredField() {
        when(req.body()).thenReturn("{ \"actions\": [ {" +
                "\"type\": \"key\"," +
                "\"id\": \"keyboard\"," +
                "\"actions\": [" +
                "{\"value\": \"A\"}," +
                "{\"type\": \"pause\", \"duration\": 500}," +
                "{\"type\": \"keyUp\", \"value\": \"A\"}]" +
                "} ]" +
                "}");
        toModel(req, W3CActionsModel.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailIfRequiredFieldIsMissing() {
        when(req.body()).thenReturn("{}");
        toModel(req, KeyCodeModel.class);
    }
}
