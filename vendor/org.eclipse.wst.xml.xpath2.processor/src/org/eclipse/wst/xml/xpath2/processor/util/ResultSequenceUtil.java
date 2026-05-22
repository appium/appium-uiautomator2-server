/*******************************************************************************
 * Copyright (c) 2011, Jesper Steen Moller, and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Jesper Steen Moller - initial API and implementation
 *     Jesper Steen Moller  - bug 340933 - Migrate to new XPath2 API
 *     Jesper Steen Moller - bug 343804 - Updated API information
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.util;

import org.eclipse.wst.xml.xpath2.processor.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.ResultSequenceFactory;
import org.eclipse.wst.xml.xpath2.processor.internal.types.AnyType;

/**
 * Introduced, but deprecated since it deals with old-style ResultSequences.
 * 
 * @since 2.0
 */
@SuppressWarnings("deprecation")
public class ResultSequenceUtil {

	public static ResultSequence newToOld(
			org.eclipse.wst.xml.xpath2.api.ResultSequence result) {
		if (result instanceof ResultSequence) 
			return (ResultSequence)result;
		
		ResultSequence rs = ResultSequenceFactory.create_new();
		for (int i = 0; i < result.size(); ++i) {
			rs.add((AnyType) result.item(i));
		}
		return rs;
	}

}
