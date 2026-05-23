/*******************************************************************************
 * Copyright (c) 2005, 2011 Andrea Bittau, University College London, and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Andrea Bittau - initial API and implementation from the PsychoPath XPath 2.0 
 *     Jesper Moller - bug 280555 - Add pluggable collation support
 *     Mukul Gandhi - bug 280798 - PsychoPath support for JDK 1.4
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal.function;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.wst.xml.xpath2.api.DynamicContext;
import org.eclipse.wst.xml.xpath2.api.EvaluationContext;
import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.ResultSequenceFactory;
import org.eclipse.wst.xml.xpath2.processor.internal.types.AnyAtomicType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.AnyType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.NodeType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.NumericType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.QName;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSBoolean;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSString;
import org.w3c.dom.Node;

/**
 * The function assesses whether two sequences are deep-equal to each other. To
 * be deep-equal, they must contain items that are pairwise deep-equal; and for
 * two items to be deep-equal, they must either by atomic values that compare
 * equal, or nodes of the same kind, with the same name, whose children are
 * deep-equal. This is defined in more detail below. The $collation argument
 * identifies a collation which is used at all levels of recursion when strings
 * are compared (but not when names are compared), according to the rules in
 * 7.3.1 Collations in the specification.
 */
public class FnDeepEqual extends AbstractCollationEqualFunction {
	/**
	 * Constructor for FnDeepEqual.
	 */
	public FnDeepEqual() {
		super(new QName("deep-equal"), 2, 3);
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
		return deep_equal(args, ec);
	}

	/**
	 * Deep-Equal expression operation.
	 * 
	 * @param args
	 *            Result from the expressions evaluation.
	 * @param context
	 *            Dynamic context
	 * @throws DynamicError
	 *             Dynamic error.
	 * @return Result of fn:deep-equal operation.
	 */
	public static ResultSequence deep_equal(Collection args, EvaluationContext context)
			throws DynamicError {

		// get args
		Iterator citer = args.iterator();
		ResultSequence arg1 = (ResultSequence) citer.next();
		ResultSequence arg2 = (ResultSequence) citer.next();
		ResultSequence arg3 = null;
		String collationURI = context.getStaticContext().getCollationProvider().getDefaultCollation();
		if (citer.hasNext()) {
			arg3 = (ResultSequence) citer.next();
			if (!arg3.empty()) {
				collationURI = arg3.first().getStringValue();
			}
		}

		boolean result = deep_equal(arg1, arg2, context, collationURI);

		return ResultSequenceFactory.create_new(new XSBoolean(result));
	}

	/**
	 * Deep-Equal boolean operation.
	 * 
	 * @param one
	 *            input1 xpath expression/variable.
	 * @param two
	 *            input2 xpath expression/variable.
	 * @param context
	 *            Current dynamic context 
	 * @return Result of fn:deep-equal operation.
	 */
	public static boolean deep_equal(ResultSequence one, ResultSequence two, EvaluationContext context, String collationURI) {
		if (one.empty() && two.empty())
			return true;

		if (one.size() != two.size())
			return false;

		Iterator onei = one.iterator();
		Iterator twoi = two.iterator();

		while (onei.hasNext()) {
			AnyType a = (AnyType) onei.next();
			AnyType b = (AnyType) twoi.next();

			if (!deep_equal(a, b, context, collationURI))
				return false;
		}
		return true;
	}

	/**
	 * Deep-Equal boolean operation for inputs of any type.
	 * 
	 * @param one
	 *            input1 xpath expression/variable.
	 * @param two
	 *            input2 xpath expression/variable.
	 * @param context 
	 * @return Result of fn:deep-equal operation.
	 */
	public static boolean deep_equal(AnyType one, AnyType two, EvaluationContext context, String collationURI) {
		if ((one instanceof AnyAtomicType) && (two instanceof AnyAtomicType))
			return deep_equal_atomic((AnyAtomicType) one, (AnyAtomicType) two, context.getDynamicContext(), collationURI);

		else if (((one instanceof AnyAtomicType) && (two instanceof NodeType))
				|| ((one instanceof NodeType) && (two instanceof AnyAtomicType)))
			return false;
		else if ((one instanceof NodeType) && (two instanceof NodeType))
			return deep_equal_node((NodeType) one, (NodeType) two, context);
		else {
			return false;
		}
	}

	/**
	 * Deep-Equal boolean operation for inputs of any atomic type.
	 * 
	 * @param one
	 *            input1 xpath expression/variable.
	 * @param two
	 *            input2 xpath expression/variable.
	 * @return Result of fn:deep-equal operation.
	 */
	public static boolean deep_equal_atomic(AnyAtomicType one, AnyAtomicType two, DynamicContext context, String collationURI) {
		if (!(one instanceof CmpEq))
			return false;
		if (!(two instanceof CmpEq))
			return false;

		CmpEq a = (CmpEq) one;

		try {
			if (isNumeric(one, two)) {
				NumericType numeric = (NumericType) one;
				if (numeric.eq(two, context)) {
					return true;
				} else {
					XSString value1 = new XSString(one.getStringValue());
					if (value1.eq(two, context)) {
						return true;
					}
				}
			}

			if (a.eq(two, context))
				return true;
			
			if (needsStringComparison(one, two)) {
				XSString xstr1 = new XSString(one.getStringValue());
				XSString xstr2 = new XSString(two.getStringValue());
				if (FnCompare.compare_string(collationURI, xstr1, xstr2,
						context).equals(BigInteger.ZERO)) {
					return true;
				}
			}
			return false;
		} catch (DynamicError err) {
			return false; // XXX ???
		}
	}

	/**
	 * Deep-Equal boolean operation for inputs of node type.
	 * 
	 * @param one
	 *            input1 xpath expression/variable.
	 * @param two
	 *            input2 xpath expression/variable.
	 * @return Result of fn:deep-equal operation.
	 */
	public static boolean deep_equal_node(NodeType one, NodeType two, EvaluationContext context) {
		Node a = one.node_value();
		Node b = two.node_value();
		
		if (a.isEqualNode(b)) {
			return true;
		}

		return false;
	}
}
