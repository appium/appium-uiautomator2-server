package io.appium.uiautomator2.model;

import android.support.test.uiautomator.UiSelector;
import android.view.accessibility.AccessibilityNodeInfo;

import io.appium.uiautomator2.utils.Attribute;

import static io.appium.uiautomator2.model.UiAutomationElement.charSequenceToString;

public class CustomUiSelector {
    private UiSelector selector;

    CustomUiSelector(UiSelector selector) {
        this.selector = selector;
    }

    /**
     * @param node
     * @return UiSelector object, based on UiAutomationElement attributes
     */
    public UiSelector getUiSelector(AccessibilityNodeInfo node) {
        XPathFinder.refreshUiElementTree();
        UiAutomationElement uiAutomationElement = UiAutomationElement.map.get(node);
        put(Attribute.PACKAGE, charSequenceToString(uiAutomationElement.getPackageName()));
        put(Attribute.CLASS, charSequenceToString(uiAutomationElement.getClassName()));
        put(Attribute.TEXT, charSequenceToString(uiAutomationElement.getText()));
        put(Attribute.CONTENT_DESC, charSequenceToString(uiAutomationElement.getContentDescription()));
        put(Attribute.RESOURCE_ID, charSequenceToString(uiAutomationElement.getResourceId()));
        put(Attribute.CHECKABLE, uiAutomationElement.isCheckable());
        put(Attribute.CHECKED, uiAutomationElement.isChecked());
        put(Attribute.CLICKABLE, uiAutomationElement.isClickable());
        put(Attribute.ENABLED, uiAutomationElement.isEnabled());
        put(Attribute.FOCUSABLE, uiAutomationElement.isFocusable());
        put(Attribute.FOCUSED, uiAutomationElement.isFocused());
        put(Attribute.LONG_CLICKABLE, uiAutomationElement.isLongClickable());
        put(Attribute.PASSWORD, uiAutomationElement.isPassword());
        put(Attribute.SCROLLABLE, uiAutomationElement.isScrollable());
        put(Attribute.SELECTED, uiAutomationElement.isSelected());
        put(Attribute.INDEX, uiAutomationElement.getIndex());

        return selector;
    }

    private void put(Attribute key, Object value) {
        if (value == null) {
            return;
        }
        switch (key) {
            case PACKAGE:
                selector = selector.packageName((String) value);
                break;
            case CLASS:
                selector = selector.className((String) value);
                break;
            case TEXT:
                selector = selector.text((String) value);
                break;
            case CONTENT_DESC:
                selector = selector.descriptionContains((String) value);
                break;
            case RESOURCE_ID:
                selector = selector.resourceId((String) value);
                break;
            case CHECKABLE:
                selector = selector.checkable((Boolean) value);
                break;
            case CHECKED:
                selector = selector.checked((Boolean) value);
                break;
            case CLICKABLE:
                selector = selector.clickable((Boolean) value);
                break;
            case ENABLED:
                selector = selector.enabled((Boolean) value);
                break;
            case FOCUSABLE:
                selector = selector.focusable((Boolean) value);
                break;
            case LONG_CLICKABLE:
                selector = selector.longClickable((Boolean) value);
                break;
            case SCROLLABLE:
                selector = selector.scrollable((Boolean) value);
                break;
            case SELECTED:
                selector = selector.selected((Boolean) value);
                break;
            case INDEX:
                selector = selector.index((Integer) value);
                break;
            default: //ignore
        }
    }
}
