package io.appium.uiautomator2.handler;


import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.view.accessibility.AccessibilityNodeInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.xml.parsers.ParserConfigurationException;

import io.appium.uiautomator2.common.exceptions.ElementNotFoundException;
import io.appium.uiautomator2.common.exceptions.InvalidSelectorException;
import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;
import io.appium.uiautomator2.common.exceptions.UiSelectorSyntaxException;
import io.appium.uiautomator2.core.AccessibilityNodeInfoGetter;
import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.AndroidElement;
import io.appium.uiautomator2.model.By;
import io.appium.uiautomator2.model.By.ByClass;
import io.appium.uiautomator2.model.By.ById;
import io.appium.uiautomator2.model.KnownElements;
import io.appium.uiautomator2.model.Session;
import io.appium.uiautomator2.model.XPathFinder;
import io.appium.uiautomator2.model.internal.NativeAndroidBySelector;
import io.appium.uiautomator2.server.WDStatus;
import io.appium.uiautomator2.utils.Device;
import io.appium.uiautomator2.utils.ElementHelpers;
import io.appium.uiautomator2.utils.Logger;
import io.appium.uiautomator2.utils.NodeInfoList;
import io.appium.uiautomator2.utils.UiAutomatorParser;

import static io.appium.uiautomator2.model.internal.CustomUiDevice.getInstance;
import static io.appium.uiautomator2.utils.Device.getAndroidElement;

public class FindElement extends SafeRequestHandler {

    /**
     * java_package : type / name
     *
     * com.example.Test:id/enter
     *
     * ^[a-zA-Z_] - Java package must start with letter or underscore
     * [a-zA-Z0-9\._]* - Java package may contain letters, numbers, periods and
     * underscores : - : ends the package and starts the type [^\/]+ - type is
     * made up of at least one non-/ characters \\/ - / ends the type and starts
     * the name [\S]+$ - the name contains at least one non-space character and
     * then the line is ended
     *
     * Example:
     * http://java-regex-tester.appspot.com/regex/5f04ac92-f9aa-45a6-b1dc-e2c25fd3cc6b
     *
     */
    static final Pattern resourceIdRegex   = Pattern
            .compile("^[a-zA-Z_][a-zA-Z0-9\\._]*:[^\\/]+\\/[\\S]+$");

    public FindElement(String mappedUri) {
        super(mappedUri);
    }

    /**
     * returns  UiObject2 for an xpath expression
     * TODO: Need to handle contextId based finding
     */
    private static Object getXPathUiObject(final String expression, AndroidElement element) throws ParserConfigurationException, InvalidSelectorException, ClassNotFoundException, UiAutomator2Exception {
        AccessibilityNodeInfo nodeInfo = null;
        if(element != null) {
            nodeInfo = AccessibilityNodeInfoGetter.fromUiObject(element.getUiObject());
        }
        final NodeInfoList nodeList = XPathFinder.getNodesList(expression, nodeInfo /* AccessibilityNodeInfo */);
        if (nodeList.size() == 0) {
                throw new ElementNotFoundException();
        }
        return getInstance().findObject(nodeList);
    }

