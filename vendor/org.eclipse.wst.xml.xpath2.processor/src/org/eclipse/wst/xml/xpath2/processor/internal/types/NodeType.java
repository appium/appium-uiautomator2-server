/*******************************************************************************
 * Copyright (c) 2005, 2012 Andrea Bittau, University College London, and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Andrea Bittau - initial API and implementation from the PsychoPath XPath 2.0
 *     Mukul Gandhi - bug 276134 - improvements to schema aware primitive type support
 *                                 for attribute/element nodes
 *     David Carver (STAR)- bug 277774 - XSDecimal returning wrong values.
 *     Jesper Moller - bug 275610 - Avoid big time and memory overhead for externals
 *     David Carver (STAR) - bug 281186 - implementation of fn:id and fn:idref
 *     David Carver (STAR) - bug 289304 - fixed schema awareness on elements
 *     Mukul Gandhi - bug 318313 - improvements to computation of typed values of nodes,
 *                                 when validated by XML Schema primitive types
 *     Mukul Gandhi - bug 323900 - improving computing the typed value of element &
 *                                 attribute nodes, where the schema type of nodes
 *                                 are simple, with varieties 'list' and 'union'.                                 
 *     Jesper Steen Moller  - bug 340933 - Migrate to new XPath2 API
 *     Lukasz Wycisk - bug 361803 - NodeType:dom_to_xpath and null value
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal.types;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import org.eclipse.wst.xml.xpath2.api.ResultBuffer;
import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.api.typesystem.ComplexTypeDefinition;
import org.eclipse.wst.xml.xpath2.api.typesystem.PrimitiveType;
import org.eclipse.wst.xml.xpath2.api.typesystem.SimpleTypeDefinition;
import org.eclipse.wst.xml.xpath2.api.typesystem.TypeDefinition;
import org.eclipse.wst.xml.xpath2.api.typesystem.TypeModel;
import org.eclipse.wst.xml.xpath2.processor.PsychoPathTypeHelper;
import org.eclipse.wst.xml.xpath2.processor.ResultSequenceFactory;
import org.w3c.dom.Attr;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;
import org.w3c.dom.TypeInfo;

/**
 * A representation of a Node datatype
 */
public abstract class NodeType extends AnyType {
	
	protected static final String SCHEMA_TYPE_IDREF = "IDREF";
	protected static final String SCHEMA_TYPE_ID = "ID";
	private Node _node;
	protected TypeModel _typeModel;

	public static final Comparator NODE_COMPARATOR = new Comparator() {
		public int compare(Object o1, Object o2) {
			return compare_node((NodeType)o1, (NodeType)o2);
		}
	};
	
	/**
	 * Initialises according to the supplied parameters
	 * 
	 * @param node
	 *            The Node being represented
	 * @param document_order
	 *            The document order
	 */
	public NodeType(Node node, TypeModel tm) {
		_node = node;
		_typeModel = tm;
	}

	/**
	 * Retrieves the actual node being represented
	 * 
	 * @return Actual node being represented
	 */
	public Node node_value() {
		return _node;
	}

	// Accessors defined in XPath Data model
	// http://www.w3.org/TR/xpath-datamodel/
	/**
	 * Retrieves the actual node being represented
	 * 
	 * @return Actual node being represented
	 */
	public abstract ResultSequence typed_value();

	/**
	 * Retrieves the name of the node
	 * 
	 * @return QName representation of the name of the node
	 */
	public abstract QName node_name(); // may return null ["empty sequence"]

	// XXX element should override
	public ResultSequence nilled() {
		return ResultSequenceFactory.create_new();
	}

	// a little factory for converting from DOM to our representation
	public static NodeType dom_to_xpath(Node node, TypeModel tm) {
		assert node != null;
		
		switch (node.getNodeType()) {
		case Node.ELEMENT_NODE:
			return new ElementType((Element) node, tm);

		case Node.COMMENT_NODE:
			return new CommentType((Comment) node, tm);

		case Node.ATTRIBUTE_NODE:
			return new AttrType((Attr) node, tm);

		case Node.TEXT_NODE:
		case Node.CDATA_SECTION_NODE:
			return new TextType((Text) node, tm);

		case Node.DOCUMENT_NODE:
			return new DocType((Document) node, tm);

		case Node.PROCESSING_INSTRUCTION_NODE:
			return new PIType((ProcessingInstruction) node, tm);

		}

		// for unknown unsupported implementations
		return null;
	}

	public static org.eclipse.wst.xml.xpath2.processor.ResultSequence eliminate_dups(org.eclipse.wst.xml.xpath2.processor.ResultSequence rs) {
		Hashtable added = new Hashtable(rs.size());

		for (Iterator i = rs.iterator(); i.hasNext();) {
			NodeType node = (NodeType) i.next();
			Node n = node.node_value();

			if (added.containsKey(n))
				i.remove();
			else
				added.put(n, Boolean.TRUE);
		}
		return rs;
	}

