/*******************************************************************************
 * Copyright (c) 2005, 2013 Andrea Bittau, University College London, and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Andrea Bittau - initial API and implementation from the PsychoPath XPath 2.0 
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal.ast;

import org.eclipse.wst.xml.xpath2.processor.ast.XPath;

/**
 * Visitor class for XPath expressions.
 */
public interface XPathVisitor {
	/**
	 * Visit XPath.
	 */
	public Object visit(XPath xp);

	/**
	 * Visit ForExpr.
	 */
	public Object visit(ForExpr fex);

	/**
	 * Visit QuantifiedExpr.
	 */
	public Object visit(QuantifiedExpr qex);

	/**
	 * Visit IfExpr.
	 */
	public Object visit(IfExpr ifex);

	/**
	 * Visit OrExpr.
	 */
	public Object visit(OrExpr orex);

	/**
	 * Visit AndExpr.
	 */
	public Object visit(AndExpr andex);

	/**
	 * Visit CmpExpr.
	 */
	public Object visit(CmpExpr cmpex);

	/**
	 * Visit RangeExpr.
	 */
	public Object visit(RangeExpr rex);

	/**
	 * Visit AddExpr.
	 */
	public Object visit(AddExpr addex);

	/**
	 * Visit SubExpr.
	 */
	public Object visit(SubExpr subex);

	/**
	 * Visit MulExpr.
	 */
	public Object visit(MulExpr mulex);

	/**
	 * Visit DivExpr.
	 */
	public Object visit(DivExpr mulex);

	/**
	 * Visit IDivExpr.
	 */
	public Object visit(IDivExpr mulex);

	/**
	 * Visit ModExpr.
	 */
	public Object visit(ModExpr mulex);

	/**
	 * Visit UnionExpr.
	 */
	public Object visit(UnionExpr unex);

	/**
	 * Visit PipeExpr.
	 */
	public Object visit(PipeExpr pipex);

	/**
	 * Visit IntersectExpr.
	 */
	public Object visit(IntersectExpr iexpr);

	/**
	 * Visit ExceptExpr.
	 */
	public Object visit(ExceptExpr eexpr);

	/**
	 * Visit InstOfExpr.
	 */
	public Object visit(InstOfExpr ioexp);

	/**
	 * Visit TreatAsExpr.
	 */
	public Object visit(TreatAsExpr taexp);

	/**
	 * Visit CastableExpr.
	 */
	public Object visit(CastableExpr cexp);

	/**
	 * Visit CastExpr.
	 */
	public Object visit(CastExpr cexp);

	/**
	 * Visit MinusExpr.
	 */
	public Object visit(MinusExpr e);

	/**
	 * Visit PlusExpr.
	 */
	public Object visit(PlusExpr e);

	/**
	 * Visit XPathExpr.
	 */
	public Object visit(XPathExpr e);

	/**
	 * Visit ForwardStep.
	 */
	public Object visit(ForwardStep e);

	/**
	 * Visit ReverseStep.
	 */
	public Object visit(ReverseStep e);

	/**
	 * Visit NameTest.
	 */
	public Object visit(NameTest e);

	/**
	 * Visit VarRef.
	 */
	public Object visit(VarRef e);

	/**
	 * Visit StringLiteral.
	 */
	public Object visit(StringLiteral e);

	/**
	 * Visit IntegerLiteral.
	 */
	public Object visit(IntegerLiteral e);

	/**
	 * Visit DoubleLiteral.
	 */
	public Object visit(DoubleLiteral e);

	/**
	 * Visit DecimalLiteral.
	 */
	public Object visit(DecimalLiteral e);

	/**
	 * Visit ParExpr.
	 */
	public Object visit(ParExpr e);

	/**
	 * Visit CntxItemExpr.
	 */
	public Object visit(CntxItemExpr e);

	/**
	 * Visit FunctionCall.
	 */
	public Object visit(FunctionCall e);

	/**
	 * Visit SingleType.
	 */
	public Object visit(SingleType e);

	/**
	 * Visit SequenceType.
	 */
	public Object visit(SequenceType e);

	/**
	 * Visit ItemType.
	 */
	public Object visit(ItemType e);

	/**
	 * Visit AnyKindTest.
	 */
	public Object visit(AnyKindTest e);

	/**
	 * Visit DocumentTest.
	 */
	public Object visit(DocumentTest e);

	/**
	 * Visit TextTest.
	 */
	public Object visit(TextTest e);

	/**
	 * Visit CommentTest.
	 */
	public Object visit(CommentTest e);

	/**
	 * Visit PITest.
	 */
	public Object visit(PITest e);

	/**
	 * Visit AttributeTest.
	 */
	public Object visit(AttributeTest e);

	/**
	 * Visit SchemaAttrTest.
	 */
	public Object visit(SchemaAttrTest e);

	/**
	 * Visit ElementTest.
	 */
	public Object visit(ElementTest e);

	/**
	 * Visit SchemElemTest.
	 */
	public Object visit(SchemaElemTest e);

	/**
	 * Visit AxisStep.
	 */
	public Object visit(AxisStep e);

	/**
	 * Visit FilterExpr.
	 */
	public Object visit(FilterExpr e);
}
