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

/**
 * An item in the XPath2 data model
 * 
 * @since 2.0
 * @noimplement This interface is not intended to be implemented by clients.
 */

public interface Item {
	/**
	 * @return A description of the item type.
	 */
	ItemType getItemType();

	/**
	 * @return The "Raw" Java object, e.g. org.w3.Node for a node,
	 *         java.util.String for strings, etc.
	 */
	Object getNativeValue();

	String getStringValue();
}