	public static org.eclipse.wst.xml.xpath2.processor.ResultSequence sort_document_order(org.eclipse.wst.xml.xpath2.processor.ResultSequence rs) {
		ArrayList res = new ArrayList(rs.size());

		for (Iterator i = rs.iterator(); i.hasNext();) {
			NodeType node = (NodeType) i.next();
			boolean added = false;

			for (int j = 0; j < res.size(); j++) {
				NodeType x = (NodeType) res.get(j);

				if (before(node, x)) {
					res.add(j, node);
					added = true;
					break;
				}
			}
			if (!added)
				res.add(node);
		}

		rs = ResultSequenceFactory.create_new();
		for (Iterator i = res.iterator(); i.hasNext();) {
			NodeType node = (NodeType) i.next();

			rs.add(node);
		}

		return rs;
	}

	public static boolean same(NodeType a, NodeType b) {
		return (a.node_value().isSameNode(b.node_value()));
		// While compare_node(a, b) == 0 is tempting, it is also expensive
	}

	public boolean before(NodeType two) {
		return before(this, two);
	}

	public static boolean before(NodeType a, NodeType b) {
		return compare_node(a, b) < 0;
	}

	public boolean after(NodeType two) {
		return after(this, two);
	}

	public static boolean after(NodeType a, NodeType b) {
		return compare_node(a, b) > 0;
	}
	
	private static int compare_node(NodeType a, NodeType b) {
		Node nodeA = a.node_value();
		Node nodeB = b.node_value();
		
		if (nodeA == nodeB || nodeA.isSameNode(nodeB)) return 0;

		Document docA = getDocument(nodeA);
		Document docB = getDocument(nodeB);
		
		if (docA != docB && ! docA.isSameNode(docB)) {
			return compareDocuments(docA, docB);
		}
		short relation = nodeA.compareDocumentPosition(nodeB);
		if ((relation & Node.DOCUMENT_POSITION_PRECEDING) != 0) 
			  return 1;
		if ((relation & Node.DOCUMENT_POSITION_FOLLOWING) != 0) 
			  return -1;
		throw new RuntimeException("Unexpected result from node comparison: " + relation);
	}

	private static int compareDocuments(Document docA, Document docB) {
		// Arbitrary but fulfills the spec (provided documenURI is always set)
		if (docB.getDocumentURI() == null && docA.getDocumentURI() == null) {
			return System.identityHashCode(docA) - System.identityHashCode(docB); 
		}
		return docB.getDocumentURI().compareTo(docA.getDocumentURI());
	}

	private static Document getDocument(Node nodeA) {
		return nodeA instanceof Document ? (Document)nodeA : nodeA.getOwnerDocument();
	}

	protected Object getTypedValueForPrimitiveType(TypeDefinition typeDef) {		
		String strValue = getStringValue();
		
		if (typeDef == null) {
		   return new XSUntypedAtomic(strValue);
		}
		
		return SchemaTypeValueFactory.newSchemaTypeValue(PsychoPathTypeHelper.getXSDTypeShortCode(typeDef), strValue);
	} // getTypedValueForPrimitiveType 

	
	/*
	 * Construct the "typed value" from a "string value", given the simpleType of the node.
     */
	protected ResultSequence getXDMTypedValue(TypeDefinition typeDef, List/*<Short>*/ itemValTypes) {
		
		if ("anySimpleType".equals(typeDef.getName()) || 
		    "anyAtomicType".equals(typeDef.getName())) {
			return new XSUntypedAtomic(getStringValue());
		}
		else {
			SimpleTypeDefinition simpType = null;

			if (typeDef instanceof ComplexTypeDefinition) {
				ComplexTypeDefinition complexTypeDefinition = (ComplexTypeDefinition) typeDef;
				simpType = complexTypeDefinition.getSimpleType();
				if (simpType != null) {
					// element has a complexType with a simple content
					return getTypedValueForSimpleContent(simpType, itemValTypes);
				}
				else {
					// element has a complexType with complex content
					return new XSUntypedAtomic(getStringValue());
				}
			} else {
				// element has a simpleType
				simpType = (SimpleTypeDefinition) typeDef;
				return getTypedValueForSimpleContent(simpType, itemValTypes);
			}
		}
		
	} // getXDMTypedValue
	
