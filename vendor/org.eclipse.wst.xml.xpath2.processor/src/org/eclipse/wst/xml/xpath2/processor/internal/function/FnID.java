/*******************************************************************************
 * Copyright (c) 2009, 2011 Standard for Technology in Automotive Retail, and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * 	   David Carver (STAR) - bug 281168 - initial API and implementation
 *     David Carver  - bug 281186 - implementation of fn:id and fn:idref
 *     Mukul Gandhi - bug 280798 - PsychoPath support for JDK 1.4
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal.function;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.wst.xml.xpath2.api.EvaluationContext;
import org.eclipse.wst.xml.xpath2.api.ResultBuffer;
import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.internal.SeqType;
import org.eclipse.wst.xml.xpath2.processor.internal.TypeError;
import org.eclipse.wst.xml.xpath2.processor.internal.types.AttrType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.ElementType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.NodeType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.QName;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSIDREF;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSString;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Returns the sequence of element nodes that have an ID value matching the value of one
 * or more of the IDREF values supplied in $arg .
 */
public class FnID extends Function {
	private static Collection _expected_args = null;
	
	/**
	 * Constructor for FnInsertBefore.
	 */
	public FnID() {
		super(new QName("id"), 1, 2);
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
		return id(args, ec);
	}

	/**
	 * Insert-Before operation.
	 * 
	 * @param args
	 *            Result from the expressions evaluation.
	 * @throws DynamicError
	 *             Dynamic error.
	 * @return Result of fn:insert-before operation.
	 */
	public static ResultSequence id(Collection args, EvaluationContext context) throws DynamicError {
		Collection cargs = Function.convert_arguments(args, expected_args());

		ResultBuffer rs = new ResultBuffer();
		
		Iterator argIt = cargs.iterator();
		ResultSequence idrefRS = (ResultSequence) argIt.next();
		String[] idrefst = idrefRS.first().getStringValue().split(" ");

		ArrayList idrefs = createIDRefs(idrefst);
		ResultSequence nodeArg = null;
		NodeType nodeType = null;
		if (argIt.hasNext()) {
			nodeArg = (ResultSequence) argIt.next();
			nodeType = (NodeType)nodeArg.first();
		} else {
			if (context.getContextItem() == null) {
				throw DynamicError.contextUndefined();
			}
			if (!(context.getContextItem() instanceof NodeType)) {
				throw new DynamicError(TypeError.invalid_type(null));
			}
			nodeType = (NodeType) context.getContextItem();
			if (nodeType.node_value().getOwnerDocument() == null) {
				throw DynamicError.contextUndefined();
			}
		}
		
		Node node = nodeType.node_value();
		if (node.getOwnerDocument() == null) {
			// W3C Test suite seems to want XPDY0002
			throw DynamicError.contextUndefined();
			//throw DynamicError.noContextDoc();
		}
		
		if (hasIDREF(idrefs, node)) {
			ElementType element = new ElementType((Element) node, context.getStaticContext().getTypeModel());
			rs.add(element);
		}
		
		processAttributes(node, idrefs, rs, context);
		processChildNodes(node, idrefs, rs, context);

		return rs.getSequence();
	}
	
	private static ArrayList createIDRefs(String[] idReftokens) {
		ArrayList xsidRef = new ArrayList();
		for (int i = 0; i < idReftokens.length; i++) {
			XSIDREF idref = new XSIDREF(idReftokens[i]);
			xsidRef.add(idref);
		}
		return xsidRef;
	}
	
	private static void processChildNodes(Node node, List idrefs, ResultBuffer rs, EvaluationContext context) {
		if (!node.hasChildNodes()) {
			return;
		}
		
		NodeList nodeList = node.getChildNodes();
		for (int nodecnt = 0; nodecnt < nodeList.getLength(); nodecnt++) {
			Node childNode = nodeList.item(nodecnt);
			if (childNode.getNodeType() == Node.ELEMENT_NODE && !isDuplicate(childNode, rs)) {
				ElementType element = new ElementType((Element)childNode, context.getStaticContext().getTypeModel());
				if (element.isID()) {
					if (hasIDREF(idrefs, childNode)) {
						rs.add(element);
					}
				} 
				processAttributes(childNode, idrefs, rs, context);
				processChildNodes(childNode, idrefs, rs, context);
			}
		}

	}
	
	private static void processAttributes(Node node, List idrefs, ResultBuffer rs, EvaluationContext context) {
		if (!node.hasAttributes()) {
			return;
		}
		
		NamedNodeMap attributeList = node.getAttributes();
		for (int atsub = 0; atsub < attributeList.getLength(); atsub++) {
			Attr atNode = (Attr) attributeList.item(atsub);
			NodeType atType = new AttrType(atNode, context.getStaticContext().getTypeModel());
			if (atType.isID()) {
				if (hasIDREF(idrefs, atNode)) {
					if (!isDuplicate(node, rs)) {
						ElementType element = new ElementType((Element)node, context.getStaticContext().getTypeModel());
						rs.add(element);
					}
				}
			}
		}
	}
	
	private static boolean hasIDREF(List idrefs, Node node) {
		for (int i = 0; i < idrefs.size(); i++) {
			XSIDREF idref = (XSIDREF) idrefs.get(i);
			if (idref.getStringValue().equals(node.getNodeValue())) {
				return true;
			}
		}
		return false;
	}
	
	private static boolean isDuplicate(Node node, ResultBuffer rs) {
		Iterator it = rs.iterator();
		while (it.hasNext()) {
			if (it.next().equals(node)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Obtain a list of expected arguments.
	 * 
	 * @return Result of operation.
	 */
	public synchronized static Collection expected_args() {
		if (_expected_args == null) {
			_expected_args = new ArrayList();
			SeqType arg = new SeqType(new XSString(), SeqType.OCC_STAR);
			_expected_args.add(arg);
			_expected_args.add(new SeqType(SeqType.OCC_NONE));
		}

		return _expected_args;
	}
	
}
