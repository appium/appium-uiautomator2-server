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

package io.appium.uiautomator2.server;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import io.appium.uiautomator2.handler.AcceptAlert;
import io.appium.uiautomator2.handler.AppStrings;
import io.appium.uiautomator2.handler.CaptureScreenshot;
import io.appium.uiautomator2.handler.Clear;
import io.appium.uiautomator2.handler.Click;
import io.appium.uiautomator2.handler.DeleteSession;
import io.appium.uiautomator2.handler.DismissAlert;
import io.appium.uiautomator2.handler.Drag;
import io.appium.uiautomator2.handler.FindElement;
import io.appium.uiautomator2.handler.FindElements;
import io.appium.uiautomator2.handler.FirstVisibleView;
import io.appium.uiautomator2.handler.Flick;
import io.appium.uiautomator2.handler.GetAlertText;
import io.appium.uiautomator2.handler.GetBatteryInfo;
import io.appium.uiautomator2.handler.GetClipboard;
import io.appium.uiautomator2.handler.GetDevicePixelRatio;
import io.appium.uiautomator2.handler.GetDeviceSize;
import io.appium.uiautomator2.handler.GetElementAttribute;
import io.appium.uiautomator2.handler.GetElementScreenshot;
import io.appium.uiautomator2.handler.GetName;
import io.appium.uiautomator2.handler.GetRect;
import io.appium.uiautomator2.handler.GetRotation;
import io.appium.uiautomator2.handler.GetScreenOrientation;
import io.appium.uiautomator2.handler.GetSessionDetails;
import io.appium.uiautomator2.handler.GetSettings;
import io.appium.uiautomator2.handler.GetSize;
import io.appium.uiautomator2.handler.GetSystemBars;
import io.appium.uiautomator2.handler.GetText;
import io.appium.uiautomator2.handler.Location;
import io.appium.uiautomator2.handler.LongPressKeyCode;
import io.appium.uiautomator2.handler.MultiPointerGesture;
import io.appium.uiautomator2.handler.NetworkConnection;
import io.appium.uiautomator2.handler.NewSession;
import io.appium.uiautomator2.handler.OpenNotification;
import io.appium.uiautomator2.handler.PressBack;
import io.appium.uiautomator2.handler.PressKeyCode;
import io.appium.uiautomator2.handler.RotateScreen;
import io.appium.uiautomator2.handler.ScrollTo;
import io.appium.uiautomator2.handler.ScrollToElement;
import io.appium.uiautomator2.handler.SendKeysToElement;
import io.appium.uiautomator2.handler.SetClipboard;
import io.appium.uiautomator2.handler.Source;
import io.appium.uiautomator2.handler.Status;
import io.appium.uiautomator2.handler.Swipe;
import io.appium.uiautomator2.handler.TouchDown;
import io.appium.uiautomator2.handler.TouchLongClick;
import io.appium.uiautomator2.handler.TouchMove;
import io.appium.uiautomator2.handler.TouchUp;
import io.appium.uiautomator2.handler.UpdateSettings;
import io.appium.uiautomator2.handler.W3CActions;
import io.appium.uiautomator2.handler.request.BaseRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.http.IHttpResponse;
import io.appium.uiautomator2.http.IHttpServlet;

public class AppiumServlet implements IHttpServlet {

    public static final String SESSION_ID_KEY = "SESSION_ID_KEY";

    public static final String ELEMENT_ID_KEY = "id";
    public static final String ELEMENT_ID_NEXT_KEY = "elementId";
    public static final String COMMAND_NAME_KEY = "COMMAND_KEY";
    public static final String NAME_ID_KEY = "NAME_ID_KEY";
    protected static ConcurrentMap<String, BaseRequestHandler> getHandler = new ConcurrentHashMap<>();
    protected static ConcurrentMap<String, BaseRequestHandler> postHandler = new ConcurrentHashMap<>();
    protected static ConcurrentMap<String, BaseRequestHandler> deleteHandler = new ConcurrentHashMap<>();
    private ConcurrentMap<String, String[]> mapperUrlSectionsCache = new ConcurrentHashMap<>();


    public AppiumServlet() {
        init();
    }

    private void init() {
        registerGetHandler();
        registerPostHandler();
        registerDeleteHandler();
    }

    private void registerDeleteHandler() {
        register(deleteHandler, new DeleteSession("/wd/hub/session/:sessionId"));
    }

