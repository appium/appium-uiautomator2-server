package io.appium.uiautomator2.utils;

import android.view.accessibility.AccessibilityNodeInfo;

import java.util.ArrayList;

public class NodeInfoList {

    private ArrayList<AccessibilityNodeInfo> nodeList = new ArrayList<AccessibilityNodeInfo>();

    public void addToList(AccessibilityNodeInfo node) {
        nodeList.add(node);
    }

    public ArrayList<AccessibilityNodeInfo> getNodeList() {
        return nodeList;
    }

    public int size() {
        return nodeList.size();
    }

}
