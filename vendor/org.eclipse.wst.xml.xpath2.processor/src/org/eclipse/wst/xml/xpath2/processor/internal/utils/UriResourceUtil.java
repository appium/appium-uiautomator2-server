/*******************************************************************************
 * Copyright (c) 2025 Appium Contributors.
 * SPDX-License-Identifier: Apache-2.0
 *******************************************************************************/
package org.eclipse.wst.xml.xpath2.processor.internal.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Resolves and opens document URIs, including W3C test-suite paths served from the classpath.
 */
public final class UriResourceUtil {

	private UriResourceUtil() {
	}

	public static URI resolve(String uri, URI baseUri) {
		if (uri == null) {
			return null;
		}
		try {
			URI candidate = URI.create(uri);
			if (candidate.isAbsolute()) {
				return candidate;
			}
			URI classpathUri = resolveClasspath(uri);
			if (classpathUri != null) {
				return classpathUri;
			}
			if (baseUri != null) {
				return baseUri.resolve(uri);
			}
			return candidate;
		} catch (IllegalArgumentException ex) {
			return null;
		}
	}

	public static InputStream openStream(URI uri) throws IOException {
		if (uri == null) {
			throw new IOException("URI is null");
		}
		try {
			return new URL(uri.toString()).openStream();
		} catch (MalformedURLException ex) {
			URI classpathUri = resolveClasspath(uri.toString());
			if (classpathUri != null) {
				return new URL(classpathUri.toString()).openStream();
			}
			throw new IOException(ex);
		}
	}

	private static URI resolveClasspath(String uri) {
		String resourcePath = uri.startsWith("/") ? uri.substring(1) : uri;
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		if (loader == null) {
			loader = UriResourceUtil.class.getClassLoader();
		}
		URL resource = loader.getResource(resourcePath);
		if (resource == null && !resourcePath.equals(uri)) {
			resource = loader.getResource(uri);
		}
		if (resource == null) {
			return null;
		}
		try {
			return resource.toURI();
		} catch (URISyntaxException ex) {
			return null;
		}
	}
}
