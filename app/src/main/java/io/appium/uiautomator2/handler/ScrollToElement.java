package io.appium.uiautomator2.handler;

import androidx.annotation.Nullable;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;

import io.appium.uiautomator2.common.exceptions.ElementNotFoundException;
import io.appium.uiautomator2.common.exceptions.InvalidArgumentException;
import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.AndroidElement;
import io.appium.uiautomator2.model.AppiumUIA2Driver;
import io.appium.uiautomator2.model.Session;
import io.appium.uiautomator2.utils.Logger;

public class ScrollToElement extends SafeRequestHandler {

    public ScrollToElement(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) throws UiObjectNotFoundException {
        Logger.info("Scroll into view command");
        String[] elementIds = getElementIds(request);
        StringBuilder errorMsg = new StringBuilder();
        UiObject elementUiObject = null;
        UiObject scrollElementUiObject = null;
        Session session = AppiumUIA2Driver.getInstance().getSessionOrThrow();

        AndroidElement element = session.getKnownElements().getElementFromCache(elementIds[0]);
        if (element == null) {
            throw new ElementNotFoundException();
        }
        AndroidElement scrollToElement = session.getKnownElements().getElementFromCache(elementIds[1]);
        if (scrollToElement == null) {
            throw new ElementNotFoundException();
        }

        // attempt to get UiObjects from the container and scroll to element
        // if we can't, we have to error out, since scrollIntoView only works with UiObjects
        // (and not for example UiObject2)
        // TODO make an equivalent implementation of this method for UiObject2 if possible
        try {
            elementUiObject = (UiObject) element.getUiObject();
            try {
                scrollElementUiObject = (UiObject) scrollToElement.getUiObject();
            } catch (Exception e) {
                errorMsg.append("Scroll to Element");
            }
        } catch (Exception e) {
            errorMsg.append("Element");
        }

        if (!errorMsg.toString().isEmpty()) {
            errorMsg.append(" was not an instance of UiObject; only UiSelector is supported. " +
                    "Ensure you use the '-android uiautomator' locator strategy when " +
                    "finding elements for use with ScrollToElement");
            Logger.error(errorMsg.toString());
            throw new InvalidArgumentException(errorMsg.toString());
        }

        boolean elementIsFound = false;
        if (elementUiObject != null) {
            UiScrollable uiScrollable = new UiScrollable(elementUiObject.getSelector());
            elementIsFound = uiScrollable.scrollIntoView(scrollElementUiObject);
        }
        return new AppiumResponse(getSessionId(request), elementIsFound);
    }

    private static class UiScrollable extends androidx.test.uiautomator.UiScrollable {

        /**
         * Constructor.
         *
         * @param container a {@link UiSelector} selector to identify the scrollable
         *                  layout element.
         * @since API Level 16
         */
        public UiScrollable(UiSelector container) {
            super(container);
        }

        @Override
        public boolean scrollIntoView(@Nullable UiObject obj) throws UiObjectNotFoundException {
            if (obj == null) {
                return false;
            }

            if (obj.exists()) {
                return true;
            }

            // we will need to reset the search from the beginning to start search
            flingToBeginning(getMaxSearchSwipes());
            if (obj.exists()) {
                return true;
            }


            for (int x = 0; x < getMaxSearchSwipes(); x++) {
                if (!scrollForward()) {
                    return false;
                }

                if (obj.exists()) {
                    return true;
                }
            }

            return false;
        }

    }
}
