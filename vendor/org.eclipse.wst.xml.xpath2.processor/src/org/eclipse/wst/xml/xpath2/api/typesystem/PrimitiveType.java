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

import org.eclipse.wst.xml.xpath2.api.Item;

/**
 * @since 2.0
 */
public interface PrimitiveType/*<Type extends AnyType, NativeType>*/ extends TypeDefinition {

    /**
     * validate a given string against this simple type.
     *
     * @param content       the string value that needs to be validated
     * @param context       the validation context
     * @param validatedInfo used to store validation result
     *
     * @return              the actual value (QName, Boolean) of the string value
     */
    public boolean validate(String content);

    /**
     * validate a given string against this simple type.
     *
     * @param content       the string value that needs to be validated
     * @param context       the validation context
     * @param validatedInfo used to store validation result
     *
     * @return              the actual value (QName, Boolean) of the string value
     */
    public boolean validateNative(/*NativeType*/ Object content);

    /**
     * validate a given string against this simple type.
     *
     * @param content       the string value that needs to be validated
     * @param context       the validation context
     * @param validatedInfo used to store validation result
     *
     * @return              the actual value (QName, Boolean) of the string value
     */
    public /*Type*/Item construct(Object content);

    /**
     * @return              the actual value (QName, Boolean) of the string value
     */
    public Class/*<Type>*/ getInterfaceClass();

    /**
     * @return              The expected native type (class or interface) to expect when calling getValue. 
     */
    public Class/*<NativeType>*/ getNativeType();

    /**
     * Check whether two actual values are equal.
     *
     * @param value1  the first value
     * @param value2  the second value
     * @return        true if the two value are equal
     */
    public boolean isEqual(Object value1, Object value2);

    /**
     * Check whether this type is or is derived from ID.
     * REVISIT: this method makes ID special, which is not a good design.
     *          but since ID is not a primitive, there doesn't seem to be a
     *          clean way of doing it except to define special method like this.
     *
     * @return  whether this simple type is or is derived from ID.
     */
    public boolean isIDType();

    /**
     * Return the whitespace corresponding to this datatype.
     * 
     * @return valid values are WS_PRESERVE, WS_REPLACE, WS_COLLAPSE.
     * @exception DatatypeException
     *                   union datatypes don't have whitespace facet associated with them
     */
//    public short getWhitespace() throws DatatypeException;
}
