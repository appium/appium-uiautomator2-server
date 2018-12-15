package io.appium.uiautomator2.core;


import java.io.InvalidClassException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;
import io.appium.uiautomator2.utils.Logger;

public class UiObjectChildGenerator implements Iterable<Object> {

    protected Object parent;
    protected int curChild = -1;
    protected int childCount = -1;
    protected List<UiObject2> uiObject2Children = new ArrayList<>();

    public UiObjectChildGenerator(Object parent) throws InvalidClassException {
        if (!(parent instanceof UiObject) && !(parent instanceof UiObject2)) {
            throw new InvalidClassException("Parent must be UiObject or UiObject 2");
        }
        this.parent = parent;
    }

    @Override
    public Iterator<Object> iterator() {
        return new Iter();
    }

    private class Iter implements Iterator<Object> {
        private void init() {
            if (parent instanceof UiObject) {
                try {
                    childCount = ((UiObject) parent).getChildCount();
                } catch (UiObjectNotFoundException e) {
                    Logger.error("Could not get child count from UiObject");
                    childCount = 0;
                }
            } else {
                uiObject2Children = ((UiObject2) parent).getChildren();
                childCount = uiObject2Children.size();
            }
            curChild = 0;
        }

        @Override
        @Nullable
        public Object next() {
            if (curChild == -1) {
                init();
            }

            Object child;
            if (parent instanceof UiObject) {
                try {
                    child = ((UiObject) parent).getChild(new UiSelector().index(curChild));
                } catch (UiObjectNotFoundException e) {
                    child = null;
                }
            } else {
                child = uiObject2Children.get(curChild);
            }
            ++curChild;
            return child;
        }

        @Override
        public boolean hasNext() {
            if (curChild == -1) {
                init();
            }
            return curChild < childCount;
        }
    }
}
