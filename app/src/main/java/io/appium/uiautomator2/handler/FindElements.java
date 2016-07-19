package io.appium.uiautomator2.handler;

import android.support.test.uiautomator.BySelector;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;

import org.json.JSONArray;
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
import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.AndroidElement;
import io.appium.uiautomator2.model.By;
import io.appium.uiautomator2.model.KnownElements;
import io.appium.uiautomator2.model.internal.NativeAndroidBySelector;
import io.appium.uiautomator2.server.WDStatus;
import io.appium.uiautomator2.utils.ClassInstancePair;
import io.appium.uiautomator2.utils.Device;
import io.appium.uiautomator2.utils.ElementHelpers;
import io.appium.uiautomator2.utils.Logger;
import io.appium.uiautomator2.utils.UiAutomatorParser;
import io.appium.uiautomator2.utils.XMLHierarchy;

import static io.appium.uiautomator2.model.internal.CustomUiDevice.getInstance;
import static io.appium.uiautomator2.utils.Device.getAndroidElement;
import static io.appium.uiautomator2.utils.Device.getUiDevice;

public class FindElements extends SafeRequestHandler {

    private static final Pattern endsWithInstancePattern = Pattern.compile(".*INSTANCE=\\d+]$");

    public FindElements(String mappedUri) {
        super(mappedUri);
    }

    /**
     * returns  UiObject2 for an xpath expression
     **/
    private static List<Object> getXPathUiObjects(final String expression, final boolean multiple, String contextId) throws ElementNotFoundException, ParserConfigurationException, InvalidSelectorException, ClassNotFoundException, UiAutomator2Exception {
        final List<BySelector> selectors = new ArrayList<BySelector>();

        final ArrayList<ClassInstancePair> pairs = contextId.equals("") ? XMLHierarchy.getClassInstancePairs(expression) : XMLHierarchy.getClassInstancePairs(expression, contextId);

        if (!multiple) {
            if (pairs.size() == 0) {
                throw new ElementNotFoundException();
            }
            selectors.add(pairs.get(0).getSelector());
        } else {
            for (final ClassInstancePair pair : pairs) {
                selectors.add(pair.getSelector());
            }
        }
        return getInstance().findObjects(selectors.get(0));
    }

