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
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal.function;

// this is the equivalent of libc =D
/**
 * Maintains a library of built-in operators as functions.
 * 
 * This is necessary if normalization is being used.
 */
public class OpFunctionLibrary extends FunctionLibraryImpl {

	// XXX should be internal
	public static final String XPATH_OP_NS = "http://www.w3.org/TR/2003/WD-xquery-semantics-20030502/";

	/**
	 * Constructor for OpFunctionLibrary.
	 */
	public OpFunctionLibrary() {
		super(XPATH_OP_NS);

		// operators according to formal semantics
		addFunction(new FsDiv());
		addFunction(new FsEq());
		addFunction(new FsGe());
		addFunction(new FsGt());
		addFunction(new FsIDiv());
		addFunction(new FsLe());
		addFunction(new FsLt());
		addFunction(new FsMinus());
		addFunction(new FsMod());
		addFunction(new FsNe());
		addFunction(new FsPlus());
		addFunction(new FsTimes());

		// utility functions in formal semantics
		addFunction(new FsConvertOperand());

		// operators according to functions & operators
		addFunction(new OpExcept());
		addFunction(new OpIntersect());
		addFunction(new OpTo());
		addFunction(new OpUnion());
	}
}
