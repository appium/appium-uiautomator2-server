/*******************************************************************************
 * Copyright (c) 2010, 2011 Mukul Gandhi, and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Mukul Gandhi - initial API and implementation
 *     Mukul Gandhi - bug 323900 - improving computing the typed value of element and attribute nodes, where the 
 *                                 schema type of nodes are simple, with varieties 'list' and 'union'. 
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor;

import org.eclipse.wst.xml.xpath2.api.typesystem.SimpleTypeDefinition;
import org.eclipse.wst.xml.xpath2.api.typesystem.TypeDefinition;


/**
 * An PsychoPath Engine helper class providing useful module implementations for commonly 
 * performed "XML schema" evaluation tasks.  
 *
 * @since 2.0
 */
public class PsychoPathTypeHelper {
	
	// PsychoPath engine specific constants to support new built-in types, introduced in XML Schema 1.1.
	public static short DAYTIMEDURATION_DT = -100;
	public static short YEARMONTHDURATION_DT = -101;
	
	
	/* 
	 * Get Xerces "schema type" short code, given a type definition instance object. PsychoPath engine uses few custom 
	 * type 'short codes', to support XML Schema 1.1.
	 */
	public static short getXSDTypeShortCode(TypeDefinition typeDef) {
		
		// dummy type "short code" initializer
		short typeCode = -100;
		
		if ("dayTimeDuration".equals(typeDef.getName())) {
			typeCode = DAYTIMEDURATION_DT; 
		}
		else if ("yearMonthDuration".equals(typeDef.getName())) {
			typeCode = YEARMONTHDURATION_DT; 
		}
		
		return (typeCode != -100) ? typeCode : ((SimpleTypeDefinition) typeDef).getBuiltInKind();
		
	} // getXSDTypeShortCode

}
