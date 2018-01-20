package io.appium.uiautomator2.handler;

import android.view.MotionEvent.PointerCoords;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;
import io.appium.uiautomator2.core.UiAutomatorBridge;
import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.server.WDStatus;
import io.appium.uiautomator2.utils.Logger;

public class MultiPointerGesture extends SafeRequestHandler {

    public MultiPointerGesture(String mappedUri) {
        super(mappedUri);
    }

    @Override
    public AppiumResponse safeHandle(IHttpRequest request) {
        final PointerCoords[][] pcs;
        try {
            pcs = parsePointerCoords(request);

            Boolean rt = UiAutomatorBridge.getInstance().getInteractionController().performMultiPointerGesture(pcs);
            if (!rt) {
                return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR, "Unable to perform multi pointer gesture");
            }
            return new AppiumResponse(getSessionId(request), WDStatus.SUCCESS, "OK");
        } catch (JSONException e) {
            Logger.error("Exception while reading JSON: ", e);
            return new AppiumResponse(getSessionId(request), WDStatus.JSON_DECODER_ERROR, e);
        } catch (UiAutomator2Exception e) {
            Logger.error("Exception while performing LongPressKeyCode action: ", e);
            return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR, e);
        }
    }

    private PointerCoords[][] parsePointerCoords(final IHttpRequest request)
            throws JSONException {
        final JSONArray actions = (org.json.JSONArray) getPayload(request).get("actions");

        final double time = computeLongestTime(actions);

        final PointerCoords[][] pcs = new PointerCoords[actions.length()][];
        for (int i = 0; i < actions.length(); i++) {
            final JSONArray gestures = actions.getJSONArray(i);

            pcs[i] = gesturesToPointerCoords(time, gestures);
        }

        return pcs;
    }

    private double computeLongestTime(final JSONArray actions) throws JSONException {
        double max = 0.0;
        for (int i = 0; i < actions.length(); i++) {
            final JSONArray gestures = actions.getJSONArray(i);
            final double endTime = gestures.getJSONObject(gestures.length() - 1)
                    .getDouble("time");
            if (endTime > max) {
                max = endTime;
            }
        }

        return max;
    }

    private PointerCoords[] gesturesToPointerCoords(final double maxTime,
                                                    final JSONArray gestures) throws JSONException {
        // gestures, e.g.:
        // [
        // {"touch":{"y":529.5,"x":120},"time":0.2},
        // {"touch":{"y":529.5,"x":130},"time":0.4},
        // {"touch":{"y":454.5,"x":140},"time":0.6},
        // {"touch":{"y":304.5,"x":150},"time":0.8}
        // ]

        // From the docs:
        // "Steps are injected about 5 milliseconds apart, so 100 steps may take
        // around 0.5 seconds to complete."
        final int steps = (int) (maxTime * 200) + 2;

        final PointerCoords[] pc = new PointerCoords[steps];

        int i = 1;
        JSONObject current = gestures.getJSONObject(0);
        double currentTime = current.getDouble("time");
        double runningTime = 0.0;
        final int gesturesLength = gestures.length();
        for (int j = 0; j < steps; j++) {
            if (runningTime > currentTime && i < gesturesLength) {
                current = gestures.getJSONObject(i++);
                currentTime = current.getDouble("time");
            }

            pc[j] = createPointerCoords(current);

            runningTime += 0.005;
        }

        return pc;
    }

    private PointerCoords createPointerCoords(final JSONObject obj)
            throws JSONException {
        final JSONObject o = obj.getJSONObject("touch");

        final int x = o.getInt("x");
        final int y = o.getInt("y");

        final PointerCoords p = new PointerCoords();
        p.size = 1;
        p.pressure = 1;
        p.x = x;
        p.y = y;

        return p;
    }
}
