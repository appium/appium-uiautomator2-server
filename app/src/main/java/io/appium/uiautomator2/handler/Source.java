/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.appium.uiautomator2.handler;

import org.w3c.dom.Document;

import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;
import io.appium.uiautomator2.core.AccessibilityNodeInfoDumper;
import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.server.WDStatus;
import io.appium.uiautomator2.utils.Logger;

import static io.appium.uiautomator2.utils.AXWindowHelpers.refreshRootAXNode;

/**
 * Get page source. Return as string of XML doc
 */
public class Source extends SafeRequestHandler {

    public Source(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) {
        refreshRootAXNode();
        try {
            final Document doc = AccessibilityNodeInfoDumper.asXmlDocument();
            final TransformerFactory tf = TransformerFactory.newInstance();
            final Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            final StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));
            return new AppiumResponse(getSessionId(request), WDStatus.SUCCESS, writer.getBuffer().toString());
        } catch (final TransformerConfigurationException e) {
            Logger.error("Unable to handle the request:" + e);
            return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR, "Something went terribly wrong while converting xml document to string:" + e);
        } catch (final TransformerException e) {
            Logger.error("Unable to handle the request:" + e);
            return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR, "Could not parse xml hierarchy to string: " + e);
        } catch (UiAutomator2Exception e) {
            Logger.error("Exception while performing getSource action: ", e);
            return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR, e);
        }

    }
}
