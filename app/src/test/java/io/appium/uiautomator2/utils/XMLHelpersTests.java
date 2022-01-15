/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.appium.uiautomator2.utils;

import org.apache.xerces.jaxp.DocumentBuilderFactoryImpl;
import org.eclipse.wst.xml.xpath2.api.Item;
import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.api.XPath2Expression;
import org.eclipse.wst.xml.xpath2.processor.Engine;
import org.eclipse.wst.xml.xpath2.processor.XPathParserException;
import org.eclipse.wst.xml.xpath2.processor.util.DynamicContextBuilder;
import org.eclipse.wst.xml.xpath2.processor.util.StaticContextBuilder;
import org.junit.Ignore;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import static org.junit.Assert.assertEquals;

import android.os.SystemClock;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import io.appium.uiautomator2.common.exceptions.InvalidSelectorException;

@SuppressWarnings("SameParameterValue")
public class XMLHelpersTests {
    private static final String XML = "<?xml version='1.0' encoding='UTF-8' standalone='yes' ?>\n" +
            "<hierarchy index=\"0\" class=\"hierarchy\" rotation=\"0\" width=\"1080\" height=\"1794\">\n" +
            "  <android.widget.FrameLayout index=\"0\" package=\"com.abcde.pqrst.gamma\" class=\"android.widget.FrameLayout\" text=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[0,0][1080,1794]\" displayed=\"true\">\n" +
            "    <android.widget.LinearLayout index=\"0\" package=\"com.abcde.pqrst.gamma\" class=\"android.widget.LinearLayout\" text=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[0,0][1080,1794]\" displayed=\"true\">\n" +
            "      <android.widget.FrameLayout index=\"0\" package=\"com.abcde.pqrst.gamma\" class=\"android.widget.FrameLayout\" text=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[0,63][1080,1794]\" displayed=\"true\">\n" +
            "        <android.widget.LinearLayout index=\"0\" package=\"com.abcde.pqrst.gamma\" class=\"android.widget.LinearLayout\" text=\"\" resource-id=\"com.abcde.pqrst.gamma:id/action_bar_root\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[0,63][1080,1794]\" displayed=\"true\">\n" +
            "          <android.widget.FrameLayout index=\"0\" package=\"com.abcde.pqrst.gamma\" class=\"android.widget.FrameLayout\" text=\"\" resource-id=\"android:id/content\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[0,63][1080,1794]\" displayed=\"true\">\n" +
            "            <android.widget.FrameLayout index=\"0\" package=\"com.abcde.pqrst.gamma\" class=\"android.widget.FrameLayout\" text=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[0,63][1080,1794]\" displayed=\"true\">\n" +
            "              <androidx.drawerlayout.widget.DrawerLayout index=\"0\" package=\"com.abcde.pqrst.gamma\" class=\"androidx.drawerlayout.widget.DrawerLayout\" text=\"\" resource-id=\"com.abcde.pqrst.gamma:id/drawer_layout\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[0,63][1080,1794]\" displayed=\"true\">\n" +
            "                <android.view.ViewGroup index=\"0\" package=\"com.abcde.pqrst.gamma\" class=\"android.view.ViewGroup\" text=\"\" resource-id=\"com.abcde.pqrst.gamma:id/toolbar_coordinator_layout\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[0,63][1080,1794]\" displayed=\"true\">\n" +
            "                  <android.view.ViewGroup index=\"0\" package=\"com.abcde.pqrst.gamma\" class=\"android.view.ViewGroup\" text=\"\" resource-id=\"com.abcde.pqrst.gamma:id/toolbar\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[0,63][1080,210]\" displayed=\"true\">\n" +
            "                    <android.widget.ImageButton index=\"0\" package=\"com.abcde.pqrst.gamma\" class=\"android.widget.ImageButton\" text=\"\" content-desc=\"Navigate up\" checkable=\"false\" checked=\"false\" clickable=\"true\" enabled=\"true\" focusable=\"true\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[0,63][147,210]\" displayed=\"true\" />\n" +
            "                    <android.widget.TextView index=\"1\" package=\"com.abcde.pqrst.gamma\" class=\"android.widget.TextView\" text=\"OFFLINE MAPS\" resource-id=\"com.abcde.pqrst.gamma:id/toolbar_title\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[353,110][727,162]\" displayed=\"true\" />\n" +
            "                  </android.view.ViewGroup>\n" +
            "                  <android.widget.LinearLayout index=\"2\" package=\"com.abcde.pqrst.gamma\" class=\"android.widget.LinearLayout\" text=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[0,210][1080,1794]\" displayed=\"true\">\n" +
            "                    <android.widget.FrameLayout index=\"0\" package=\"com.abcde.pqrst.gamma\" class=\"android.widget.FrameLayout\" text=\"\" resource-id=\"com.abcde.pqrst.gamma:id/activity_frame_layout\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[0,210][1080,1794]\" displayed=\"true\">\n" +
            "                      <android.widget.LinearLayout index=\"0\" package=\"com.abcde.pqrst.gamma\" class=\"android.widget.LinearLayout\" text=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[0,210][1080,1794]\" displayed=\"true\">\n" +
            "                        <android.widget.TextView index=\"0\" package=\"com.abcde.pqrst.gamma\" class=\"android.widget.TextView\" text=\"Download settings\" resource-id=\"com.abcde.pqrst.gamma:id/offline_management_settings_header\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[0,210][1080,334]\" displayed=\"true\" />\n" +
            "                        <android.view.View index=\"1\" package=\"com.abcde.pqrst.gamma\" class=\"android.view.View\" text=\"\" resource-id=\"com.abcde.pqrst.gamma:id/offline_divider_1\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[0,334][1080,337]\" displayed=\"true\" />\n" +
            "                        <android.view.ViewGroup index=\"2\" package=\"com.abcde.pqrst.gamma\" class=\"android.view.ViewGroup\" text=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[0,337][1080,589]\" displayed=\"true\">\n" +
            "                          <android.widget.LinearLayout index=\"0\" package=\"com.abcde.pqrst.gamma\" class=\"android.widget.LinearLayout\" text=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[0,337][932,589]\" displayed=\"true\">\n" +
            "                            <android.widget.TextView index=\"0\" package=\"com.abcde.pqrst.gamma\" class=\"android.widget.TextView\" text=\"Allow downloads over cell network\" resource-id=\"com.abcde.pqrst.gamma:id/offline_list_network_switch_title\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[42,379][890,455]\" displayed=\"true\" />\n" +
            "                            <android.widget.TextView index=\"1\" package=\"com.abcde.pqrst.gamma\" class=\"android.widget.TextView\" text=\"If Wi-Fi isn't available, your maps will use your cell data to download updates.\" resource-id=\"com.abcde.pqrst.gamma:id/offline_list_network_switch_message\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[42,455][890,547]\" displayed=\"true\" />\n" +
            "                          </android.widget.LinearLayout>\n" +
            "                          <android.widget.Switch index=\"1\" package=\"com.abcde.pqrst.gamma\" class=\"android.widget.Switch\" text=\"OFF\" content-desc=\"offline_list_network_switch\" resource-id=\"com.abcde.pqrst.gamma:id/offline_list_network_switch\" checkable=\"true\" checked=\"false\" clickable=\"true\" enabled=\"true\" focusable=\"true\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[932,337][1080,471]\" displayed=\"true\" />\n" +
            "                        </android.view.ViewGroup>\n" +
            "                        <android.view.View index=\"3\" package=\"com.abcde.pqrst.gamma\" class=\"android.view.View\" text=\"\" resource-id=\"com.abcde.pqrst.gamma:id/offline_divider_2\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[0,589][1080,592]\" displayed=\"true\" />\n" +
            "                        <androidx.recyclerview.widget.RecyclerView index=\"4\" package=\"com.abcde.pqrst.gamma\" class=\"androidx.recyclerview.widget.RecyclerView\" text=\"\" resource-id=\"com.abcde.pqrst.gamma:id/recycler_view\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"true\" focused=\"true\" long-clickable=\"false\" password=\"false\" scrollable=\"true\" selected=\"false\" bounds=\"[0,592][1080,1794]\" displayed=\"true\">\n" +
            "                          <android.widget.RelativeLayout index=\"0\" package=\"com.abcde.pqrst.gamma\" class=\"android.widget.RelativeLayout\" text=\"\" content-desc=\"Region Cell 2\" resource-id=\"com.abcde.pqrst.gamma:id/region_cell_layout\" checkable=\"false\" checked=\"false\" clickable=\"true\" enabled=\"true\" focusable=\"true\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[0,592][1080,674]\" displayed=\"true\">\n" +
            "                            <android.widget.LinearLayout index=\"0\" package=\"com.abcde.pqrst.gamma\" class=\"android.widget.LinearLayout\" text=\"\" resource-id=\"com.abcde.pqrst.gamma:id/region_descriptor_layout\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[42,592][933,674]\" displayed=\"true\">\n" +
            "                              <android.widget.TextView index=\"1\" package=\"com.abcde.pqrst.gamma\" class=\"android.widget.TextView\" text=\"72 MB\" resource-id=\"com.abcde.pqrst.gamma:id/region_subtext\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[42,592][933,641]\" displayed=\"true\" />\n" +
            "                            </android.widget.LinearLayout>\n" +
            "                            <android.widget.LinearLayout index=\"1\" package=\"com.abcde.pqrst.gamma\" class=\"android.widget.LinearLayout\" text=\"\" resource-id=\"com.abcde.pqrst.gamma:id/region_button_holder\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[933,592][1038,674]\" displayed=\"true\">\n" +
            "                              <android.widget.ImageButton index=\"0\" package=\"com.abcde.pqrst.gamma\" class=\"android.widget.ImageButton\" text=\"\" content-desc=\"region_download\" resource-id=\"com.abcde.pqrst.gamma:id/region_download\" checkable=\"false\" checked=\"false\" clickable=\"true\" enabled=\"true\" focusable=\"true\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[933,592][1038,634]\" displayed=\"true\" />\n" +
            "                            </android.widget.LinearLayout>\n" +
            "                          </android.widget.RelativeLayout>\n" +
            "                          <android.widget.RelativeLayout index=\"1\" package=\"com.abcde.pqrst.gamma\" class=\"android.widget.RelativeLayout\" text=\"\" content-desc=\"Region Cell 3\" resource-id=\"com.abcde.pqrst.gamma:id/region_cell_layout\" checkable=\"false\" checked=\"false\" clickable=\"true\" enabled=\"true\" focusable=\"true\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[0,677][1080,861]\" displayed=\"true\">\n" +
            "                            <android.widget.LinearLayout index=\"0\" package=\"com.abcde.pqrst.gamma\" class=\"android.widget.LinearLayout\" text=\"\" resource-id=\"com.abcde.pqrst.gamma:id/region_descriptor_layout\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[42,677][933,861]\" displayed=\"true\">\n" +
            "                              <android.widget.TextView index=\"0\" package=\"com.abcde.pqrst.gamma\" class=\"android.widget.TextView\" text=\"Ann Arbor, MI\" resource-id=\"com.abcde.pqrst.gamma:id/region_name\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[42,709][933,769]\" displayed=\"true\" />\n" +
            "                              <android.widget.TextView index=\"1\" package=\"com.abcde.pqrst.gamma\" class=\"android.widget.TextView\" text=\"151 MB\" resource-id=\"com.abcde.pqrst.gamma:id/region_subtext\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[42,775][933,828]\" displayed=\"true\" />\n" +
            "                            </android.widget.LinearLayout>\n" +
            "                            <android.widget.LinearLayout index=\"1\" package=\"com.abcde.pqrst.gamma\" class=\"android.widget.LinearLayout\" text=\"\" resource-id=\"com.abcde.pqrst.gamma:id/region_button_holder\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[933,677][1038,861]\" displayed=\"true\">\n" +
            "                              <android.widget.ImageButton index=\"0\" package=\"com.abcde.pqrst.gamma\" class=\"android.widget.ImageButton\" text=\"\" content-desc=\"region_download\" resource-id=\"com.abcde.pqrst.gamma:id/region_download\" checkable=\"false\" checked=\"false\" clickable=\"true\" enabled=\"true\" focusable=\"true\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[933,716][1038,821]\" displayed=\"true\" />\n" +
            "                            </android.widget.LinearLayout>\n" +
            "                          </android.widget.RelativeLayout>\n" +
            "                          <android.widget.RelativeLayout index=\"2\" package=\"com.abcde.pqrst.gamma\" class=\"android.widget.RelativeLayout\" text=\"\" content-desc=\"Region Cell 4\" resource-id=\"com.abcde.pqrst.gamma:id/region_cell_layout\" checkable=\"false\" checked=\"false\" clickable=\"true\" enabled=\"true\" focusable=\"true\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[0,864][1080,1048]\" displayed=\"true\">\n" +
            "                            <android.widget.LinearLayout index=\"0\" package=\"com.abcde.pqrst.gamma\" class=\"android.widget.LinearLayout\" text=\"\" resource-id=\"com.abcde.pqrst.gamma:id/region_descriptor_layout\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[42,864][933,1048]\" displayed=\"true\">\n" +
            "                              <android.widget.TextView index=\"0\" package=\"com.abcde.pqrst.gamma\" class=\"android.widget.TextView\" text=\"Asheville, NC\" resource-id=\"com.abcde.pqrst.gamma:id/region_name\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[42,896][933,956]\" displayed=\"true\" />\n" +
            "                              <android.widget.TextView index=\"1\" package=\"com.abcde.pqrst.gamma\" class=\"android.widget.TextView\" text=\"88 MB\" resource-id=\"com.abcde.pqrst.gamma:id/region_subtext\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[42,962][933,1015]\" displayed=\"true\" />\n" +
            "                            </android.widget.LinearLayout>\n" +
            "                            <android.widget.LinearLayout index=\"1\" package=\"com.abcde.pqrst.gamma\" class=\"android.widget.LinearLayout\" text=\"\" resource-id=\"com.abcde.pqrst.gamma:id/region_button_holder\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[933,864][1038,1048]\" displayed=\"true\">\n" +
            "                              <android.widget.ImageButton index=\"0\" package=\"com.abcde.pqrst.gamma\" class=\"android.widget.ImageButton\" text=\"\" content-desc=\"region_download\" resource-id=\"com.abcde.pqrst.gamma:id/region_download\" checkable=\"false\" checked=\"false\" clickable=\"true\" enabled=\"true\" focusable=\"true\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[933,903][1038,1008]\" displayed=\"true\" />\n" +
            "                            </android.widget.LinearLayout>\n" +
            "                          </android.widget.RelativeLayout>\n" +
            "                          <android.widget.RelativeLayout index=\"3\" package=\"com.abcde.pqrst.gamma\" class=\"android.widget.RelativeLayout\" text=\"\" content-desc=\"Region Cell 5\" resource-id=\"com.abcde.pqrst.gamma:id/region_cell_layout\" checkable=\"false\" checked=\"false\" clickable=\"true\" enabled=\"true\" focusable=\"true\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[0,1051][1080,1235]\" displayed=\"true\">\n" +
            "                            <android.widget.LinearLayout index=\"0\" package=\"com.abcde.pqrst.gamma\" class=\"android.widget.LinearLayout\" text=\"\" resource-id=\"com.abcde.pqrst.gamma:id/region_descriptor_layout\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[42,1051][933,1235]\" displayed=\"true\">\n" +
            "                              <android.widget.TextView index=\"0\" package=\"com.abcde.pqrst.gamma\" class=\"android.widget.TextView\" text=\"Atlanta, GA\" resource-id=\"com.abcde.pqrst.gamma:id/region_name\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[42,1083][933,1143]\" displayed=\"true\" />\n" +
            "                              <android.widget.TextView index=\"1\" package=\"com.abcde.pqrst.gamma\" class=\"android.widget.TextView\" text=\"669 MB\" resource-id=\"com.abcde.pqrst.gamma:id/region_subtext\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[42,1149][933,1202]\" displayed=\"true\" />\n" +
            "                            </android.widget.LinearLayout>\n" +
            "                            <android.widget.LinearLayout index=\"1\" package=\"com.abcde.pqrst.gamma\" class=\"android.widget.LinearLayout\" text=\"\" resource-id=\"com.abcde.pqrst.gamma:id/region_button_holder\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[933,1051][1038,1235]\" displayed=\"true\">\n" +
            "                              <android.widget.ImageButton index=\"0\" package=\"com.abcde.pqrst.gamma\" class=\"android.widget.ImageButton\" text=\"\" content-desc=\"region_download\" resource-id=\"com.abcde.pqrst.gamma:id/region_download\" checkable=\"false\" checked=\"false\" clickable=\"true\" enabled=\"true\" focusable=\"true\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[933,1090][1038,1195]\" displayed=\"true\" />\n" +
            "                            </android.widget.LinearLayout>\n" +
            "                          </android.widget.RelativeLayout>\n" +
            "                          <android.widget.RelativeLayout index=\"4\" package=\"com.abcde.pqrst.gamma\" class=\"android.widget.RelativeLayout\" text=\"\" content-desc=\"Region Cell 6\" resource-id=\"com.abcde.pqrst.gamma:id/region_cell_layout\" checkable=\"false\" checked=\"false\" clickable=\"true\" enabled=\"true\" focusable=\"true\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[0,1238][1080,1422]\" displayed=\"true\">\n" +
            "                            <android.widget.LinearLayout index=\"0\" package=\"com.abcde.pqrst.gamma\" class=\"android.widget.LinearLayout\" text=\"\" resource-id=\"com.abcde.pqrst.gamma:id/region_descriptor_layout\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[42,1238][933,1422]\" displayed=\"true\">\n" +
            "                              <android.widget.TextView index=\"0\" package=\"com.abcde.pqrst.gamma\" class=\"android.widget.TextView\" text=\"some, text\" resource-id=\"com.abcde.pqrst.gamma:id/region_name\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[42,1270][933,1330]\" displayed=\"true\" />\n" +
            "                              <android.widget.TextView index=\"1\" package=\"com.abcde.pqrst.gamma\" class=\"android.widget.TextView\" text=\"244 MB\" resource-id=\"com.abcde.pqrst.gamma:id/region_subtext\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[42,1336][933,1389]\" displayed=\"true\" />\n" +
            "                            </android.widget.LinearLayout>\n" +
            "                            <android.widget.LinearLayout index=\"1\" package=\"com.abcde.pqrst.gamma\" class=\"android.widget.LinearLayout\" text=\"\" resource-id=\"com.abcde.pqrst.gamma:id/region_button_holder\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[933,1238][1038,1422]\" displayed=\"true\">\n" +
            "                              <android.widget.ImageButton index=\"0\" package=\"com.abcde.pqrst.gamma\" class=\"android.widget.ImageButton\" text=\"\" content-desc=\"region_download\" resource-id=\"com.abcde.pqrst.gamma:id/region_download\" checkable=\"false\" checked=\"false\" clickable=\"true\" enabled=\"true\" focusable=\"true\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[933,1277][1038,1382]\" displayed=\"true\" />\n" +
            "                            </android.widget.LinearLayout>\n" +
            "                          </android.widget.RelativeLayout>\n" +
            "                          <android.widget.RelativeLayout index=\"5\" package=\"com.abcde.pqrst.gamma\" class=\"android.widget.RelativeLayout\" text=\"\" content-desc=\"Region Cell 7\" resource-id=\"com.abcde.pqrst.gamma:id/region_cell_layout\" checkable=\"false\" checked=\"false\" clickable=\"true\" enabled=\"true\" focusable=\"true\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[0,1425][1080,1609]\" displayed=\"true\">\n" +
            "                            <android.widget.LinearLayout index=\"0\" package=\"com.abcde.pqrst.gamma\" class=\"android.widget.LinearLayout\" text=\"\" resource-id=\"com.abcde.pqrst.gamma:id/region_descriptor_layout\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[42,1425][933,1609]\" displayed=\"true\">\n" +
            "                              <android.widget.TextView index=\"0\" package=\"com.abcde.pqrst.gamma\" class=\"android.widget.TextView\" text=\"Basalt, CO\" resource-id=\"com.abcde.pqrst.gamma:id/region_name\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[42,1457][933,1517]\" displayed=\"true\" />\n" +
            "                              <android.widget.TextView index=\"1\" package=\"com.abcde.pqrst.gamma\" class=\"android.widget.TextView\" text=\"14 MB\" resource-id=\"com.abcde.pqrst.gamma:id/region_subtext\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[42,1523][933,1576]\" displayed=\"true\" />\n" +
            "                            </android.widget.LinearLayout>\n" +
            "                            <android.widget.LinearLayout index=\"1\" package=\"com.abcde.pqrst.gamma\" class=\"android.widget.LinearLayout\" text=\"\" resource-id=\"com.abcde.pqrst.gamma:id/region_button_holder\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[933,1425][1038,1609]\" displayed=\"true\">\n" +
            "                              <android.widget.ImageButton index=\"0\" package=\"com.abcde.pqrst.gamma\" class=\"android.widget.ImageButton\" text=\"\" content-desc=\"region_download\" resource-id=\"com.abcde.pqrst.gamma:id/region_download\" checkable=\"false\" checked=\"false\" clickable=\"true\" enabled=\"true\" focusable=\"true\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[933,1464][1038,1569]\" displayed=\"true\" />\n" +
            "                            </android.widget.LinearLayout>\n" +
            "                          </android.widget.RelativeLayout>\n" +
            "                          <android.widget.RelativeLayout index=\"6\" package=\"com.abcde.pqrst.gamma\" class=\"android.widget.RelativeLayout\" text=\"\" content-desc=\"Region Cell 8\" resource-id=\"com.abcde.pqrst.gamma:id/region_cell_layout\" checkable=\"false\" checked=\"false\" clickable=\"true\" enabled=\"true\" focusable=\"true\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[0,1612][1080,1794]\" displayed=\"true\">\n" +
            "                            <android.widget.LinearLayout index=\"0\" package=\"com.abcde.pqrst.gamma\" class=\"android.widget.LinearLayout\" text=\"\" resource-id=\"com.abcde.pqrst.gamma:id/region_descriptor_layout\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[42,1612][933,1794]\" displayed=\"true\">\n" +
            "                              <android.widget.TextView index=\"0\" package=\"com.abcde.pqrst.gamma\" class=\"android.widget.TextView\" text=\"Baton Rouge, LA\" resource-id=\"com.abcde.pqrst.gamma:id/region_name\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[42,1644][933,1704]\" displayed=\"true\" />\n" +
            "                              <android.widget.TextView index=\"1\" package=\"com.abcde.pqrst.gamma\" class=\"android.widget.TextView\" text=\"97 MB\" resource-id=\"com.abcde.pqrst.gamma:id/region_subtext\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[42,1710][933,1763]\" displayed=\"true\" />\n" +
            "                            </android.widget.LinearLayout>\n" +
            "                            <android.widget.LinearLayout index=\"1\" package=\"com.abcde.pqrst.gamma\" class=\"android.widget.LinearLayout\" text=\"\" resource-id=\"com.abcde.pqrst.gamma:id/region_button_holder\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[933,1612][1038,1794]\" displayed=\"true\">\n" +
            "                              <android.widget.ImageButton index=\"0\" package=\"com.abcde.pqrst.gamma\" class=\"android.widget.ImageButton\" text=\"\" content-desc=\"region_download\" resource-id=\"com.abcde.pqrst.gamma:id/region_download\" checkable=\"false\" checked=\"false\" clickable=\"true\" enabled=\"true\" focusable=\"true\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[933,1651][1038,1756]\" displayed=\"true\" />\n" +
            "                            </android.widget.LinearLayout>\n" +
            "                          </android.widget.RelativeLayout>\n" +
            "                        </androidx.recyclerview.widget.RecyclerView>\n" +
            "                      </android.widget.LinearLayout>\n" +
            "                    </android.widget.FrameLayout>\n" +
            "                  </android.widget.LinearLayout>\n" +
            "                </android.view.ViewGroup>\n" +
            "              </androidx.drawerlayout.widget.DrawerLayout>\n" +
            "            </android.widget.FrameLayout>\n" +
            "          </android.widget.FrameLayout>\n" +
            "        </android.widget.LinearLayout>\n" +
            "      </android.widget.FrameLayout>\n" +
            "    </android.widget.LinearLayout>\n" +
            "  </android.widget.FrameLayout>\n" +
            "</hierarchy>";



