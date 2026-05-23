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
 *     David Carver  - initial API and implementation
 *     Mukul Gandhi - bug 280798 - PsychoPath support for JDK 1.4
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal.function;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.XMLConstants;

import org.eclipse.wst.xml.xpath2.api.DynamicContext;
import org.eclipse.wst.xml.xpath2.api.EvaluationContext;
import org.eclipse.wst.xml.xpath2.api.Item;
import org.eclipse.wst.xml.xpath2.api.ResultBuffer;
import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.internal.SeqType;
import org.eclipse.wst.xml.xpath2.processor.internal.TypeError;
import org.eclipse.wst.xml.xpath2.processor.internal.types.ElementType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.QName;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSString;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 * Returns the in-scope-prefixes for the element and any of it's ancestors.
 */
public class FnInScopePrefixes extends Function {
	private static Collection _expected_args = null;

	/**
	 * Constructor for FnPrefixFromQName
	 */
	public FnInScopePrefixes() {
		super(new QName("in-scope-prefixes"), 1);
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
		return inScopePrefixes(args, ec.getDynamicContext());
	}

	/**
	 * Prefix-from-QName operation.
	 * 
	 * @param args
	 *            Result from the expressions evaluation.
	 * @throws DynamicError
	 *             Dynamic error.
	 * @return Result of fn:prefix-from-QName operation.
	 */
	public static ResultSequence inScopePrefixes(Collection args, DynamicContext dc) throws DynamicError {

//		Collection cargs = Function.convert_arguments(args, expected_args());
		Collection cargs = args;

		ResultSequence arg1 = (ResultSequence) cargs.iterator().next();

		if (arg1.empty())
		  return ResultBuffer.EMPTY;

		ResultBuffer rs = new ResultBuffer();
		
		Item anytype = arg1.item(0);
		if (!(anytype instanceof ElementType)) {
			throw new DynamicError(TypeError.invalid_type(null));
		}

		ElementType element = (ElementType) anytype;
		List prefixList = lookupPrefixes(element);
		createPrefixResultSet(rs, prefixList);
		return rs.getSequence();
	}

	private static void createPrefixResultSet(ResultBuffer rs, List prefixList) {
		for (int i = 0; i < prefixList.size(); i++) {
			String prefix = (String) prefixList.get(i);
			rs.add(new XSString(prefix));
		}
	}

	private static List lookupPrefixes(ElementType element) {
		Element domElm = (Element) element.node_value();
		
		List prefixList = new ArrayList();
		Node node = domElm;
		
		while (node != null && node.getNodeType() != Node.DOCUMENT_NODE) {
			NamedNodeMap attrs = node.getAttributes();
			for (int i = 0; i < attrs.getLength(); i++) {
				Node attr = attrs.item(i);
				String prefix = null;
				if (attr.getNamespaceURI() != null &&
					attr.getNamespaceURI().equals(XMLConstants.XMLNS_ATTRIBUTE_NS_URI)) {
					// Default Namespace
					if (attr.getNodeName().equals(XMLConstants.XMLNS_ATTRIBUTE)) {
						prefix = "";
					} else {
						// Should we check the namespace in the Dynamic Context and return that???
						prefix = attr.getLocalName();
					}
					if (prefix != null) {
						if (!prefixList.contains(prefix)) {
							prefixList.add(prefix);
						}
					}
				}
			}
			
			node = node.getParentNode();
		}
		return prefixList;
	}
	
	/**
	 * Obtain a list of expected arguments.
	 * 
	 * @return Result of operation.
	 */
	public synchronized static Collection expected_args() {
		if (_expected_args == null) {
			_expected_args = new ArrayList();
			SeqType arg = new SeqType(new ElementType(), SeqType.OCC_PLUS);
			_expected_args.add(arg);
		}

		return _expected_args;
	}
}
