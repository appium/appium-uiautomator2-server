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

import androidx.annotation.Nullable;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.IntStream;

import io.appium.uiautomator2.handler.AcceptAlert;
import io.appium.uiautomator2.handler.CaptureScreenshot;
import io.appium.uiautomator2.handler.Clear;
import io.appium.uiautomator2.handler.Click;
import io.appium.uiautomator2.handler.DeleteSession;
import io.appium.uiautomator2.handler.DismissAlert;
import io.appium.uiautomator2.handler.ActiveElement;
import io.appium.uiautomator2.handler.FindElement;
import io.appium.uiautomator2.handler.FindElements;
import io.appium.uiautomator2.handler.FirstVisibleView;
import io.appium.uiautomator2.handler.GetActionHistory;
import io.appium.uiautomator2.handler.GetAlertText;
import io.appium.uiautomator2.handler.GetBatteryInfo;
import io.appium.uiautomator2.handler.GetClipboard;
import io.appium.uiautomator2.handler.GetDeviceInfo;
import io.appium.uiautomator2.handler.GetDevicePixelRatio;
import io.appium.uiautomator2.handler.GetDeviceSize;
import io.appium.uiautomator2.handler.GetDisplayDensity;
import io.appium.uiautomator2.handler.GetElementAttribute;
import io.appium.uiautomator2.handler.GetElementScreenshot;
import io.appium.uiautomator2.handler.GetName;
import io.appium.uiautomator2.handler.GetOrientation;
import io.appium.uiautomator2.handler.GetPackages;
import io.appium.uiautomator2.handler.GetRect;
import io.appium.uiautomator2.handler.GetRotation;
import io.appium.uiautomator2.handler.GetSessionDetails;
import io.appium.uiautomator2.handler.GetSessions;
import io.appium.uiautomator2.handler.GetSettings;
import io.appium.uiautomator2.handler.GetSize;
import io.appium.uiautomator2.handler.GetSystemBars;
import io.appium.uiautomator2.handler.GetText;
import io.appium.uiautomator2.handler.Location;
import io.appium.uiautomator2.handler.LongPressKeyCode;
import io.appium.uiautomator2.handler.NetworkConnection;
import io.appium.uiautomator2.handler.NewSession;
import io.appium.uiautomator2.handler.OpenNotification;
import io.appium.uiautomator2.handler.PressBack;
import io.appium.uiautomator2.handler.PressKeyCode;
import io.appium.uiautomator2.handler.ScheduleAction;
import io.appium.uiautomator2.handler.W3CActions;
import io.appium.uiautomator2.handler.gestures.ScrollTo;
import io.appium.uiautomator2.handler.gestures.ScrollToElement;
import io.appium.uiautomator2.handler.SendKeysToElement;
import io.appium.uiautomator2.handler.SetClipboard;
import io.appium.uiautomator2.handler.SetOrientation;
import io.appium.uiautomator2.handler.SetRotation;
import io.appium.uiautomator2.handler.Source;
import io.appium.uiautomator2.handler.Status;
import io.appium.uiautomator2.handler.UnscheduleAction;
import io.appium.uiautomator2.handler.UpdateSettings;
import io.appium.uiautomator2.handler.request.BaseRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.http.IHttpResponse;
import io.appium.uiautomator2.http.IHttpServlet;

public class AppiumServlet implements IHttpServlet {

    public static final String SESSION_ID_KEY = "SESSION_ID_KEY";

    public static final String ELEMENT_ID_KEY = "id";
    private static final String COMMAND_NAME_KEY = "COMMAND_KEY";
    public static final String NAME_ID_KEY = "NAME_ID_KEY";
    public static final int MAX_ELEMENTS = 3;
    public static final int SECOND_ELEMENT_IDX = 2;
    private static final ConcurrentMap<String, BaseRequestHandler> getHandler = new ConcurrentHashMap<>();
    private static final ConcurrentMap<String, BaseRequestHandler> postHandler = new ConcurrentHashMap<>();
    private static final ConcurrentMap<String, BaseRequestHandler> deleteHandler = new ConcurrentHashMap<>();
    private final ConcurrentMap<String, String[]> mapperUrlSectionsCache = new ConcurrentHashMap<>();


    public AppiumServlet() {
        init();
    }

    private void init() {
        registerGetHandler();
        registerPostHandler();
        registerDeleteHandler();
    }

    private void registerDeleteHandler() {
        register(deleteHandler, new DeleteSession("/session/:sessionId"));
    }

