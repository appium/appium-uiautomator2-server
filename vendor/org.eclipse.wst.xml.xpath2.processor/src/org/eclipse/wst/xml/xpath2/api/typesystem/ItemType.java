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

package org.eclipse.wst.xml.xpath2.api.typesystem;

/**
 * @since 2.0
 */
public interface ItemType {
	
	public short getOccurrence();
	
	public final static short OCCURRENCE_OPTIONAL = 0;
	public final static short OCCURRENCE_ONE = 1;
	public final static short OCCURRENCE_NONE_OR_MANY = 2;
	public final static short OCCURRENCE_ONE_OR_MANY = 3;

	public final static short ALWAYS_ONE_MASK = 0x01;
	public final static short MAYBE_MANY_MASK = 0x02;
}
