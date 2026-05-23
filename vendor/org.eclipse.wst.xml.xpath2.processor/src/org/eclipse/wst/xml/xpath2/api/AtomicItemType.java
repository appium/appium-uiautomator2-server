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

import org.eclipse.wst.xml.xpath2.api.typesystem.ItemType;
import org.eclipse.wst.xml.xpath2.api.typesystem.TypeDefinition;

/**
 * Defines an sequence or item of atomic types.
 * 
 * @since 2.0
 */
public interface AtomicItemType extends ItemType {

	/**
	 * Returns the schema type of the sequence or type.
	 * 
	 * @return The Schema type of the sequence or item.
	 */
	public TypeDefinition getTypeDefinition();
}
