/*******************************************************************************
 * Copyright (c) 2009, 2011 Standards for Technology in Automotive Retail and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     David Carver - initial API and implementation from the PsychoPath XPath 2.0 
 *     Mukul Gandhi - bug 280798 - PsychoPath support for JDK 1.4
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal.function;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.wst.xml.xpath2.api.EvaluationContext;
import org.eclipse.wst.xml.xpath2.api.Item;
import org.eclipse.wst.xml.xpath2.api.ResultBuffer;
import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.internal.SeqType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.QName;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSAnyURI;

/**
 * The purpose of this function is to enable a relative URI to be resolved
 * against an absolute URI. The first form of this function resolves $relative
 * against the value of the base-uri property from the static context. If the
 * base-uri property is not initialized in the static context an error is raised
 * [err:FONS0005].
 * 
 * If $relative is a relative URI reference, it is resolved against $base, or
 * the base-uri property from the static context, using an algorithm such as the
 * ones described in [RFC 2396] or [RFC 3986], and the resulting absolute URI
 * reference is returned. An error may be raised [err:FORG0009] in the
 * resolution process. If $relative is an absolute URI reference, it is returned
 * unchanged. If $relative or $base is not a valid xs:anyURI an error is raised
 * [err:FORG0002]. If $relative is the empty sequence, the empty sequence is
 * returned.
 */
public class FnResolveURI extends Function {
	private static Collection _expected_args = null;

	/**
	 * Constructor for FnBaseUri.
	 */
	public FnResolveURI() {
		super(new QName("resolve-uri"), 1, 2);
	}

	/**
	 * Evaluate arguments.
	 * 
	 * @param args
	 *            argument expressions.
	 * @throws DynamicError
	 *             Dynamic error.
	 * @return Result of evaluation.
	 */
	public ResultSequence evaluate(Collection args, EvaluationContext ec) throws DynamicError {
		return resolveURI(args, ec);
	}

	/**
	 * Resolve-URI operation.
	 * 
	 * @param args
	 *            Result from the expressions evaluation.
	 * @param d_context
	 *            Dynamic context
	 * @throws DynamicError
	 *             Dynamic error.
	 * @return Result of fn:resolve-uri operation.
	 */
	public static ResultSequence resolveURI(Collection args,
			EvaluationContext ec) throws DynamicError {
		if (ec.getStaticContext().getBaseUri() == null) {
			throw DynamicError.noBaseURI();
		}
		
		Collection cargs = args;
		Iterator argit = cargs.iterator();
		ResultSequence relativeRS = (ResultSequence) argit.next();
		ResultSequence baseUriRS = null;
		if (argit.hasNext()) {
			baseUriRS = (ResultSequence) argit.next();
		}

		if (relativeRS.empty()) {
			return ResultBuffer.EMPTY;
		}

		Item relativeURI = relativeRS.first();
		String resolvedURI = null;
				
		if (baseUriRS == null) {
			resolvedURI = resolveURI(ec.getStaticContext().getBaseUri().toString(), relativeURI.getStringValue());
		} else {
			Item baseURI = baseUriRS.first();
			resolvedURI = resolveURI(baseURI.getStringValue(), relativeURI.getStringValue());
		}

		return new XSAnyURI(resolvedURI);
	}

	private static String resolveURI(String base, String relative) throws DynamicError {
		String resolved = null;
		try {
			URI baseURI = new URI(base);
			resolved = baseURI.resolve(relative).toString();
		} catch (Exception ex) {
			throw DynamicError.errorResolvingURI();
		}
		return resolved;
	}
	

	/**
	 * Obtain a list of expected arguments.
	 * 
	 * @return Result of operation.
	 */
	public synchronized static Collection expected_args() {
		if (_expected_args == null) {
			_expected_args = new ArrayList();
			_expected_args.add(new SeqType(SeqType.OCC_QMARK));
			_expected_args.add(new SeqType(SeqType.OCC_NONE));
		}

		return _expected_args;
	}
}