    private void registerPostHandler() {
        register(postHandler, new NewSession("/session"));
        register(postHandler, new FindElement("/session/:sessionId/element"));
        register(postHandler, new FindElements("/session/:sessionId/elements"));
        register(postHandler, new Click("/session/:sessionId/element/:id/click"));
        register(postHandler, new Clear("/session/:sessionId/element/:id/clear"));
        register(postHandler, new SetOrientation("/session/:sessionId/orientation"));
        register(postHandler, new SetRotation("/session/:sessionId/rotation"));
        register(postHandler, new PressBack("/session/:sessionId/back"));
        register(postHandler, new SendKeysToElement("/session/:sessionId/element/:id/value"));
        register(postHandler, new SendKeysToElement("/session/:sessionId/keys"));
        register(postHandler, new OpenNotification("/session/:sessionId/appium/device/open_notifications"));
        register(postHandler, new PressKeyCode("/session/:sessionId/appium/device/press_keycode"));
        register(postHandler, new LongPressKeyCode("/session/:sessionId/appium/device/long_press_keycode"));
        register(postHandler, new UpdateSettings("/session/:sessionId/appium/settings"));
        register(postHandler, new NetworkConnection("/session/:sessionId/network_connection"));
        register(postHandler, new GetClipboard("/session/:sessionId/appium/device/get_clipboard"));
        register(postHandler, new SetClipboard("/session/:sessionId/appium/device/set_clipboard"));
        register(postHandler, new AcceptAlert("/session/:sessionId/alert/accept"));
        register(postHandler, new DismissAlert("/session/:sessionId/alert/dismiss"));
        register(postHandler, new W3CActions("/session/:sessionId/actions"));

        register(postHandler, new io.appium.uiautomator2.handler.gestures.Drag("/session/:sessionId/appium/gestures/drag"));
        register(postHandler, new io.appium.uiautomator2.handler.gestures.Fling("/session/:sessionId/appium/gestures/fling"));
        register(postHandler, new io.appium.uiautomator2.handler.gestures.Click("/session/:sessionId/appium/gestures/click"));
        register(postHandler, new io.appium.uiautomator2.handler.gestures.LongClick("/session/:sessionId/appium/gestures/long_click"));
        register(postHandler, new io.appium.uiautomator2.handler.gestures.DoubleClick("/session/:sessionId/appium/gestures/double_click"));
        register(postHandler, new io.appium.uiautomator2.handler.gestures.PinchClose("/session/:sessionId/appium/gestures/pinch_close"));
        register(postHandler, new io.appium.uiautomator2.handler.gestures.PinchOpen("/session/:sessionId/appium/gestures/pinch_open"));
        register(postHandler, new io.appium.uiautomator2.handler.gestures.Scroll("/session/:sessionId/appium/gestures/scroll"));
        register(postHandler, new ScrollTo("/session/:sessionId/gestures/scroll_to"));
        register(postHandler, new ScrollToElement("/session/:sessionId/appium/element/:id/scroll_to/:id2"));
        register(postHandler, new io.appium.uiautomator2.handler.gestures.Swipe("/session/:sessionId/appium/gestures/swipe"));

        register(postHandler, new ScheduleAction("/session/:sessionId/appium/schedule_action"));
        register(postHandler, new GetActionHistory("/session/:sessionId/appium/action_history"));
        register(postHandler, new UnscheduleAction("/session/:sessionId/appium/unschedule_action"));
    }

