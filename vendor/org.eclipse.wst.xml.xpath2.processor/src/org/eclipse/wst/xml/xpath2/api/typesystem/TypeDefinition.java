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

import java.util.List;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;


/**
 * @noimplement This interface is not intended to be implemented by clients.
 * @since 2.0
 */
public interface TypeDefinition {
	public String getNamespace();
	public String getName();
	
    public TypeDefinition getBaseType();

    public static final short DERIVATION_NONE           = 0;
    public static final short DERIVATION_EXTENSION      = 1;
    public static final short DERIVATION_RESTRICTION    = 2;
    public static final short DERIVATION_SUBSTITUTION   = 4;
    public static final short DERIVATION_UNION          = 8;
    public static final short DERIVATION_LIST           = 16;

    public boolean derivedFromType(TypeDefinition ancestorType,
                                   short derivationMethod);

    public boolean derivedFrom(String namespace,
                               String name,
                               short derivationMethod);
	
    public Class getNativeType();
    
    List/*<Short>*/ getSimpleTypes(Attr attr);

	  List/*<Short>*/ getSimpleTypes(Element attr);
    
}
