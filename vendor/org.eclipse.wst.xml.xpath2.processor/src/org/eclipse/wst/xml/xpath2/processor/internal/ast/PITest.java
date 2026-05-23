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
 *     David Carver - bug 298535 - Attribute instance of improvements 
 *     Mukul Gandhi - bug 280798 - PsychoPath support for JDK 1.4
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal.ast;

import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.api.StaticContext;
import org.eclipse.wst.xml.xpath2.processor.internal.types.AnyType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.PIType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.QName;

/**
 * Class for Processing Instruction support.
 */
public class PITest extends KindTest {
	private String _arg;

	/**
	 * Constructor for PITest.
	 * 
	 * @param arg
	 *            instruction argument.
	 */
	public PITest(String arg) {
		_arg = arg;
	}

	/**
	 * Default Constructor for PITest.
	 */
	public PITest() {
		this(null);
	}

	/**
	 * Support for Visitor interface.
	 * 
	 * @return Result of Visitor operation.
	 */
	public Object accept(XPathVisitor v) {
		return v.visit(this);
	}

	/**
	 * Support for String arguments.
	 * 
	 * @return Result of String operation.
	 */
	public String arg() {
		return _arg;
	}

	public AnyType createTestType(ResultSequence rs, StaticContext sc) {
		// TODO Auto-generated method stub
		return null;
	}

	public QName name() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isWild() {
		return false;
	}

	public Class getXDMClassType() {
		return PIType.class;
	}

}