    private void registerGetHandler() {
        register(getHandler, new Status("/status"));
        register(getHandler, new GetPackages("/appium/device/apps"));
        register(getHandler, new GetSessions("/sessions"));
        register(getHandler, new GetPackages("/session/:sessionId/appium/device/apps"));
        register(getHandler, new GetSessionDetails("/session/:sessionId"));
        register(getHandler, new CaptureScreenshot("/session/:sessionId/screenshot"));
        register(getHandler, new GetOrientation("/session/:sessionId/orientation"));
        register(getHandler, new GetRotation("/session/:sessionId/rotation"));
        register(getHandler, new GetText("/session/:sessionId/element/:id/text"));
        register(getHandler, new GetElementAttribute("/session/:sessionId/element/:id/attribute/:name"));
        register(getHandler, new GetRect("/session/:sessionId/element/:id/rect"));
        register(getHandler, new GetSize("/session/:sessionId/element/:id/size"));
        register(getHandler, new GetName("/session/:sessionId/element/:id/name"));
        register(getHandler, new ActiveElement("/session/:sessionId/element/active"));
        // W3C endpoint
        register(getHandler, new GetElementScreenshot("/session/:sessionId/element/:id/screenshot"));
        // JSONWP endpoint
        register(getHandler, new GetElementScreenshot("/session/:sessionId/screenshot/:id"));
        register(getHandler, new Location("/session/:sessionId/element/:id/location"));
        register(getHandler, new GetDeviceSize("/session/:sessionId/window/:windowHandle/size"));
        register(getHandler, new Source("/session/:sessionId/source"));
        register(getHandler, new GetSystemBars("/session/:sessionId/appium/device/system_bars"));
        register(getHandler, new GetBatteryInfo("/session/:sessionId/appium/device/battery_info"));
        register(getHandler, new GetSettings("/session/:sessionId/appium/settings"));
        register(getHandler, new GetDevicePixelRatio("/session/:sessionId/appium/device/pixel_ratio"));
        register(getHandler, new FirstVisibleView("/session/:sessionId/appium/element/:id/first_visible"));
        register(getHandler, new GetAlertText("/session/:sessionId/alert/text"));
        register(getHandler, new GetDeviceInfo("/session/:sessionId/appium/device/info"));
        register(getHandler, new GetDisplayDensity("/session/:sessionId/appium/device/display_density"));
    }

    private void register(Map<String, BaseRequestHandler> registerOn, BaseRequestHandler handler) {
        registerOn.put(handler.getMappedUri(), handler);
    }

    @Nullable
    private BaseRequestHandler findMatcher(IHttpRequest request, Map<String, BaseRequestHandler> handler) {
        String[] urlToMatchSections = getRequestUrlSections(request.uri());
        return handler.entrySet().stream()
                .filter(entry -> {
                    String[] mapperUrlSections = getMapperUrlSectionsCached(entry.getKey());
                    return isFor(mapperUrlSections, urlToMatchSections);
                })
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(null);
    }

    @Nullable
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
                // To work around a bug in Selenium Grid 2.31.0.
                int qPos = section.indexOf('?');
                if (qPos != -1) {
                    sections[i] = section.substring(0, qPos);
                }
            }
            mapperUrlSectionsCache.put(mapperUrl, sections);
        }
        return sections;
    }

    private boolean isFor(String[] mapperUrlSections, String[] urlToMatchSections) {
        if (urlToMatchSections == null) {
            return mapperUrlSections.length == 0;
        }
        if (mapperUrlSections.length != urlToMatchSections.length) {
            return false;
        }
        return IntStream.range(0, mapperUrlSections.length)
                .allMatch(
                    i -> mapperUrlSections[i].startsWith(":") || mapperUrlSections[i].equals(urlToMatchSections[i])
                );
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
        if (handler != null) {
            handleRequest(request, response, handler);
        }
    }

    private void handleRequest(IHttpRequest request, IHttpResponse response, BaseRequestHandler handler) {
        addHandlerAttributesToRequest(request, handler.getMappedUri());
        AppiumResponse result = handler.handle(request);
        handleResponse(response, result);
    }

    private void handleResponse(IHttpResponse response, @Nullable AppiumResponse result) {
        if (result != null) {
            result.renderTo(response);
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

        String name = getParameter(mappedUri, request.uri(), ":name");
        if (name != null) {
            request.data().put(NAME_ID_KEY, name);
        }

        String id = getParameter(mappedUri, request.uri(), ":id");
        if (id != null) {
            request.data().put(ELEMENT_ID_KEY, id);
        }
        for (int elementIdx = SECOND_ELEMENT_IDX; elementIdx < MAX_ELEMENTS + SECOND_ELEMENT_IDX; ++elementIdx) {
            String elementId = getParameter(mappedUri, request.uri(), ":id" + elementIdx);
            if (elementId != null) {
                request.data().put(ELEMENT_ID_KEY + elementIdx, elementId);
            }
        }
    }

    @Nullable
    private String getParameter(String configuredUri, String actualUri, String param) {
        String[] configuredSections = configuredUri.split("/");
        String[] currentSections = actualUri.split("/");
        if (configuredSections.length != currentSections.length) {
            return null;
        }
        return IntStream.range(0, currentSections.length)
                .filter(i -> configuredSections[i].contains(param))
                .mapToObj(i -> {
                    try {
                        return URLDecoder.decode(currentSections[i], StandardCharsets.UTF_8.name());
                    } catch (UnsupportedEncodingException e) {
                        throw new IllegalArgumentException(e);
                    }
                })
                .findFirst()
                .orElse(null);
    }
}
