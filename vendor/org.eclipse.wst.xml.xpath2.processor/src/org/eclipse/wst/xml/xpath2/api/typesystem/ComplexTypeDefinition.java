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

// TODO: Review all of this

/**
 * @since 2.0
 */
public interface ComplexTypeDefinition extends TypeDefinition {

    // Content Model Types
    /**
     * Represents an empty content type. A content type with the distinguished 
     * value empty validates elements with no character or element 
     * information item children. 
     */
    public static final short CONTENT_EMPTY         = 0;
    /**
     * Represents a simple content type. A content type which is simple 
     * validates elements with character-only children. 
     */
    public static final short CONTENT_SIMPLE        = 1;
    /**
     * Represents an element-only content type. An element-only content type 
     * validates elements with children that conform to the supplied content 
     * model. 
     */
    public static final short CONTENT_ELEMENT       = 2;
    /**
     * Represents a mixed content type.
     */
    public static final short CONTENT_MIXED         = 3;

    /**
     * [derivation method]: either <code>DERIVATION_EXTENSION</code>, 
     * <code>DERIVATION_RESTRICTION</code>, or <code>DERIVATION_NONE</code> 
     * (see <code>XSConstants</code>). 
     */
    public short getDerivationMethod();

    /**
     * [abstract]: a boolean. Complex types for which <code>abstract</code> is 
     * true must not be used as the type definition for the validation of 
     * element information items. 
     */
    public boolean getAbstract();

    /**
     *  A set of attribute uses if it exists, otherwise an empty 
     * <code>XSObjectList</code>. 
     */
//    public XSObjectList getAttributeUses();

    /**
     * An attribute wildcard if it exists, otherwise <code>null</code>. 
     */
//    public XSWildcard getAttributeWildcard();

    /**
     * [content type]: one of empty (<code>CONTENTTYPE_EMPTY</code>), a simple 
     * type definition (<code>CONTENTTYPE_SIMPLE</code>), mixed (
     * <code>CONTENTTYPE_MIXED</code>), or element-only (
     * <code>CONTENTTYPE_ELEMENT</code>). 
     */
    public short getContentType();

    /**
     * A simple type definition corresponding to a simple content model, 
     * otherwise <code>null</code>. 
     */
    public SimpleTypeDefinition getSimpleType();

    /**
     * A particle for a mixed or element-only content model, otherwise 
     * <code>null</code>. 
     */
//    public XSParticle getParticle();

    /**
     * [prohibited substitutions]: a subset of {extension, restriction}
     * @param restriction  Extension or restriction constants (see 
     *   <code>XSConstants</code>). 
     * @return True if <code>restriction</code> is a prohibited substitution, 
     *   otherwise false.
     */
    public boolean isProhibitedSubstitution(short restriction);

    /**
     *  [prohibited substitutions]: A subset of {extension, restriction} or 
     * <code>DERIVATION_NONE</code> represented as a bit flag (see 
     * <code>XSConstants</code>). 
     */
    public short getProhibitedSubstitutions();

    /**
     * A sequence of [annotations] or an empty <code>XSObjectList</code>.
     */
//    public XSObjectList getAnnotations();


	
}
