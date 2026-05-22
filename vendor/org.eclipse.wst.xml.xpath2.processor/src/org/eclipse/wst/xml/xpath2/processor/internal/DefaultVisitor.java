/*******************************************************************************
 * Copyright (c) 2013 Jesper Steen Moeller, and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Jesper Steen Moller  - bug 338494 - But without dependency on magic constants 
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal;

import java.util.Iterator;

import org.eclipse.wst.xml.xpath2.processor.ast.XPath;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.AddExpr;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.AndExpr;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.AnyKindTest;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.AttributeTest;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.AxisStep;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.CastExpr;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.CastableExpr;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.CmpExpr;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.CntxItemExpr;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.CommentTest;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.DecimalLiteral;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.DivExpr;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.DocumentTest;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.DoubleLiteral;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.ElementTest;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.ExceptExpr;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.Expr;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.FilterExpr;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.ForExpr;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.ForwardStep;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.FunctionCall;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.IDivExpr;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.IfExpr;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.InstOfExpr;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.IntegerLiteral;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.IntersectExpr;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.ItemType;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.MinusExpr;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.ModExpr;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.MulExpr;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.NameTest;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.OrExpr;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.PITest;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.ParExpr;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.PipeExpr;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.PlusExpr;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.QuantifiedExpr;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.RangeExpr;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.ReverseStep;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.SchemaAttrTest;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.SchemaElemTest;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.SequenceType;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.SingleType;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.StringLiteral;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.SubExpr;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.TextTest;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.TreatAsExpr;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.UnionExpr;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.VarExprPair;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.VarRef;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.XPathExpr;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.XPathVisitor;

@SuppressWarnings({"unchecked", "deprecation"})
public class DefaultVisitor implements XPathVisitor {

	/**
	 * Returns the normalized tree
	 * 
	 * @param xp
	 *            is the xpath expression.
	 * @return the xpath expressions.
	 */
	public Object visit(XPath xp) {
		for (Iterator<Expr> i = xp.iterator(); i.hasNext();) {
			Expr e = (Expr) i.next();
			e.accept(this);
		}
		return null;
	}

	/**
	 * 
	 * @param fex
	 *            is the For expression.
	 * @return fex expression.
	 */
	public Object visit(ForExpr fex) {
		for (Iterator<VarExprPair> i = fex.iterator(); i.hasNext();) {
			i.next().expr().accept(this);
		}
		fex.expr().accept(this);
		return null;
	}

	/**
	 * 
	 * @param qex
	 *            is the Quantified expression.
	 * @return qex expression.
	 */
	public Object visit(QuantifiedExpr qex) {
		for (Iterator<VarExprPair> i = qex.iterator(); i.hasNext();) {
			i.next().expr().accept(this);
		}
		qex.expr().accept(this);
		return null;
	}

	/**
	 * 
	 * @param ifex
	 *            is the 'if' expression.
	 * @return ifex expression.
	 */
	public Object visit(IfExpr ifex) {
		for (Iterator<Expr> i = ifex.iterator(); i.hasNext();) {
			i.next().accept(this);
		}
		ifex.then_clause().accept(this);
		ifex.else_clause().accept(this);
		return ifex;
	}

	/**
	 * @param ex
	 *            is the 'or' expression.
	 * @return make logic expr(orex).
	 */
	public Object visit(OrExpr ex) {
		ex.left().accept(this);
		ex.right().accept(this);
		return null;
	}

	/**
	 * @param ex
	 *            is the 'and' expression.
	 * @return make logic expr(andex).
	 */
	public Object visit(AndExpr ex) {
		ex.left().accept(this);
		ex.right().accept(this);
		return null;
	}

	/**
	 * @param cmpex
	 *            is the compare expression.
	 * @return cmpex.
	 */
	public Object visit(CmpExpr ex) {
		ex.left().accept(this);
		ex.right().accept(this);
		return null;
	}

	/**
	 * @param rex
	 *            is the range expression.
	 * @return a new function.
	 */
	public Object visit(RangeExpr ex) {
		ex.left().accept(this);
		ex.right().accept(this);
		return null;
	}

	/**
	 * @param ex
	 *            is the add expression.
	 * @return a new function.
	 */
	public Object visit(AddExpr ex) {
		ex.left().accept(this);
		ex.right().accept(this);
		return null;
	}

	/**
	 * @param ex
	 *            is the sub expression.
	 * @return a new function.
	 */
	public Object visit(SubExpr ex) {
		ex.left().accept(this);
		ex.right().accept(this);
		return null;
	}

	/**
	 * @param mulex
	 *            is the multiply expression.
	 * @return a new function.
	 */
	public Object visit(MulExpr ex) {
		ex.left().accept(this);
		ex.right().accept(this);
		return null;
	}

	/**
	 * @param ex
	 *            is the division expression.
	 * @return a new function.
	 */
	public Object visit(DivExpr ex) {
		ex.left().accept(this);
		ex.right().accept(this);
		return null;
	}

	/**
	 * @param ex
	 *            is the integer division expression that always returns an
	 *            integer.
	 * @return a new function.
	 */
	public Object visit(IDivExpr ex) {
		ex.left().accept(this);
		ex.right().accept(this);
		return null;
	}

	/**
	 * @param ex
	 *            is the mod expression.
	 * @return a new function.
	 */
	public Object visit(ModExpr ex) {
		ex.left().accept(this);
		ex.right().accept(this);
		return null;
	}

	/**
	 * @param ex
	 *            is the union expression.
	 * @return a new function.
	 */
	public Object visit(UnionExpr ex) {
		ex.left().accept(this);
		ex.right().accept(this);
		return null;
	}

	/**
	 * @param ex
	 *            is the pipe expression.
	 * @return a new function.
	 */
	public Object visit(PipeExpr ex) {
		ex.left().accept(this);
		ex.right().accept(this);
		return null;
	}

	/**
	 * @param ex
	 *            is the intersect expression.
	 * @return a new function.
	 */
	public Object visit(IntersectExpr ex) {
		ex.left().accept(this);
		ex.right().accept(this);
		return null;
	}

	/**
	 * @param ex
	 *            is the except expression.
	 * @return a new function.
	 */
	public Object visit(ExceptExpr ex) {
		ex.left().accept(this);
		ex.right().accept(this);
		return null;
	}

	/**
	 * @param ioexp
	 *            is the instance of expression.
	 * @return a ioexp.
	 */
	public Object visit(InstOfExpr ex) {
		ex.left().accept(this);
		ex.right().accept(this);
		return null;
	}

	/**
	 * @param taexp
	 *            is the treat as expression.
	 * @return a taexp.
	 */
	public Object visit(TreatAsExpr ex) {
		ex.left().accept(this);
		ex.right().accept(this);
		return null;
	}

	/**
	 * @param cexp
	 *            is the castable expression.
	 * @return cexp.
	 */
	public Object visit(CastableExpr cexp) {
		cexp.left().accept(this);
		cexp.right().accept(this);
		return null;
	}

	/**
	 * @param cexp
	 *            is the cast expression.
	 * @return cexp.
	 */
	public Object visit(CastExpr cexp) {
		cexp.left().accept(this);
		cexp.right().accept(this);
		return null;
	}

	/**
	 * @param e
	 *            is the minus expression.
	 * @return new sub expression
	 */
	public Object visit(MinusExpr e) {
		e.arg().accept(this);
		return null;
	}

	/**
	 * @param e
	 *            is the plus expression.
	 * @return new add expression
	 */
	public Object visit(PlusExpr e) {
		e.arg().accept(this);
		return null;
	}

	/**
	 * @param e
	 *            is the xpath expression.
	 * @return result.
	 */
	public Object visit(XPathExpr e) {
		e.expr().accept(this);
		e.next().accept(this);
		return null;
	}

	/**
	 * @param e
	 *            is the forward step.
	 * @return e
	 */
	public Object visit(ForwardStep e) {
		e.node_test().accept(this);
		return null;
	}

	/**
	 * @param e
	 *            is the reverse step.
	 * @return e
	 */
	public Object visit(ReverseStep e) {
		e.node_test().accept(this);
		return null;
	}

	/**
	 * @param e
	 *            is the Name test.
	 * @return e
	 */
	public Object visit(NameTest e) {
		return null;
	}

	/**
	 * @param e
	 *            is the veriable reference.
	 * @return e
	 */
	public Object visit(VarRef e) {
		return null;
	}

	/**
	 * @param e
	 *            is the string literal.
	 * @return e
	 */
	public Object visit(StringLiteral e) {
		return null;
	}

	/**
	 * @param e
	 *            is the integer literal.
	 * @return e
	 */
	public Object visit(IntegerLiteral e) {
		return null;
	}

	/**
	 * @param e
	 *            is the double literal.
	 * @return e
	 */
	public Object visit(DoubleLiteral e) {
		return null;
	}

	/**
	 * @param e
	 *            is the decimal literal.
	 * @return e
	 */
	public Object visit(DecimalLiteral e) {
		return null;
	}

	/**
	 * @param e
	 *            is the par expression.
	 * @return e
	 */
	public Object visit(ParExpr e) {
		for (Iterator<Expr> i = e.iterator(); i.hasNext();) {
			i.next().accept(this);
		}
		return null;
	}

	/**
	 * @param e
	 *            is the Cntx Item Expression.
	 * @return new function
	 */
	public Object visit(CntxItemExpr e) {
		return null;
	}

	/**
	 * @param e
	 *            is the fucntion call.
	 * @return e
	 */
	public Object visit(FunctionCall e) {
		for (Iterator<Expr> i = e.iterator(); i.hasNext();) {
			i.next().accept(this);
		}
		return e;
	}

	/**
	 * @param e
	 *            is the single type.
	 * @return e
	 */
	public Object visit(SingleType e) {
		return e;
	}

	/**
	 * @param e
	 *            is the sequence type.
	 * @return e
	 */
	public Object visit(SequenceType e) {
		ItemType it = e.item_type();
		if (it != null)
			it.accept(this);
		return null;
	}

	/**
	 * @param e
	 *            is the item type.
	 * @return e
	 */
	public Object visit(ItemType e) {

		switch (e.type()) {
		case ItemType.ITEM:
			break;
		case ItemType.QNAME:
			break;

		case ItemType.KINDTEST:
			e.kind_test().accept(this);
			break;
		}
		return null;
	}

	/**
	 * @param e
	 *            is the any kind test.
	 * @return e
	 */
	public Object visit(AnyKindTest e) {
		return null;
	}

	/**
	 * @param e
	 *            is the document test.
	 * @return e
	 */
	public Object visit(DocumentTest e) {

		switch (e.type()) {
		case DocumentTest.ELEMENT:
			e.elem_test().accept(this);
			break;

		case DocumentTest.SCHEMA_ELEMENT:
			e.schema_elem_test().accept(this);
			break;
		}
		return null;
	}

	/**
	 * @param e
	 *            is the text test.
	 * @return e
	 */
	public Object visit(TextTest e) {
		return null;
	}

	/**
	 * @param e
	 *            is the common test.
	 * @return e
	 */
	public Object visit(CommentTest e) {
		return null;
	}

	/**
	 * @param e
	 *            is the PI test.
	 * @return e
	 */
	public Object visit(PITest e) {
		return null;
	}

	/**
	 * @param e
	 *            is the attribute test.
	 * @return e
	 */
	public Object visit(AttributeTest e) {
		return e;
	}

	/**
	 * @param e
	 *            is the schema attribute test.
	 * @return e
	 */
	public Object visit(SchemaAttrTest e) {
		return e;
	}

	/**
	 * @param e
	 *            is the element test.
	 * @return e
	 */
	public Object visit(ElementTest e) {
		return e;
	}

	/**
	 * @param e
	 *            is the schema element test.
	 * @return e
	 */
	public Object visit(SchemaElemTest e) {
		return e;
	}

	/**
	 * @param e
	 *            is the axis step.
	 * @return e
	 */
	public Object visit(AxisStep e) {
		e.step().accept(this);
		for (Iterator<Expr> i = e.iterator(); i.hasNext();) {
			i.next().accept(this);
		}
		return null;
	}

	/**
	 * @param e
	 *            is the filter expression.
	 * @return e
	 */
	public Object visit(FilterExpr e) {
		e.primary().accept(this);
		for (Iterator<Expr> i = e.iterator(); i.hasNext();) {
			i.next().accept(this);
		}
		return e;
	}
}