    @Override
    public AppiumResponse safeHandle(IHttpRequest request) {
        try {
            Logger.info("Find element command");
            KnownElements ke = new KnownElements();
            final JSONObject payload = getPayload(request);
            final String method = payload.getString("strategy");
            final String selector = payload.getString("selector");
            final String contextId = payload.getString("context");
            Logger.info(String.format("find element command using '%s' with selector '%s'.", method, selector));
            final By by = new NativeAndroidBySelector().pickFrom(method, selector);

            Device.waitForIdle();
            Object element;
            if(contextId.length() > 0) {
                element = this.findElement(by, contextId);
            } else {
                element = this.findElement(by);
            }
            if (element == null) {
                return new AppiumResponse(getSessionId(request), WDStatus.NO_SUCH_ELEMENT);
            } else {
                String id = UUID.randomUUID().toString();
                AndroidElement androidElement = getAndroidElement(id, element, by);
                ke.add(androidElement);
                JSONObject result = ElementHelpers.toJSON(androidElement);
                return new AppiumResponse(getSessionId(request), WDStatus.SUCCESS, result);
            }
        } catch (UnsupportedOperationException e) {
            Logger.error("Unsupported operation: ", e);
            return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR, e);
        } catch (InvalidSelectorException e) {
            Logger.error("Invalid selector: ", e);
            return new AppiumResponse(getSessionId(request), WDStatus.INVALID_SELECTOR, e);
        } catch (ElementNotFoundException e) {
            Logger.error("Element not found: ", e);
            return new AppiumResponse(getSessionId(request), WDStatus.NO_SUCH_ELEMENT);
        } catch (ParserConfigurationException e) {
            Logger.error("Unable to parse configuration: ", e);
            return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR, e);
        } catch (ClassNotFoundException e) {
            Logger.error("Class not found: ", e);
            return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR, e);
        } catch (JSONException e) {
            Logger.error("Exception while reading JSON: ", e);
            return new AppiumResponse(getSessionId(request), WDStatus.JSON_DECODER_ERROR, e);
        } catch (UiSelectorSyntaxException e) {
            Logger.error("Unable to parse UiSelector: ", e);
            return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_COMMAND, e);
        } catch (UiAutomator2Exception e) {
            Logger.error("Exception while finding element: ", e);
            return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR, e);
        } catch (UiObjectNotFoundException e) {
            Logger.error("Element not found: ", e);
            return new AppiumResponse(getSessionId(request), WDStatus.NO_SUCH_ELEMENT);
        }
    }

    private Object findElement(By by) throws InvalidSelectorException, ElementNotFoundException,
            ParserConfigurationException, ClassNotFoundException, UiSelectorSyntaxException,
            UiAutomator2Exception, UiObjectNotFoundException {
        if (by instanceof ById) {
            String locator = getElementLocator((ById)by);
            return getInstance().findObject(android.support.test.uiautomator.By.res(locator));
        } else if (by instanceof By.ByAccessibilityId) {
            return getInstance().findObject(android.support.test.uiautomator.By.desc(by.getElementLocator()));
        } else if (by instanceof ByClass) {
            return getInstance().findObject(android.support.test.uiautomator.By.clazz(by.getElementLocator()));
        } else if (by instanceof By.ByXPath) {
            return getXPathUiObject(by.getElementLocator(), null /* AndroidElement */);
        } else if (by instanceof By.ByAndroidUiAutomator) {
            return getInstance().findObject(findByUiAutomator(by.getElementLocator()));
        }
        String msg = String.format("By locator %s is currently not supported!", by.getClass().getSimpleName());
        throw new UnsupportedOperationException(msg);
    }

    private Object findElement(By by, String contextId) throws InvalidSelectorException, ParserConfigurationException, ClassNotFoundException, UiSelectorSyntaxException, UiAutomator2Exception, UiObjectNotFoundException {

        AndroidElement element = KnownElements.getElementFromCache(contextId);
        if (element == null) {
            throw new ElementNotFoundException();
        }
        if (by instanceof ById) {
            String locator = getElementLocator((ById)by);
            return element.getChild(android.support.test.uiautomator.By.res(locator));
        } else if (by instanceof By.ByAccessibilityId) {
            return element.getChild(android.support.test.uiautomator.By.desc(by.getElementLocator()));
        } else if (by instanceof ByClass) {
            return element.getChild(android.support.test.uiautomator.By.clazz(by.getElementLocator()));
        } else if (by instanceof By.ByXPath) {
            return getXPathUiObject(by.getElementLocator(), element);
        } else if (by instanceof By.ByAndroidUiAutomator) {
            return element.getChild(findByUiAutomator(by.getElementLocator()));
        }
        String msg = String.format("By locator %s is currently not supported!", by.getClass().getSimpleName());
        throw new UnsupportedOperationException(msg);

    }
    /**
     * finds the UiSelector for given expression
     */
    public UiSelector findByUiAutomator(String expression) throws UiSelectorSyntaxException,
            UiObjectNotFoundException {
        List<UiSelector> parsedSelectors = null;
        UiAutomatorParser uiAutomatorParser = new UiAutomatorParser();
        final List<UiSelector> selectors = new ArrayList<UiSelector>();
        parsedSelectors = uiAutomatorParser.parse(expression);

        for (final UiSelector selector : parsedSelectors) {
            selectors.add(selector);
        }
        return selectors.get(0);
    }

    public static String getElementLocator(ById by){
        String locator = by.getElementLocator();

        if(!resourceIdRegex.matcher(by.getElementLocator()).matches()) {
            // not a fully qualified resource id
            // transform "textToBeChanged" into:
            // com.example.android.testing.espresso.BasicSample:id/textToBeChanged
            // it's prefixed with the app package.
            locator = (String) Session.capabilities.get("appPackage") + ":id/" + by.getElementLocator();
            Logger.debug("Updated findElement locator strategy: " + locator);
        }
        return locator;
    }
}
