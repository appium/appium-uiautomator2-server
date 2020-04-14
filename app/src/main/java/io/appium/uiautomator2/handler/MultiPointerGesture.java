package io.appium.uiautomator2.handler;

import android.view.MotionEvent.PointerCoords;

import io.appium.uiautomator2.model.api.appium_touch_actions.GestureModel;
import io.appium.uiautomator2.model.api.appium_touch_actions.TouchActionsModel;
import io.appium.uiautomator2.model.api.appium_touch_actions.TouchLocationModel;

import io.appium.uiautomator2.common.exceptions.InvalidElementStateException;
import io.appium.uiautomator2.core.UiAutomatorBridge;
import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;

import java.util.List;

import static io.appium.uiautomator2.utils.ModelUtils.toModel;

public class MultiPointerGesture extends SafeRequestHandler {

    public MultiPointerGesture(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) {
        final PointerCoords[][] pcs = parsePointerCoords(request);
        if (!UiAutomatorBridge.getInstance().getInteractionController().performMultiPointerGesture(pcs)) {
            throw new InvalidElementStateException("Unable to perform multi pointer gesture");
        }
        return new AppiumResponse(getSessionId(request));
    }

    private PointerCoords[][] parsePointerCoords(final IHttpRequest request) {
        TouchActionsModel model = toModel(request, TouchActionsModel.class);

        final double time = computeLongestTime(model.actions);

        final PointerCoords[][] pcs = new PointerCoords[model.actions.size()][];
        for (int i = 0; i < model.actions.size(); i++) {
            final List<GestureModel> gestures = model.actions.get(i);

            pcs[i] = gesturesToPointerCoords(time, gestures);
        }

        return pcs;
    }

    private double computeLongestTime(List<List<GestureModel>> actions) {
        double max = 0.0;
        for (final List<GestureModel> gestures : actions) {
            final double endTime = gestures.get(gestures.size() - 1).time;
            if (endTime > max) {
                max = endTime;
            }
        }
        return max;
    }

    private PointerCoords[] gesturesToPointerCoords(final double maxTime, List<GestureModel> gestures) {
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
        GestureModel current = gestures.get(0);
        double currentTime = current.time;
        double runningTime = 0.0;
        final int gesturesLength = gestures.size();
        for (int j = 0; j < steps; j++) {
            if (runningTime > currentTime && i < gesturesLength) {
                current = gestures.get(i++);
                currentTime = current.time;
            }

            pc[j] = createPointerCoords(current);
            runningTime += 0.005;
        }

        return pc;
    }

    private PointerCoords createPointerCoords(GestureModel gesture) {
        final TouchLocationModel o = gesture.touch;

        final int x = o.x.intValue();
        final int y = o.y.intValue();

        final PointerCoords p = new PointerCoords();
        p.size = 1;
        p.pressure = 1;
        p.x = x;
        p.y = y;

        return p;
    }
}