    @Override
    public AppiumResponse safeHandle(IHttpRequest request) {
        JSONArray result = new JSONArray();
        try {
            Logger.info("Find elements command");
            KnownElements ke = new KnownElements();
            JSONObject payload = getPayload(request);
            String method = payload.getString("strategy");
            String selector = payload.getString("selector");
            Logger.info(String.format("find element command using '%s' with selector '%s'.", method, selector));
            By by = new NativeAndroidBySelector().pickFrom(method, selector);
            getUiDevice().waitForIdle();
            List<Object> elements = this.findElements(by);


            for (Object element : elements) {
                String id = UUID.randomUUID().toString();
                AndroidElement androidElement = getAndroidElement(id, element);
                ke.add(androidElement);
                JSONObject jsonElement = new JSONObject();
                jsonElement.put("ELEMENT", id);
                result.put(jsonElement);
            }
            return new AppiumResponse(getSessionId(request), result);
        } catch (ElementNotFoundException ignored) {
            /* For findElements up on no Element. instead of throwing exception unlike in findElement,
               empty array should be return. for more info refer:
               https://github.com/SeleniumHQ/selenium/wiki/JsonWireProtocol#sessionsessionidelements
              */
            return new AppiumResponse(getSessionId(request), result);
        } catch (UnsupportedOperationException e) {
            Logger.error("Unsupported operation: ", e);
            return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR, e);
        } catch (InvalidSelectorException e) {
            Logger.error("Invalid selector: ", e);
            return new AppiumResponse(getSessionId(request), WDStatus.INVALID_SELECTOR, e);
        } catch (JSONException e) {
            Logger.error("Exception while reading JSON: ", e);
            return new AppiumResponse(getSessionId(request), WDStatus.JSON_DECODER_ERROR, e);
        } catch (ParserConfigurationException e) {
            Logger.error("Unable to parse configuration: ", e);
            return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR, e);
        } catch (ClassNotFoundException e) {
            Logger.error("Class not found: ", e);
            return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR, e);
        } catch (UiAutomator2Exception e) {
            Logger.error("Exception while finding element: ", e);
            return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR, e);
        }
    }

    private List<Object> findElements(By by) throws ElementNotFoundException, ParserConfigurationException, ClassNotFoundException, InvalidSelectorException, UiAutomator2Exception {
        if (by instanceof By.ById) {
            return getInstance().findObjects(android.support.test.uiautomator.By.res(by.getElementLocator()));
        }/* else if (by instanceof ByTagName) {
            return findElementByTagName(by.getElementLocator());
        }*/ else if (by instanceof By.ByLinkText) {
            return getInstance().findObjects(android.support.test.uiautomator.By.desc(by.getElementLocator()));
        } else if (by instanceof By.ByPartialLinkText) {
            return getInstance().findObjects(android.support.test.uiautomator.By.descContains(by.getElementLocator()));
        } else if (by instanceof By.ByClass) {
            return getInstance().findObjects(android.support.test.uiautomator.By.clazz(by.getElementLocator()));
        } else if (by instanceof By.ByName) {
            return getInstance().findObjects(android.support.test.uiautomator.By.text(by.getElementLocator()));
        } else if (by instanceof By.ByXPath) {
            //TODO: need to handle the context parameter in a smart way
            return getXPathUiObjects(by.getElementLocator(), true, "");
        } else if (by instanceof By.ByAndroidUiAutomator) {
            //TODO: need to handle the context parameter in a smart way
            return getUiObjectsUsingAutomator(findByUiAutomator(by.getElementLocator()), "");
        }

        String msg = String.format("By locator %s is curently not supported!", by.getClass().getSimpleName());
        throw new UnsupportedOperationException(msg);
    }

    public List<UiSelector> findByUiAutomator(String expression) {
        List<UiSelector> parsedSelectors = null;
        UiAutomatorParser uiAutomatorParser = new UiAutomatorParser();
        final List<UiSelector> selectors = new ArrayList<UiSelector>();
        try {
            parsedSelectors = uiAutomatorParser.parse(expression);
        } catch (final UiSelectorSyntaxException e) {
            Logger.error(new UiSelectorSyntaxException(
                    "Could not parse UiSelector argument: " + e.getMessage()));
        }

        for (final UiSelector selector : parsedSelectors) {
            selectors.add(selector);
        }
        return selectors;
    }

    /**
     * returns  List<UiObject> using '-android automator' expression
     **/
    private List<Object> getUiObjectsUsingAutomator(List<UiSelector> selectors, String contextId) {
        List<Object> foundElements = new ArrayList<Object>();
        for (final UiSelector sel : selectors) {
            // With multiple selectors, we expect that some elements may not
            // exist.
            try {
                Logger.debug("Using: " + sel.toString());
                final List<Object> elementsFromSelector = fetchElements(
                        sel, contextId);
                foundElements.addAll(elementsFromSelector);
            } catch (final UiObjectNotFoundException ignored) {
                //for findElements up on no elements, empty array should return.
            }
        }
        foundElements = ElementHelpers.dedupe(foundElements);
        return foundElements;
    }

    /**
     * finds elements with given UiSelector return List<UiObject
     *
     * @param sel
     * @param key
     * @return
     */
    private List<Object> fetchElements(UiSelector sel, String key) throws UiObjectNotFoundException {
        //TODO: finding elements with contextId yet to implement
        boolean keepSearching = true;
        final String selectorString = sel.toString();
        final boolean useIndex = selectorString.contains("CLASS_REGEX=");
        final boolean endsWithInstance = endsWithInstancePattern.matcher(selectorString).matches();
        Logger.debug("getElements selector:" + selectorString);
        final ArrayList<Object> elements = new ArrayList<Object>();

        // If sel is UiSelector[CLASS=android.widget.Button, INSTANCE=0]
        // then invoking instance with a non-0 argument will corrupt the selector.
        //
        // sel.instance(1) will transform the selector into:
        // UiSelector[CLASS=android.widget.Button, INSTANCE=1]
        //
        // The selector now points to an entirely different element.
        if (endsWithInstance) {
            Logger.debug("Selector ends with instance.");
            // There's exactly one element when using instance.
            UiObject instanceObj = getUiDevice().findObject(sel);
            if (instanceObj != null && instanceObj.exists()) {
                elements.add(instanceObj);
            }
            return elements;
        }

        UiObject lastFoundObj;
        final AndroidElement baseEl = KnownElements.getElementFromCache(key);

        UiSelector tmp;
        int counter = 0;
        while (keepSearching) {
            if (baseEl == null) {
                Logger.debug("Element[" + key + "] is null: (" + counter + ")");

                if (useIndex) {
                    Logger.debug("  using index...");
                    tmp = sel.index(counter);
                } else {
                    tmp = sel.instance(counter);
                }

                Logger.debug("getElements tmp selector:" + tmp.toString());
                lastFoundObj = Device.getUiDevice().findObject(tmp);
            } else {
                Logger.debug("Element[" + key + "] is " + baseEl.getId() + ", counter: "
                        + counter);
                lastFoundObj = (UiObject) baseEl.getChild(sel.instance(counter));
            }
            counter++;
            if (lastFoundObj != null && lastFoundObj.exists()) {
                elements.add(lastFoundObj);
            } else {
                keepSearching = false;
            }
        }
        return elements;

    }
}
