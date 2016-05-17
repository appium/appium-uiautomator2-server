package io.appium.uiautomator2.handler;

import org.w3c.dom.Document;

import java.io.StringWriter;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.server.WDStatus;
import io.appium.uiautomator2.utils.Logger;
import io.appium.uiautomator2.utils.ReflectionUtils;
import io.appium.uiautomator2.utils.XMLHierarchy;

/**
 * Get page source. Return as string of XML doc
 */
public class Source extends BaseRequestHandler {

    public Source(String mappedUri) {
        super(mappedUri);
    }

    @Override
    public AppiumResponse handle(IHttpRequest request) {
        ReflectionUtils.clearAccessibilityCache();

        final Document doc = (Document) XMLHierarchy.getFormattedXMLDoc();

        final TransformerFactory tf = TransformerFactory.newInstance();
        final StringWriter writer = new StringWriter();
        Transformer transformer;
        String xmlString;
        try {
            transformer = tf.newTransformer();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));
            xmlString = writer.getBuffer().toString();
            return new AppiumResponse(getSessionId(request), WDStatus.SUCCESS, xmlString);
        } catch (final TransformerConfigurationException e) {
            Logger.error("Unable to handle the request:" + e);
            throw new RuntimeException("Something went terribly wrong while converting xml document to string", e);
        } catch (final TransformerException e) {
            return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR, "Could not parse xml hierarchy to string: " + e);
        }

    }
}
