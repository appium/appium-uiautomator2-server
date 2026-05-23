/*******************************************************************************
 * Copyright (c) 2009, 2012 Jesper Steen Moller, and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Jesper Steen Moller - initial API and implementation
 *     Jesper Steen Moller - bug 281028 - avg/min/max/sum work
 *     Mukul Gandhi - bug 280798 - PsychoPath support for JDK 1.4
 *    Lukasz Wycisk - bug 361060 - Aggregations with nil=�true� throw exceptions.
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal.utils;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.api.Item;
import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.internal.types.AnyAtomicType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.AnyType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.NodeType;

/**
 * Generic type promoter for handling subtype substitution and type promotions for functions and operators.
 */
public abstract class TypePromoter {
	private Class targetType = null;

	abstract public AnyAtomicType doPromote(AnyAtomicType value) throws DynamicError;	

	public final AnyAtomicType promote(AnyType value) throws DynamicError {
		// This is a short cut, really
		if (value.getClass() == getTargetType()) return (AnyAtomicType)value;

		AnyAtomicType atomized = atomize(value);
		if( atomized == null )
		{// empty sequence
			return null;
		}
		return doPromote(atomized);
	}

	/**
	 * @param typeToConsider The 
	 * @return The supertype to treat it as (i.e. if a xs:nonNegativeInteger is treated as xs:number)
	 */
	abstract protected Class substitute(Class typeToConsider);	

	abstract protected boolean checkCombination(Class newType);
		
	public void considerType(Class typeToConsider) throws DynamicError {
		Class baseType = substitute(typeToConsider);
		
		if (baseType == null) {
			throw DynamicError.argument_type_error(typeToConsider);
		}
		
		if (targetType == null) {
			targetType = baseType;
		} else {
			if (! checkCombination(baseType)) {
				throw DynamicError.argument_type_error(typeToConsider);
			}
		}
	}
	
	public void considerTypes(Collection typesToConsider) throws DynamicError {		
		for (Iterator iter = typesToConsider.iterator(); iter.hasNext();) {
			considerType((Class)iter.next());	
		}
	}
	
	public void considerSequence(ResultSequence sequenceToConsider) throws DynamicError {
		for (int i = 0; i < sequenceToConsider.size(); ++i) {
			Item item = sequenceToConsider.item(i);
			considerValue(item);
		}
	}
	
	public Class getTargetType() {
		return targetType;
	}
	
	protected void setTargetType(Class class1) {
		this.targetType = class1;
	}

	public AnyAtomicType atomize(Item at) {
		if (at instanceof NodeType) {
			ResultSequence nodeValues = ((NodeType)at).typed_value();
			if(nodeValues.empty()){
				return null;
			}
			return (AnyAtomicType)nodeValues.first();
		}
		else {
			return (AnyAtomicType)at;
		}
	}
	
	public void considerValue(Item at) throws DynamicError {
		final AnyAtomicType atomize = this.atomize(at);
		if( atomize != null )
		{// we known that it is not empty sequence
			this.considerType(atomize.getClass());
		}
	}


}