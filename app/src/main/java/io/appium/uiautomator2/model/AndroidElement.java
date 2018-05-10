package io.appium.uiautomator2.model;

import android.graphics.Rect;
import android.support.test.uiautomator.UiObjectNotFoundException;

import java.util.List;

import io.appium.uiautomator2.common.exceptions.InvalidCoordinatesException;
import io.appium.uiautomator2.common.exceptions.InvalidSelectorException;
import io.appium.uiautomator2.common.exceptions.NoAttributeFoundException;
import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;
import io.appium.uiautomator2.utils.Point;

public interface AndroidElement {

    By getBy();

    void clear() throws UiObjectNotFoundException;

    void click() throws UiObjectNotFoundException;

    boolean longClick() throws UiObjectNotFoundException;

    String getText() throws UiObjectNotFoundException;

    String getName() throws UiObjectNotFoundException;

    String getStringAttribute(final String attr) throws UiObjectNotFoundException,
            NoAttributeFoundException;

    boolean getBoolAttribute(final String attr)
            throws UiObjectNotFoundException, UiAutomator2Exception;

    boolean setText(final String text, boolean unicodeKeyboard);

    String getId();

    Rect getBounds() throws UiObjectNotFoundException;

    Object getChild(final Object sel) throws UiObjectNotFoundException,
            InvalidSelectorException, ClassNotFoundException;

    List<Object> getChildren(final Object selector, final By by)
            throws UiObjectNotFoundException, InvalidSelectorException, ClassNotFoundException;

    String getContentDesc() throws UiObjectNotFoundException;

    Object getUiObject();

    Point getAbsolutePosition(final Point point)
            throws UiObjectNotFoundException, InvalidCoordinatesException;

    boolean dragTo(final int destX, final int destY, final int steps)
            throws UiObjectNotFoundException, InvalidCoordinatesException;

    boolean dragTo(final Object destObj, final int steps)
            throws UiObjectNotFoundException, InvalidCoordinatesException;
}
