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

import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;

/**
 * Support for Mathematical division.
 */
public interface MathDiv {
	/**
	 * Division operation.
	 * 
	 * @param arg
	 *            input argument.
	 * @throws DynamicError
	 *             Dynamic error.
	 * @return Result of operation.
	 */
	public ResultSequence div(ResultSequence arg) throws DynamicError;
}