    private void registerPostHandler() {
        register(postHandler, new NewSession("/wd/hub/session"));
        register(postHandler, new FindElement("/wd/hub/session/:sessionId/element"));
        register(postHandler, new FindElements("/wd/hub/session/:sessionId/elements"));
        register(postHandler, new Click("/wd/hub/session/:sessionId/element/:id/click"));
        register(postHandler, new Click("/wd/hub/session/:sessionId/appium/tap"));
        register(postHandler, new Clear("/wd/hub/session/:sessionId/element/:id/clear"));
        register(postHandler, new RotateScreen("/wd/hub/session/:sessionId/orientation"));
        register(postHandler, new RotateScreen("/wd/hub/session/:sessionId/rotation"));
        register(postHandler, new PressBack("/wd/hub/session/:sessionId/back"));
        register(postHandler, new SendKeysToElement("/wd/hub/session/:sessionId/element/:id/value"));
        register(postHandler, new SendKeysToElement("/wd/hub/session/:sessionId/keys"));
        register(postHandler, new Swipe("/wd/hub/session/:sessionId/touch/perform"));
        register(postHandler, new TouchLongClick("/wd/hub/session/:sessionId/touch/longclick"));
        register(postHandler, new OpenNotification("/wd/hub/session/:sessionId/appium/device/open_notifications"));
        register(postHandler, new PressKeyCode("/wd/hub/session/:sessionId/appium/device/press_keycode"));
        register(postHandler, new LongPressKeyCode("/wd/hub/session/:sessionId/appium/device/long_press_keycode"));
        register(postHandler, new Drag("/wd/hub/session/:sessionId/touch/drag"));
        register(postHandler, new AppStrings("/wd/hub/session/:sessionId/appium/app/strings"));
        register(postHandler, new Flick("/wd/hub/session/:sessionId/touch/flick"));
        register(postHandler, new ScrollTo("/wd/hub/session/:sessionId/touch/scroll"));
        register(postHandler, new MultiPointerGesture("/wd/hub/session/:sessionId/touch/multi/perform"));
        register(postHandler, new W3CActions("/wd/hub/session/:sessionId/actions"));
        register(postHandler, new TouchDown("/wd/hub/session/:sessionId/touch/down"));
        register(postHandler, new TouchUp("/wd/hub/session/:sessionId/touch/up"));
        register(postHandler, new TouchMove("/wd/hub/session/:sessionId/touch/move"));
        register(postHandler, new UpdateSettings("/wd/hub/session/:sessionId/appium/settings"));
        register(postHandler, new NetworkConnection("/wd/hub/session/:sessionId/network_connection"));
        register(postHandler, new ScrollToElement("/wd/hub/session/:sessionId/appium/element/:id/scroll_to/:elementId"));
        register(postHandler, new GetClipboard("/wd/hub/session/:sessionId/appium/device/get_clipboard"));
        register(postHandler, new SetClipboard("/wd/hub/session/:sessionId/appium/device/set_clipboard"));
        register(postHandler, new AcceptAlert("/wd/hub/session/:sessionId/alert/accept"));
        register(postHandler, new DismissAlert("/wd/hub/session/:sessionId/alert/dismiss"));
    }

    private void registerGetHandler() {
        register(getHandler, new Status("/wd/hub/status"));
        register(getHandler, new GetSessionDetails("/wd/hub/session/:sessionId"));
        register(getHandler, new CaptureScreenshot("/wd/hub/session/:sessionId/screenshot"));
        register(getHandler, new GetScreenOrientation("/wd/hub/session/:sessionId/orientation"));
        register(getHandler, new GetRotation("/wd/hub/session/:sessionId/rotation"));
        register(getHandler, new GetText("/wd/hub/session/:sessionId/element/:id/text"));
        register(getHandler, new GetElementAttribute("/wd/hub/session/:sessionId/element/:id/attribute/:name"));
        register(getHandler, new GetRect("/wd/hub/session/:sessionId/element/:id/rect"));
        register(getHandler, new GetSize("/wd/hub/session/:sessionId/element/:id/size"));
        register(getHandler, new GetName("/wd/hub/session/:sessionId/element/:id/name"));
        register(getHandler, new GetElementScreenshot("/wd/hub/session/:sessionId/element/:id/screenshot"));
        register(getHandler, new Location("/wd/hub/session/:sessionId/element/:id/location"));
        register(getHandler, new GetDeviceSize("/wd/hub/session/:sessionId/window/:windowHandle/size"));
        register(getHandler, new Source("/wd/hub/session/:sessionId/source"));
        register(getHandler, new GetSystemBars("/wd/hub/session/:sessionId/appium/device/system_bars"));
        register(getHandler, new GetBatteryInfo("/wd/hub/session/:sessionId/appium/device/battery_info"));
        register(getHandler, new GetSettings("/wd/hub/session/:sessionId/appium/settings"));
        register(getHandler, new GetDevicePixelRatio("/wd/hub/session/:sessionId/appium/device/pixel_ratio"));
        register(getHandler, new FirstVisibleView("/wd/hub/session/:sessionId/appium/element/:id/first_visible"));
        register(getHandler, new GetAlertText("/wd/hub/session/:sessionId/alert/text"));
    }

    protected void register(Map<String, BaseRequestHandler> registerOn, BaseRequestHandler handler) {
        registerOn.put(handler.getMappedUri(), handler);
    }

