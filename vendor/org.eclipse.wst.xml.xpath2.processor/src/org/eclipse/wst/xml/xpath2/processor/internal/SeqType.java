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
 *     Jesper Steen Moeller - bug 285145 - don't silently allow empty sequences always
 *     Jesper Steen Moeller - bug 297707 - Missing the empty-sequence() type
 *     David Carver - bug 298267 - Correctly handle instof with elements.
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal;

import java.util.Iterator;
import java.util.Map;

import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.api.StaticContext;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.ItemType;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.KindTest;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.SequenceType;
import org.eclipse.wst.xml.xpath2.processor.internal.function.ConstructorFL;
import org.eclipse.wst.xml.xpath2.processor.internal.function.FunctionLibrary;
import org.eclipse.wst.xml.xpath2.processor.internal.types.AnyAtomicType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.AnyType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.NodeType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.QName;
import org.w3c.dom.Node;

/**
 * represents a Sequence types used for matching expected arguments of functions
 */
public class SeqType {

	public static final int OCC_NONE = 0;
	public static final int OCC_STAR = 1;
	public static final int OCC_PLUS = 2;
	public static final int OCC_QMARK = 3;
	public static final int OCC_EMPTY = 4;

	/**
	 * Path to w3.org XML Schema specification.
	 */
	public static final String XML_SCHEMA_NS = "http://www.w3.org/2001/XMLSchema";

	private static final QName ANY_ATOMIC_TYPE = new QName("xs",
			"anyAtomicType", XML_SCHEMA_NS);

	private transient AnyType anytype = null;
	private transient int occ;
	private transient Class typeClass = null;
	private transient QName nodeName = null;
	private transient boolean wild = false;

	/**
	 * sequence type
	 * 
	 * @param t
	 *            is any type
	 * @param occ
	 *            is an integer in the sequence.
	 */
	public SeqType(AnyType t, int occ) {
		anytype = t;
		this.occ = occ;

		if (t != null)
			typeClass = t.getClass();
		else
			typeClass = null;
	}

	/**
	 * @param occ
	 *            is an integer in the sequence.
	 */
	// XXX hack to represent AnyNode...
	public SeqType(int occ) {
		this((AnyType) null, occ);

		typeClass = NodeType.class;
	}

	/**
	 * @param type_class
	 *            is a class which represents the expected type
	 * @param occ
	 *            is an integer in the sequence.
	 */
	public SeqType(Class type_class, int occ) {
		this((AnyType) null, occ);

		this.typeClass = type_class;
	}

	/**
	 * @param st
	 *            is a sequence type.
	 * @param sc
	 *            is a static context.
	 */
	public SeqType(SequenceType st, StaticContext sc, ResultSequence rs) {

		occ = mapSequenceTypeOccurrence(st.occurrence());
		// figure out the item is
		final ItemType item = st.item_type();
		KindTest ktest = null;
		switch (item.type()) {
		case ItemType.ITEM:
			typeClass = AnyType.class;
			return;

			// XXX IMPLEMENT THIS
		case ItemType.QNAME:
			final AnyAtomicType aat = make_atomic(sc, item.qname());

			assert aat != null;
			anytype = aat;
			if (item.qname().equals(ANY_ATOMIC_TYPE)) {
				typeClass = AnyAtomicType.class;
			} else {
				typeClass = anytype.getClass();
			}
			return;

		case ItemType.KINDTEST:
			ktest = item.kind_test();
			break;

		}

		if (ktest == null) {
			return;
		}

		typeClass = ktest.getXDMClassType();
		anytype = ktest.createTestType(rs, sc);
		nodeName = ktest.name();
		wild = ktest.isWild();
	}

	private AnyAtomicType make_atomic(StaticContext sc, QName qname) {
		String ns = qname.namespace();

		Map functionLibraries = sc.getFunctionLibraries();
		if (!functionLibraries.containsKey(ns))
			return null;

		FunctionLibrary fl = (FunctionLibrary) functionLibraries.get(ns);

		if (!(fl instanceof ConstructorFL))
			return null;

		ConstructorFL cfl = (ConstructorFL) fl;

		return cfl.atomic_type(qname);
	}

	public static int mapSequenceTypeOccurrence(int occurrence) {
		// convert occurrence
		switch (occurrence) {
		case SequenceType.EMPTY:
			return OCC_EMPTY;

		case SequenceType.NONE:
			return OCC_NONE;

		case SequenceType.QUESTION:
			return OCC_QMARK;

		case SequenceType.STAR:
			return OCC_STAR;

		case SequenceType.PLUS:
			return OCC_PLUS;

		default:
			assert false;
			return 0;
		}
	}

	/**
	 * @param t
	 *            is an any type.
	 */
	public SeqType(AnyType t) {
		this(t, OCC_NONE);
	}

	/**
	 * @return an integer.
	 */
	public int occurence() {
		return occ;
	}

	/**
	 * @return a type.
	 */
	public AnyType type() {
		return anytype;
	}

	/**
	 * matches args
	 * 
	 * @param args
	 *            is a result sequence
	 * @throws a
	 *             dynamic error
	 * @return a result sequence
	 */
	public ResultSequence match(ResultSequence args) throws DynamicError {

		int occurrence = occurence();

		// Check for empty sequence first
		if (occurrence == OCC_EMPTY && !args.empty()) {
			throw new DynamicError(TypeError.invalid_type(null));
		}

		int arg_count = 0;

		for (Iterator i = args.iterator(); i.hasNext();) {
			AnyType arg = (AnyType) i.next();

			// make sure all args are the same type as expected type
			if (!(typeClass.isInstance(arg))) {
				throw new DynamicError(TypeError.invalid_type(null));
			}

			if (anytype != null) {
				if ((nodeName != null || wild) && arg instanceof NodeType) {
					NodeType nodeType = (NodeType) arg;
					Node node = nodeType.node_value();
					Node lnode = ((NodeType) anytype).node_value();
					if (lnode == null) {
						//throw new DynamicError(TypeError.invalid_type(null));
						continue;
					}
					if (!lnode.isEqualNode(node)) {
						//throw new DynamicError(TypeError.invalid_type(null));
						continue;
					}
				}
			}

			arg_count++;

		}

		switch (occurrence) {
		case OCC_NONE:
			if (arg_count != 1) {
				throw new DynamicError(TypeError.invalid_type(null));
			}
			break;

		case OCC_PLUS:
			if (arg_count == 0) {
				throw new DynamicError(TypeError.invalid_type(null));
			}
			break;

		case OCC_STAR:
			break;

		case OCC_QMARK:
			if (arg_count > 1) {
				throw new DynamicError(TypeError.invalid_type(null));
			}
			break;

		default:
			assert false;

		}

		return args;
	}
}
