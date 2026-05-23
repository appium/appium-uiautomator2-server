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

/**
 * @since 2.0
 */
public interface SimpleTypeDefinition extends TypeDefinition {
    /**
     * The variety is absent for the anySimpleType definition.
     */
    public static final short VARIETY_ABSENT            = 0;
    /**
     * <code>Atomic</code> type.
     */
    public static final short VARIETY_ATOMIC            = 1;
    /**
     * <code>List</code> type.
     */
    public static final short VARIETY_LIST              = 2;
    /**
     * <code>Union</code> type.
     */
    public static final short VARIETY_UNION             = 3;

    /**
     * [variety]: one of {atomic, list, union} or absent. 
     */
    public short getVariety();

    /**
     * If variety is <code>atomic</code> the primitive type definition (a 
     * built-in primitive datatype definition or the simple ur-type 
     * definition) is available, otherwise <code>null</code>. 
     */
    public SimpleTypeDefinition getPrimitiveType();

    /**
     * Returns the closest built-in type category this type represents or 
     * derived from. For example, if this simple type is a built-in derived 
     * type integer the <code>INTEGER_DV</code> is returned.
     * 
     * KILL!
     */
    public short getBuiltInKind();

    /**
     * If variety is <code>list</code> the item type definition (an atomic or 
     * union simple type definition) is available, otherwise 
     * <code>null</code>. 
     */
    public TypeDefinition getItemType();

    /**
     * If variety is <code>union</code> the list of member type definitions (a 
     * non-empty sequence of simple type definitions) is available, 
     * otherwise an empty <code>XSObjectList</code>. 
     */
    public List/*<SimpleTypeDefinition>*/ getMemberTypes();

    /**
     * [facets]: all facets defined on this type. The value is a bit 
     * combination of FACET_XXX constants of all defined facets. 
     */
//    public short getDefinedFacets();

    /**
     * Convenience method. [Facets]: check whether a facet is defined on this 
     * type.
     * @param facetName  The name of the facet. 
     * @return  True if the facet is defined, false otherwise.
     */
//    public boolean isDefinedFacet(short facetName);

    /**
     * [facets]: all defined facets for this type which are fixed.
     */
//    public short getFixedFacets();

    /**
     * Convenience method. [Facets]: check whether a facet is defined and 
     * fixed on this type. 
     * @param facetName  The name of the facet. 
     * @return  True if the facet is fixed, false otherwise.
     */
//    public boolean isFixedFacet(short facetName);

    /**
     * Convenience method. Returns a value of a single constraining facet for 
     * this simple type definition. This method must not be used to retrieve 
     * values for <code>enumeration</code> and <code>pattern</code> facets. 
     * @param facetName The name of the facet, i.e. 
     *   <code>FACET_LENGTH, FACET_TOTALDIGITS</code>.
     *   To retrieve the value for a pattern or 
     *   an enumeration, see <code>enumeration</code> and 
     *   <code>pattern</code>.
     * @return A value of the facet specified in <code>facetName</code> for 
     *   this simple type definition or <code>null</code>. 
     */
//    public String getLexicalFacetValue(short facetName);

    /**
     * A list of enumeration values if it exists, otherwise an empty 
     * <code>StringList</code>. 
     */
//    public StringList getLexicalEnumeration();

    /**
     * A list of pattern values if it exists, otherwise an empty 
     * <code>StringList</code>. 
     */
//    public StringList getLexicalPattern();

    /**
     *  Fundamental Facet: ordered. 
     */
    public short getOrdered();

    /**
     * Fundamental Facet: cardinality. 
     */
    public boolean getFinite();

    /**
     * Fundamental Facet: bounded. 
     */
    public boolean getBounded();

    /**
     * Fundamental Facet: numeric. 
     */
    public boolean getNumeric();

    /**
     *  A list of constraining facets if it exists, otherwise an empty 
     * <code>XSObjectList</code>. Note: This method must not be used to 
     * retrieve values for <code>enumeration</code> and <code>pattern</code> 
     * facets. 
     */
//    public XSObjectList getFacets();

    /**
     *  A list of enumeration and pattern constraining facets if it exists, 
     * otherwise an empty <code>XSObjectList</code>. 
     */
//    public XSObjectList getMultiValueFacets();

    /**
     * A sequence of [annotations] or an empty <code>XSObjectList</code>.
     */
//    public XSObjectList getAnnotations();
    

}
