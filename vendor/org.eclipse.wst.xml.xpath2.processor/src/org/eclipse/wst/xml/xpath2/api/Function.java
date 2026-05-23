/*******************************************************************************
 * Copyright (c) 2009, 2011 Jesper Moller, and others
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

import java.util.Collection;

import org.eclipse.wst.xml.xpath2.api.typesystem.TypeDefinition;

/**
 * Support for functions.
 * @since 2.0
 */
public interface Function {

	/**
	 * Support for QName interface.
	 * 
	 * @return Result of QName operation.
	 */
	String getName();

	/**
	 * Minimal number of allowed arguments.
	 * 
	 * @return The smallest number of arguments possible
	 */
	int getMinArity();

	/**
	 * Maximum number of allowed arguments.
	 * 
	 * @return The highest number of arguments possible
	 */
	int getMaxArity();

	/**
	 * Maximum number of allowed arguments.
	 * 
	 * @return The highest number of arguments possible
	 */
	boolean isVariableArgument();

	/**
	 * Checks if this function has an to the
	 * 
	 * @param actual_arity
	 * @return
	 */
	boolean canMatchArity(int actualArity);

	/**
	 * Gets the return type for the function.
	 * 
	 * @return TypeDefinition for the argument
	 */
	TypeDefinition getResultType();

	/**
	 * Returns the type of the argument at position index,
	 * starting at 0.  
	 * 
	 * @return TypeDefinition for the argument
	 */
	TypeDefinition getArgumentType(int index);

	/**
	 * Name hint for the index'th argument,  
	 * starting at 0.
	 * 
	 * @return TypeDefinition for the argument
	 */
	String getArgumentNameHint(int index);

	/**
	 * Evaluate arguments.
	 * 
	 * @param args
	 *            argument expressions.
	 * @return Result of evaluation.
	 */
	ResultSequence evaluate(Collection<ResultSequence> args, EvaluationContext evaluationContext);

	/**
	 * Evaluate the exact result type.
	 * 
	 * @param args
	 *            argument expressions.
	 * @return Result of evaluation.
	 */
	TypeDefinition computeReturnType(Collection<TypeDefinition> args, StaticContext sc);
}