	/*
     * Get the XDM typed value for schema "simple content model". 
     */
	private ResultSequence getTypedValueForSimpleContent(SimpleTypeDefinition simpType, List/*<Short>*/ itemValueTypes) {
		
		ResultBuffer rs = new ResultBuffer();
		
		if (simpType.getVariety() == SimpleTypeDefinition.VARIETY_ATOMIC) {
		   AnyType schemaTypeValue = SchemaTypeValueFactory.newSchemaTypeValue(PsychoPathTypeHelper.getXSDTypeShortCode(simpType), getStringValue());
		   if (schemaTypeValue != null) {
				return schemaTypeValue;
		   } else {
			   return new XSUntypedAtomic(getStringValue());
		   }
		}
		else if (simpType.getVariety() == SimpleTypeDefinition.VARIETY_LIST) {
			addAtomicListItemsToResultSet(simpType, itemValueTypes, rs);
		}
		else if (simpType.getVariety() == SimpleTypeDefinition.VARIETY_UNION) {
			getTypedValueForVarietyUnion(simpType, rs);
		}
		
		return rs.getSequence();
		
	} // getTypedValueForSimpleContent
	
	
	/*
	 * If the variety of simpleType was 'list', add the typed "list item" values to the parent result set. 
	 */
	private void addAtomicListItemsToResultSet(SimpleTypeDefinition simpType, List/*<Short>*/ itemValueTypes, ResultBuffer rs) {
		
		// tokenize the string value by a 'longest sequence' of white-spaces. this gives us the list items as string values.
		String[] listItemsStrValues = getStringValue().split("\\s+");
		
		SimpleTypeDefinition itemType = (SimpleTypeDefinition) simpType.getItemType();		
		if (itemType.getVariety() == SimpleTypeDefinition.VARIETY_ATOMIC) {
			for (int listItemIdx = 0; listItemIdx < listItemsStrValues.length; listItemIdx++) {
			   // add an atomic typed value (whose type is the "item  type" of the list, and "string value" is the "string 
			   // value of the list item") to the "result sequence".
		       rs.add(SchemaTypeValueFactory.newSchemaTypeValue(PsychoPathTypeHelper.getXSDTypeShortCode(itemType), 
		                                                        listItemsStrValues[listItemIdx]));
			}
		}
		else if (itemType.getVariety() == SimpleTypeDefinition.VARIETY_UNION) {
		    // here the list items may have different atomic types
			for (int listItemIdx = 0; listItemIdx < listItemsStrValues.length; listItemIdx++) {
				String listItem = listItemsStrValues[listItemIdx];
				rs.add(SchemaTypeValueFactory.newSchemaTypeValue(((Short)itemValueTypes.get(listItemIdx)).shortValue(), listItem));
			}
		}
		
	} // addAtomicListItemsToResultSet
	
	
	/*
	 * If the variety of simpleType was 'union', find the typed value (and added to the parent 'result set') 
	 * to be returned as the typed value of the parent node, by considering the member types of the union (i.e
	 * whichever member type first in order, can successfully validate the string value of the parent node).
	 */
	private void getTypedValueForVarietyUnion(SimpleTypeDefinition simpType, ResultBuffer rs) {
		
		List/*<SimpleTypeDefinition>*/ memberTypes = simpType.getMemberTypes();
		// check member types in order, to find that which one can successfully validate the string value.
		for (int memTypeIdx = 0; memTypeIdx < memberTypes.size(); memTypeIdx++) {
			PrimitiveType memSimpleType = (PrimitiveType) memberTypes.get(memTypeIdx);
		   if (isValueValidForSimpleType(getStringValue(), memSimpleType)) {
			  
			   rs.add(SchemaTypeValueFactory.newSchemaTypeValue(PsychoPathTypeHelper.getXSDTypeShortCode(memSimpleType), getStringValue()));
			   // no more memberTypes need to be checked
			   break; 
		   }
		}
		
	} // getTypedValueForVarietyUnion
	
	
	/*
	 * Determine if a "string value" is valid for a given simpleType definition. This is a helped method for other methods.
	 */
	private boolean isValueValidForSimpleType (String value, PrimitiveType simplType) {
		
		// attempt to validate the value with the simpleType
		return simplType.validate(value);
		
	} // isValueValidForASimpleType
	
	
	public abstract boolean isID();
	
	public abstract boolean isIDREF();

	/**
	 * Utility method to check to see if a particular TypeInfo matches.
	 * @param typeInfo
	 * @param typeName
	 * @return
	 */
	protected boolean isType(TypeInfo typeInfo, String typeName) {
		if (typeInfo != null) {
			String typeInfoName = typeInfo.getTypeName();
			if (typeInfoName != null) {
				if (typeInfo.getTypeName().equalsIgnoreCase(typeName)) {
					return true;
				}
			} 
		}
		return false;
	}

	/**
	 * Looks up the available type for the node, if available
	 * 
	 * @return TypeDefinition, or null
	 */
	protected TypeDefinition getType() {
		if (_typeModel != null) {
			return _typeModel.getType(node_value());
		} else {
			 return null;
		}
	}

	public Object getNativeValue() {
		return node_value();
	}

	public TypeModel getTypeModel() {
		return _typeModel;
	}

	public static ResultBuffer linarize(ResultBuffer rs) {
		TreeSet all = new TreeSet(NODE_COMPARATOR);
		all.addAll(rs.getCollection());
		return new ResultBuffer().concat(all);
	}
}