    protected BaseRequestHandler findMatcher(IHttpRequest request, Map<String, BaseRequestHandler> handler) {
        String[] urlToMatchSections = getRequestUrlSections(request.uri());
        for (Map.Entry<String, ? extends BaseRequestHandler> entry : handler.entrySet()) {
            String[] mapperUrlSections = getMapperUrlSectionsCached(entry.getKey());
            if (isFor(mapperUrlSections, urlToMatchSections)) {
                return entry.getValue();
            }
        }
        return null;
    }

    private String[] getRequestUrlSections(String urlToMatch) {
        if (urlToMatch == null) {
            return null;
        }
        int qPos = urlToMatch.indexOf('?');
        if (qPos != -1) {
            urlToMatch = urlToMatch.substring(0, urlToMatch.indexOf("?"));
        }
        return urlToMatch.split("/");
    }

    private String[] getMapperUrlSectionsCached(String mapperUrl) {
        String[] sections = mapperUrlSectionsCache.get(mapperUrl);
        if (sections == null) {
            sections = mapperUrl.split("/");
            for (int i = 0; i < sections.length; i++) {
                String section = sections[i];
                // To work around a but in Selenium Grid 2.31.0.
                int qPos = section.indexOf('?');
                if (qPos != -1) {
                    sections[i] = section.substring(0, qPos);
                }
            }
            mapperUrlSectionsCache.put(mapperUrl, sections);
        }
        return sections;
    }

    protected boolean isFor(String[] mapperUrlSections, String[] urlToMatchSections) {
        if (urlToMatchSections == null) {
            return mapperUrlSections.length == 0;
        }
        if (mapperUrlSections.length != urlToMatchSections.length) {
            return false;
        }
        for (int i = 0; i < mapperUrlSections.length; i++) {
            if (!(mapperUrlSections[i].startsWith(":") || mapperUrlSections[i].equals(urlToMatchSections[i]))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void handleHttpRequest(IHttpRequest request, IHttpResponse response) {
        BaseRequestHandler handler = null;
        if ("GET".equals(request.method())) {
            handler = findMatcher(request, getHandler);
        } else if ("POST".equals(request.method())) {
            handler = findMatcher(request, postHandler);
        } else if ("DELETE".equals(request.method())) {
            handler = findMatcher(request, deleteHandler);
        }
        handleRequest(request, response, handler);
    }

    public void handleRequest(IHttpRequest request, IHttpResponse response, BaseRequestHandler handler) {
        if (handler == null) {
            response.setStatus(HttpStatusCode.NOT_FOUND.getStatusCode()).end();
            return;
        }
        addHandlerAttributesToRequest(request, handler.getMappedUri());
        AppiumResponse result = handler.handle(request);
        handleResponse(request, response, result);
    }

    protected void handleResponse(IHttpRequest request, IHttpResponse response, AppiumResponse result) {
        if (result != null) {
            String resultString = result.render();
            response.setContentType("application/json");
            response.setEncoding(Charset.forName("UTF-8"));
            response.setContent(resultString);
            try {
                if (new JSONObject(resultString).getInt("status") == 0) {
                    response.setStatus(HttpStatusCode.OK.getStatusCode());
                } else {
                    response.setStatus(HttpStatusCode.INTERNAL_SERVER_ERROR.getStatusCode());
                }
            } catch (JSONException e) {
                response.setStatus(HttpStatusCode.INTERNAL_SERVER_ERROR.getStatusCode());
            }
        }
        response.end();
    }

    private void addHandlerAttributesToRequest(IHttpRequest request, String mappedUri) {
        String sessionId = getParameter(mappedUri, request.uri(), ":sessionId");
        if (sessionId != null) {
            request.data().put(SESSION_ID_KEY, sessionId);
        }

        String command = getParameter(mappedUri, request.uri(), ":command");
        if (command != null) {
            request.data().put(COMMAND_NAME_KEY, command);
        }

        String id = getParameter(mappedUri, request.uri(), ":id");
        if (id != null) {
            request.data().put(ELEMENT_ID_KEY, URLDecoder.decode(id));
        }
        String name = getParameter(mappedUri, request.uri(), ":name");
        if (name != null) {
            request.data().put(NAME_ID_KEY, name);
        }
        String elementId = getParameter(mappedUri, request.uri(), ":elementId");
        if (elementId != null) {
            request.data().put(ELEMENT_ID_NEXT_KEY, elementId);
        }

        //request.data().put(DRIVER_KEY, driver);
    }


    protected String getParameter(String configuredUri, String actualUri, String param) {
        return getParameter(configuredUri, actualUri, param, true);
    }

    protected String getParameter(String configuredUri, String actualUri, String param, boolean sectionLengthValidation) {
        String[] configuredSections = configuredUri.split("/");
        String[] currentSections = actualUri.split("/");
        if (sectionLengthValidation && configuredSections.length != currentSections.length) {
            return null;
        }
        for (int i = 0; i < currentSections.length; i++) {
            if (configuredSections[i].contains(param)) {
                return currentSections[i];
            }
        }
        return null;
    }
}