    private static Document loadDocument(String str) {
        DocumentBuilderFactory factory = new DocumentBuilderFactoryImpl();
        factory.setNamespaceAware(true);
        factory.setValidating(false);
        try (InputStream in = new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8))) {
            return factory.newDocumentBuilder().parse(in);
        } catch (SAXException | ParserConfigurationException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Node> findNodesUsingXpath2(String xml, String xpathSelector,
                                                   boolean multiple) {
        StaticContextBuilder scb = new StaticContextBuilder();
        final XPath2Expression expr;
        try {
            expr = new Engine().parseExpression(xpathSelector, scb);
        } catch (XPathParserException e) {
            throw new InvalidSelectorException(e);
        }

        try {
            Document doc = loadDocument(xml);
            ResultSequence rs = expr.evaluate(new DynamicContextBuilder(scb), new Object[] { doc });
            Iterator<Item> iterator = rs.iterator();
            List<Node> result = new ArrayList<>();
            final long timeStarted = SystemClock.uptimeMillis();
            while (iterator.hasNext()) {
                Item item = iterator.next();
                if (!(item.getNativeValue() instanceof Node)) {
                    continue;
                }
                Node node = (Node) item.getNativeValue();
                if (node.getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }

                result.add(node);
                if (!multiple) {
                    break;
                }
            }
            Logger.info(String.format("Took %sms to retrieve %s matches for '%s' XPath query",
                    SystemClock.uptimeMillis() - timeStarted, result.size(), xpathSelector));
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Element> findNodesUsingXpath1(String xml, String xpathSelector,
                                                      boolean multiple) {
        final XPathExpression expression;
        try {
            expression = XPathFactory.newInstance().newXPath().compile(xpathSelector);
        } catch (XPathExpressionException e) {
            throw new InvalidSelectorException(e);
        }

        try {
            NodeList elements = (NodeList) expression.evaluate(
                    loadDocument(xml), XPathConstants.NODESET
            );
            List<Element> result = new ArrayList<>();
            for (int i = 0; i < elements.getLength(); ++i) {
                Element item = (Element) elements.item(i);
                result.add(item);
                if (!multiple) {
                    break;
                }
            }
             return result;
        } catch (XPathExpressionException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void parsesComplexXpath1() {
        String query = "(//android.widget.TextView[@text='some, text']/following::android.widget.ImageButton)[1]";
        List<Element> nodes = findNodesUsingXpath1(XML, query, true);
        assertEquals(nodes.size(), 1);
    }

    @Test
    @Ignore("Unignore this test as soon as https://bugs.eclipse.org/bugs/show_bug.cgi?id=578021" +
            "gets resolved")
    public void parsesComplexXpath2() {
        String query = "(//android.widget.TextView[@text='some, text']/following::android.widget.ImageButton)[1]";

        List<Node> nodes = findNodesUsingXpath2(XML, query, true);
        assertEquals(nodes.size(), 1);
    }

    @Test
    public void parsesXpath2UsingIcu() {
        String query = "//android.widget.TextView[substring(@text, 1) = 'some, text']";
        List<Node> nodes = findNodesUsingXpath2(XML, query, false);
        assertEquals(nodes.size(), 1);
    }
}
