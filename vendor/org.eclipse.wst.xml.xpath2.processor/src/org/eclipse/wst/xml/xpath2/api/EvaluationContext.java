/*******************************************************************************
 * Copyright (c) 2011 Jesper Moller, and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Jesper Moller - initial API and implementation
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.api;

/**
 *
 * @noimplement This interface is not intended to be implemented by clients.
 * @since 2.0
 */
public interface EvaluationContext {
	/**
	 * Definition: The context item is the item currently being processed. An
	 * item is either an atomic value or a node.
	 * Definition: When the context
	 * item is a node, it can also be referred to as the context node.
	 * 
	 * The
	 * context item is returned by an expression consisting of a single dot
	 * (.). When an expression E1/E2 or E1[E2] is evaluated, each item in the
	 * sequence obtained by evaluating E1 becomes the context item in the
	 * inner focus for an evaluation of E2.
	 */
	Item getContextItem();

	/**
	 * [Definition: The context position
	 * is the position of the context item within the sequence of items
	 * currently being processed.] It changes whenever the context item
	 * changes. When the focus is defined, the value of the context position
	 * is an integer greater than zero. The context position is returned by
	 * the expression fn:position(). When an expression E1/E2 or E1[E2] is
	 * evaluated, the context position in the inner focus for an evaluation of
	 * E2 is the position of the context item in the sequence obtained by
	 * evaluating E1. The position of the first item in a sequence is always 1
	 * (one). The context position is always less than or equal to the context
	 * size.
	 */
	int getContextPosition();
	
	/**
	 * [Definition: The context size is the number of items in the
	 * sequence of items currently being processed.] Its value is always an
	 * integer greater than zero. The context size is returned by the
	 * expression fn:last(). When an expression E1/E2 or E1[E2] is evaluated,
	 * the context size in the inner focus for an evaluation of E2 is the
	 * number of items in the sequence obtained by evaluating E1. [Definition:
	 * Variable values. This is a set of (expanded QName, value) pairs. It
	 * contains the same expanded QNames as the in-scope variables in the
	 * static context for the expression. The expanded QName is the name of
	 * the variable and the value is the dynamic value of the variable, which
	 * includes its dynamic type.]
	 */
	int getLastPosition();
	
	/**
	 * @return The current dynamic context in effect.
	 */
	DynamicContext getDynamicContext();
	
	/**
	 * @return The current static context in effect.
	 */
	StaticContext getStaticContext();
}
