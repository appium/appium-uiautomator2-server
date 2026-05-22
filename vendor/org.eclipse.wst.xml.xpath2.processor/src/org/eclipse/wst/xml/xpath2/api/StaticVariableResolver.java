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

import javax.xml.namespace.QName;

import org.eclipse.wst.xml.xpath2.api.typesystem.ItemType;

/**
 * @since 2.0
 */
public interface StaticVariableResolver {

	/**
	 * Is the variable present in the current context.
	 * 
	 * @param name Variable name
	 * @return Availability of the variable
	 */
	boolean isVariablePresent(QName name);
	
	/** 
	 * @param name
	 * @return
	 */
	ItemType getVariableType(QName name);
}
