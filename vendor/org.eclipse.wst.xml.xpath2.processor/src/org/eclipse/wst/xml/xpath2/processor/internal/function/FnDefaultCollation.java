/*******************************************************************************
 * Copyright (c) 2009, 2011 Standards for Technology in Automotive Retail and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     David Carver (STAR) - initial API and implementation
 *     Jesper Steen Moeller - bug 285145 - implement full arity checking
 *     Jesper Steen Moeller - bug 280555 - Add pluggable collation support
 *     Mukul Gandhi - bug 280798 - PsychoPath support for JDK 1.4
 *******************************************************************************/
package org.eclipse.wst.xml.xpath2.processor.internal.function;

import java.util.Collection;

import org.eclipse.wst.xml.xpath2.api.EvaluationContext;
import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.internal.types.QName;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSString;

/**
 * <p>
 * Summary: Returns the value of the default collation property from the static
 * context. Components of the static context are discussed in Section C.1 Static
 * Context Components.
 * </p>
 * 
 * <p>
 * Note:
 * </p>
 * 
 * <p>
 * The default collation property can never be undefined. If it is not
 * explicitly defined, a system defined default can be invoked. If this is not
 * provided, the Unicode code point collation
 * (http://www.w3.org/2005/xpath-functions/collation/codepoint) is used.
 * </p>
 * 
 * @author dcarver
 * @since 1.1
 */
public class FnDefaultCollation extends Function {

	public FnDefaultCollation() {
		super(new QName("default-collation"), 0);
	}

	public ResultSequence evaluate(Collection args, EvaluationContext ec) throws DynamicError {
		assert args.size() >= min_arity() && args.size() <= max_arity();
		return new XSString(ec.getDynamicContext().getCollationProvider().getDefaultCollation());
	}

}
