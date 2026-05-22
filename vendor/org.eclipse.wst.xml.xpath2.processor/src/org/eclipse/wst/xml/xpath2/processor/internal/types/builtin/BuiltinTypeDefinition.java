/*******************************************************************************
 * Copyright (c) 2011, 2018 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xml.xpath2.processor.internal.types.builtin;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

import javax.xml.namespace.QName;

import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.api.typesystem.TypeDefinition;
import org.eclipse.wst.xml.xpath2.processor.internal.XPathError;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;

public class BuiltinTypeDefinition implements AtomicTypeDefinition  {
	
	public final static String XS_NS = "http://www.w3.org/2001/XMLSchema"; 
	
	private final QName name;
	private final Class implementationClass;
	private final Class nativeType;
	private final BuiltinTypeDefinition baseType;
	private final Method constructorMethod;
	private final Method constructorFromNativeMethod;

	public BuiltinTypeDefinition(QName name, BuiltinTypeDefinition baseType) {
		this(name, null, null, baseType);
	}

	public BuiltinTypeDefinition(String name, BuiltinTypeDefinition baseType) {
		this(name, null, null, baseType);
	}

	public BuiltinTypeDefinition(QName name, Class implementationClass, Class nativeType, BuiltinTypeDefinition baseType) {
		this.name = name;
		this.implementationClass = implementationClass;
		this.nativeType = nativeType;
		this.baseType = baseType;
		
		Method m = null;
		try {
			m = implementationClass != null ? implementationClass.getMethod("constructor", ResultSequence.class) : null;
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
		}
		this.constructorMethod = m;

		m = null;
		try {
			m = implementationClass != null ? implementationClass.getMethod("constructor", nativeType) : null;
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			// We'll live
		}
		this.constructorFromNativeMethod = m;

	}

	public boolean isAbstract() {
		return implementationClass == null;
	}
	
	public BuiltinTypeDefinition(String name, Class implementationClass, Class nativeType, BuiltinTypeDefinition baseType) {
		this(new QName(XS_NS, name), implementationClass, nativeType, baseType);
	}
	
	public String getNamespace() {
		return name.getNamespaceURI();
	}

	public String getName() {
		return name.getLocalPart();
	}

	public TypeDefinition getBaseType() {
		return baseType;
	}

	public boolean derivedFromType(TypeDefinition ancestorType, short derivationMethod) {
		if (ancestorType == this) return true;
		if (baseType == null) return false;
		return baseType.derivedFromType(ancestorType, derivationMethod);
	}

	public boolean derivedFrom(String namespace, String name, short derivationMethod) {
		if (namespace.equals(getNamespace()) && name.equals(getName())) return true;
		
		if (baseType == null) return false;
		
		return baseType.derivedFrom(namespace, name, derivationMethod);
	}

	public List getSimpleTypes(Attr attr) {
		return Collections.emptyList();
	}

	public List getSimpleTypes(Element attr) {
		return Collections.emptyList();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.wst.xml.xpath2.processor.internal.types.builtin.AtomicTypeDefinition#construct(org.eclipse.wst.xml.xpath2.api.ResultSequence)
	 */
	public SingleItemSequence construct(ResultSequence rs) {
		try {
			if (implementationClass == null) throw new XPathError("Type " + getName() + " is abstract!");
			return (SingleItemSequence)constructorMethod.invoke(null, new Object[] { rs });
		}
		catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}
	
	public SingleItemSequence constructNative(Object obj) {
		try {
			if (constructorFromNativeMethod == null) throw new XPathError("Type " + getName() + " cannot be constructed from native object!");
			return (SingleItemSequence)constructorFromNativeMethod.invoke(null, new Object[] { obj });
		}
		catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Class getNativeType() {
		return nativeType;
	}
}
