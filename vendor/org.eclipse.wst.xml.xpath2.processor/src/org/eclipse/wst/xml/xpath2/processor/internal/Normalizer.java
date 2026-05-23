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

package org.eclipse.wst.xml.xpath2.processor.internal;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.wst.xml.xpath2.processor.StaticContext;
import org.eclipse.wst.xml.xpath2.processor.ast.XPath;
import org.eclipse.wst.xml.xpath2.processor.function.FnFunctionLibrary;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.AddExpr;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.AndExpr;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.AnyKindTest;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.AttributeTest;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.AxisStep;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.BinExpr;
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
import org.eclipse.wst.xml.xpath2.processor.internal.ast.NodeTest;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.OrExpr;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.PITest;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.ParExpr;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.PipeExpr;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.PlusExpr;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.PrimaryExpr;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.QuantifiedExpr;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.RangeExpr;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.ReverseStep;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.SchemaAttrTest;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.SchemaElemTest;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.SequenceType;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.SingleType;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.Step;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.StepExpr;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.StringLiteral;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.SubExpr;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.TextTest;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.TreatAsExpr;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.UnExpr;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.UnionExpr;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.VarExprPair;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.VarRef;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.XPathExpr;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.XPathNode;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.XPathVisitor;
import org.eclipse.wst.xml.xpath2.processor.internal.function.OpFunctionLibrary;
import org.eclipse.wst.xml.xpath2.processor.internal.types.QName;

/**
 * Normalizer that uses XPathVisitor.
 */
// XXX currently not supported anymore!
public class Normalizer implements XPathVisitor {

	private StaticContext _sc;

	/**
	 * Static Context is set to sc
	 * 
	 * @param sc
	 *            is the StaticContext.
	 */
	public Normalizer(StaticContext sc) {
		_sc = sc;
	}

	/**
	 * Returns the normalized tree
	 * 
	 * @param xp
	 *            is the xpath expression.
	 * @return the xpath expressions.
	 */
	public Object visit(XPath xp) {
		Collection exprs = new ArrayList();

		for (Iterator i = xp.iterator(); i.hasNext();) {
			Expr e = (Expr) i.next();

			Expr n = (Expr) e.accept(this);

			exprs.add(n);
		}

		return new XPath(exprs);
	}

	private void printVarExprPairs(Iterator i) {
		while (i.hasNext()) {
			VarExprPair pair = (VarExprPair) i.next();

			QName var = pair.varname();
			Expr e = pair.expr();

			e.accept(this);
		}
	}

	// does a for and a quantified expression
	// takes the iterator for var expr paris
	private void doForExpr(Iterator iter, Expr expr) {
		Collection vars = new ArrayList();

		// go through expression and cache variables
		while (iter.hasNext()) {
			VarExprPair pair = (VarExprPair) iter.next();

			QName var = pair.varname();
			Expr e = pair.expr();

			// XXX this is wrong!
			// need to define new scope, and reference "inner scope"
			// [shadow outer vars]
			/*
			 * if(_sc.variable_exists(var)) report_error(new
			 * StaticNameError("Variable " + var.string() +
			 * " already defined"));
			 */
			// ok we can cheat here cuz we only care about variable
			// "presence" not its specific instance / value so we
			// can fakely shadow without creating explicit scopes
			// if variable already exists.... then leave it there...
			// [we do not need to create a new instance and delete
			// it at the end]
			// we only need to if variable does not exist
			// XXX: i fink this is all wrong
			vars.add(var);

			e.accept(this);
		}

		// add variables to scope
		for (Iterator i = vars.iterator(); i.hasNext();) {
			QName var = (QName) i.next();
		}

		// do the bounded expression
		expr.accept(this);

		// remove variables
	}

	/**
	 * 
	 * @param fex
	 *            is the For expression.
	 * @return fex expression.
	 */
	public Object visit(ForExpr fex) {
		ForExpr last = fex;
		Expr ret = fex.expr();
		int depth = 0;

		for (Iterator i = fex.iterator(); i.hasNext();) {
			VarExprPair ve = (VarExprPair) i.next();

			// ok we got nested fors...
			if (depth > 0) {
				Collection pairs = new ArrayList();
				pairs.add(ve);

				ForExpr fe = new ForExpr(pairs, ret);
				last.set_expr(fe);

				last = fe;
			}

			depth++;
		}

		// normalize return value, and set it to the last for expr
		ret.accept(this);

		// get rid of the pairs in the parent (original) for
		if (depth > 1)
			fex.truncate_pairs();

		return fex;
	}

	/**
	 * 
	 * @param qex
	 *            is the Quantified expression.
	 * @return qex expression.
	 */
	// XXX: code duplication
	public Object visit(QuantifiedExpr qex) {
		QuantifiedExpr last = qex;
		Expr ret = qex.expr();
		int depth = 0;

		for (Iterator i = qex.iterator(); i.hasNext();) {
			VarExprPair ve = (VarExprPair) i.next();

			// ok we got nested fors...
			if (depth > 0) {
				Collection pairs = new ArrayList();
				pairs.add(ve);

				QuantifiedExpr qe = new QuantifiedExpr(qex.type(), pairs, ret);
				last.set_expr(qe);

				last = qe;
			}

			depth++;
		}

		// normalize return value, and set it to the last for expr
		ret.accept(this);

		// get rid of the pairs in the parent (original) for
		if (depth > 1)
			qex.truncate_pairs();

		return qex;

	}

	private void printExprs(Iterator i) {
		while (i.hasNext()) {
			Expr e = (Expr) i.next();

			e.accept(this);
		}
	}

	/**
	 * 
	 * @param ifex
	 *            is the 'if' expression.
	 * @return ifex expression.
	 */
	public Object visit(IfExpr ifex) {

		printExprs(ifex.iterator());

		ifex.then_clause().accept(this);

		ifex.else_clause().accept(this);

		return ifex;
	}

	/**
	 * @param name
	 *            of binary expression.
	 * @param e
	 *            is the binary expression.
	 */
	public void printBinExpr(String name, BinExpr e) {
		e.left().accept(this);
		e.right().accept(this);
	}

	private BinExpr make_logic_expr(BinExpr e) {
		Collection normalized = normalize_bin_args(e);

		XPathNode nor_arr[] = new XPathNode[2];
		int j = 0;

		for (Iterator i = normalized.iterator(); i.hasNext();) {
			nor_arr[j] = (XPathNode) i.next();
			j++;
		}

		Collection args = new ArrayList();
		args.add(nor_arr[0]);
		e.set_left(make_function(new QName("fn", "boolean",
				FnFunctionLibrary.XPATH_FUNCTIONS_NS), args));

		args.clear();
		args.add(nor_arr[1]);
		e.set_right(make_function(new QName("fn", "boolean",
				FnFunctionLibrary.XPATH_FUNCTIONS_NS), args));

		return e;
	}

	/**
	 * @param orex
	 *            is the 'or' expression.
	 * @return make logic expr(orex).
	 */
	public Object visit(OrExpr orex) {
		return make_logic_expr(orex);
	}

	/**
	 * @param andex
	 *            is the 'and' expression.
	 * @return make logic expr(andex).
	 */
	public Object visit(AndExpr andex) {
		return make_logic_expr(andex);
	}

	/**
	 * @param cmpex
	 *            is the compare expression.
	 * @return cmpex.
	 */
	public Object visit(CmpExpr cmpex) {
		switch (cmpex.type()) {
		case CmpExpr.EQ:
			return make_CmpOp(cmpex, new QName("fs", "eq",
					OpFunctionLibrary.XPATH_OP_NS));

		case CmpExpr.NE:
			return make_CmpOp(cmpex, new QName("fs", "ne",
					OpFunctionLibrary.XPATH_OP_NS));

		case CmpExpr.LT:
			return make_CmpOp(cmpex, new QName("fs", "lt",
					OpFunctionLibrary.XPATH_OP_NS));

		case CmpExpr.GT:
			return make_CmpOp(cmpex, new QName("fs", "gt",
					OpFunctionLibrary.XPATH_OP_NS));

		case CmpExpr.LE:
			return make_CmpOp(cmpex, new QName("fs", "le",
					OpFunctionLibrary.XPATH_OP_NS));

		case CmpExpr.GE:
			return make_CmpOp(cmpex, new QName("fs", "ge",
					OpFunctionLibrary.XPATH_OP_NS));

			// XXX don't have functs!
		case CmpExpr.IS:
			return make_function(new QName("op", "node-equal"),
					normalize_bin_args(cmpex));

		case CmpExpr.LESS_LESS:
			return make_function(new QName("op", "node-before"),
					normalize_bin_args(cmpex));

		case CmpExpr.GREATER_GREATER:
			return make_function(new QName("op", "node-after"),
					normalize_bin_args(cmpex));
		}

		printBinExpr("CMP" + cmpex.type(), cmpex);
		return cmpex;
	}

	private Collection normalize_bin_args(BinExpr e) {
		Collection args = new ArrayList();

		XPathNode left = (XPathNode) e.left().accept(this);
		XPathNode right = (XPathNode) e.right().accept(this);

		args.add(left);
		args.add(right);

		return args;
	}

	/**
	 * @param rex
	 *            is the range expression.
	 * @return a new function.
	 */
	public Object visit(RangeExpr rex) {
		Collection args = normalize_bin_args(rex);
		return make_function(new QName("op", "to",
				OpFunctionLibrary.XPATH_OP_NS), args);
	}

	private XPathExpr make_xpathexpr(PrimaryExpr pex) {
		FilterExpr fe = new FilterExpr(pex, new ArrayList());
		return new XPathExpr(0, fe);
	}

	private XPathExpr make_int_lit(int i) {
		IntegerLiteral il = new IntegerLiteral(BigInteger.valueOf(i));
		return make_xpathexpr(il);
	}

	private XPathExpr make_string_lit(String s) {
		StringLiteral sl = new StringLiteral(s);
		return make_xpathexpr(sl);
	}

	private XPathExpr make_convert_operand(XPathExpr arg1, XPathExpr arg2) {
		Collection args = new ArrayList();
		args.add(arg1);
		args.add(arg2);

		return make_function(new QName("fs", "convert-operand",
				OpFunctionLibrary.XPATH_OP_NS), args);
	}

	private XPathExpr make_double_lit(double d) {
		DoubleLiteral dl = new DoubleLiteral(d);
		return make_xpathexpr(dl);
	}

	// fs:fname( fs:convert-operand( fn:data(ARG1), 1.0E0 ),
	// fs:convert-operand( fn:data(ARG2), 1.0E0 )
	// )
	private XPathExpr make_convert_binop(BinExpr e, XPathExpr convarg,
			QName name) {
		Collection args = normalize_bin_args(e);
		XPathExpr args_arr[] = new XPathExpr[2];
		int j = 0;

		for (Iterator i = args.iterator(); i.hasNext();) {
			args_arr[j] = (XPathExpr) i.next();
			j++;
		}

		Collection argsfname = new ArrayList();
		for (j = 0; j < 2; j++) {
			XPathExpr arg = make_convert_operand(args_arr[j], convarg);
			argsfname.add(arg);
		}
		return make_function(name, argsfname);
	}

	private XPathExpr make_ArithOp(BinExpr e, QName name) {
		return make_convert_binop(e, make_double_lit(1.0), name);
	}

	// fs:fname( fs:convert_operand( fn:data(ARG1), "string"),
	// fs:convert_operand( fn:data(ARG2), "string")
	// )
	private XPathExpr make_CmpOp(BinExpr e, QName name) {
		return make_convert_binop(e, make_string_lit("string"), name);
	}

	/**
	 * @param addex
	 *            is the add expression.
	 * @return a new function.
	 */
	public Object visit(AddExpr addex) {
		return make_ArithOp(addex, new QName("fs", "plus",
				OpFunctionLibrary.XPATH_OP_NS));
	}

	/**
	 * @param subex
	 *            is the sub expression.
	 * @return a new function.
	 */
	public Object visit(SubExpr subex) {
		return make_ArithOp(subex, new QName("fs", "minus",
				OpFunctionLibrary.XPATH_OP_NS));
	}

	/**
	 * @param mulex
	 *            is the multiply expression.
	 * @return a new function.
	 */
	public Object visit(MulExpr mulex) {
		return make_ArithOp(mulex, new QName("fs", "times",
				OpFunctionLibrary.XPATH_OP_NS));
	}

	/**
	 * @param mulex
	 *            is the division expression.
	 * @return a new function.
	 */
	public Object visit(DivExpr mulex) {
		return make_ArithOp(mulex, new QName("fs", "div",
				OpFunctionLibrary.XPATH_OP_NS));
	}

	/**
	 * @param mulex
	 *            is the integer division expression that always returns an
	 *            integer.
	 * @return a new function.
	 */
	// XXX: integer cast!
	public Object visit(IDivExpr mulex) {
		return make_ArithOp(mulex, new QName("fs", "idiv",
				OpFunctionLibrary.XPATH_OP_NS));
	}

	/**
	 * @param mulex
	 *            is the mod expression.
	 * @return a new function.
	 */
	public Object visit(ModExpr mulex) {
		return make_ArithOp(mulex, new QName("fs", "mod",
				OpFunctionLibrary.XPATH_OP_NS));
	}

	/**
	 * @param unex
	 *            is the union expression.
	 * @return a new function.
	 */
	public Object visit(UnionExpr unex) {
		Collection args = normalize_bin_args(unex);
		return make_function(new QName("op", "union",
				OpFunctionLibrary.XPATH_OP_NS), args);
	}

	/**
	 * @param pipex
	 *            is the pipe expression.
	 * @return a new function.
	 */
	public Object visit(PipeExpr pipex) {
		Collection args = normalize_bin_args(pipex);
		return make_function(new QName("op", "union",
				OpFunctionLibrary.XPATH_OP_NS), args);
	}

	/**
	 * @param iexpr
	 *            is the intersect expression.
	 * @return a new function.
	 */
	public Object visit(IntersectExpr iexpr) {
		Collection args = normalize_bin_args(iexpr);
		return make_function(new QName("op", "intersect",
				OpFunctionLibrary.XPATH_OP_NS), args);
	}

	/**
	 * @param eexpr
	 *            is the except expression.
	 * @return a new function.
	 */
	public Object visit(ExceptExpr eexpr) {
		Collection args = normalize_bin_args(eexpr);
		return make_function(new QName("op", "except",
				OpFunctionLibrary.XPATH_OP_NS), args);
	}

	/**
	 * @param ioexp
	 *            is the instance of expression.
	 * @return a ioexp.
	 */
	public Object visit(InstOfExpr ioexp) {
		printBinExpr("INSTANCEOF", ioexp);
		return ioexp;
	}

	/**
	 * @param taexp
	 *            is the treat as expression.
	 * @return a taexp.
	 */
	public Object visit(TreatAsExpr taexp) {
		printBinExpr("TREATAS", taexp);
		return taexp;
	}

	/**
	 * @param cexp
	 *            is the castable expression.
	 * @return cexp.
	 */
	public Object visit(CastableExpr cexp) {
		printBinExpr("CASTABLE", cexp);
		return cexp;
	}

	/**
	 * @param cexp
	 *            is the cast expression.
	 * @return cexp.
	 */
	public Object visit(CastExpr cexp) {
		printBinExpr("CAST", cexp);
		return cexp;
	}

	/**
	 * @param name
	 *            is the name.
	 * @param e
	 *            is the Un Expression.
	 */
	public void printUnExpr(String name, UnExpr e) {
		e.arg().accept(this);

	}

	/**
	 * @param e
	 *            is the minus expression.
	 * @return new sub expression
	 */
	public Object visit(MinusExpr e) {
		SubExpr se = new SubExpr(make_int_lit(0), e.arg());
		return se.accept(this);
	}

	/**
	 * @param e
	 *            is the plus expression.
	 * @return new add expression
	 */
	public Object visit(PlusExpr e) {
		AddExpr ae = new AddExpr(make_int_lit(0), e.arg());

		return ae.accept(this);
	}

	private XPathExpr make_function(QName name, Collection args) {

		FunctionCall fc = new FunctionCall(name, args);
		FilterExpr fe = new FilterExpr(fc, new ArrayList());
		return new XPathExpr(0, fe);

	}

	private XPathExpr make_root_self_node() {

		// self::node()
		Step self_node = new ForwardStep(ForwardStep.SELF, new AnyKindTest());
		StepExpr self_node_expr = new AxisStep(self_node, new ArrayList());
		XPathExpr self_node_xpath = new XPathExpr(0, self_node_expr);

		// fn:root(self::node())
		Collection args = new ArrayList();
		args.add(self_node_xpath);
		XPathExpr xpe = make_function(new QName("fn", "root",
				FnFunctionLibrary.XPATH_FUNCTIONS_NS), args);

		return xpe;
	}

	private XPathExpr make_descendant_or_self() {
		Step desc_self_node = new ForwardStep(ForwardStep.DESCENDANT_OR_SELF,
				new AnyKindTest());
		StepExpr se = new AxisStep(desc_self_node, new ArrayList());

		return new XPathExpr(0, se);
	}

	/**
	 * @param e
	 *            is the xpath expression.
	 * @return result.
	 */
	public Object visit(XPathExpr e) {
		XPathExpr xp = e;
		int depth = 0; // indicates how many / we traversed
		XPathExpr result = e;

		while (xp != null) {
			int slashes = xp.slashes();
			StepExpr se = xp.expr();

			if (slashes == 1) {
				// this is a single slash and nothing else...
				if (se == null)
					return make_root_self_node();

				// /RelativePathExpr
				if (depth == 0) {
					XPathExpr xpe = make_root_self_node();
					xpe.set_next(e);

					result = xpe;
				}
			}

			if (slashes == 2) {
				// //RelativePathExpr
				if (depth == 0) {
					XPathExpr desc = make_descendant_or_self();
					desc.set_slashes(1);
					e.set_slashes(1);
					desc.set_next(e);

					XPathExpr root_self = make_root_self_node();
					root_self.set_next(desc);
					return root_self;
				}
			}

			if (se != null)
				se.accept(this);

			XPathExpr next = xp.next();

			// peek if the next guy will have 2 slashes...
			if (next != null) {
				// StepExpr//StepExpr
				if (next.slashes() == 2) {
					// create the node to stick between the
					// slashes
					XPathExpr desc = make_descendant_or_self();
					desc.set_slashes(1);

					// current node / desc / next
					xp.set_next(desc);
					desc.set_next(next);
					next.set_slashes(1);
				}
			}
			xp = next;
			depth++;
		}
		return result;
	}

	/**
	 * @param e
	 *            is the forward step.
	 * @return e
	 */
	// XXX: normalzie!
	public Object visit(ForwardStep e) {
		int axis = e.axis();

		switch (axis) {
		case ForwardStep.AT_SYM:
			e.set_axis(ForwardStep.ATTRIBUTE);
			break;

		case ForwardStep.NONE:
			e.set_axis(ForwardStep.CHILD);
			break;

		}

		e.node_test().accept(this);

		return e;
	}

	/**
	 * @param e
	 *            is the reverse step.
	 * @return e
	 */
	public Object visit(ReverseStep e) {

		if (e.axis() == ReverseStep.DOTDOT) {
			NodeTest nt = new AnyKindTest();
			Step s = new ReverseStep(ReverseStep.PARENT, nt);

			return s;
		}

		NodeTest nt = e.node_test();
		if (nt != null)
			nt.accept(this);

		return e;
	}

	/**
	 * @param e
	 *            is the Name test.
	 * @return e
	 */
	public Object visit(NameTest e) {

		String prefix = e.name().prefix();

		// XXX: is this correct ?
		// i.e. if there is no prefix... its ok.. else it must exist
		if (prefix == null)
			return null;

		return e;
	}

	/**
	 * @param e
	 *            is the veriable reference.
	 * @return e
	 */
	public Object visit(VarRef e) {
		return e;
	}

	/**
	 * @param e
	 *            is the string literal.
	 * @return e
	 */
	public Object visit(StringLiteral e) {
		return e;
	}

	/**
	 * @param e
	 *            is the integer literal.
	 * @return e
	 */
	public Object visit(IntegerLiteral e) {
		return e;
	}

	/**
	 * @param e
	 *            is the double literal.
	 * @return e
	 */
	public Object visit(DoubleLiteral e) {
		return e;
	}

	/**
	 * @param e
	 *            is the decimal literal.
	 * @return e
	 */
	public Object visit(DecimalLiteral e) {
		return e;
	}

	/**
	 * @param e
	 *            is the par expression.
	 * @return e
	 */
	public Object visit(ParExpr e) {
		printExprs(e.iterator());
		return e;
	}

	/**
	 * @param e
	 *            is the Cntx Item Expression.
	 * @return new function
	 */
	public Object visit(CntxItemExpr e) {
		return new VarRef(new QName("fs", "dot"));
	}

	/**
	 * @param e
	 *            is the fucntion call.
	 * @return e
	 */
	// XXX: how do we normalize ?
	public Object visit(FunctionCall e) {

		printExprs(e.iterator());
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

		return e;
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

		return e;
	}

	/**
	 * @param e
	 *            is the any kind test.
	 * @return e
	 */
	public Object visit(AnyKindTest e) {
		return e;
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
		return e;
	}

	/**
	 * @param e
	 *            is the text test.
	 * @return e
	 */
	public Object visit(TextTest e) {
		return e;
	}

	/**
	 * @param e
	 *            is the common test.
	 * @return e
	 */
	public Object visit(CommentTest e) {
		return e;
	}

	/**
	 * @param e
	 *            is the PI test.
	 * @return e
	 */
	public Object visit(PITest e) {
		String arg = e.arg();
		if (arg == null)
			arg = "";

		return e;
	}

	/**
	 * @param e
	 *            is the attribute test.
	 * @return e
	 */
	// XXX NO CHECK ?
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
	// XXX NO SEMANTIC CHECK?!
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

	private void printCollExprs(Iterator i) {
		while (i.hasNext()) {
			Collection exprs = (Collection) i.next();

			printExprs(exprs.iterator());
		}
	}

	/**
	 * @param e
	 *            is the axis step.
	 * @return e
	 */
	public Object visit(AxisStep e) {

		Step s = (Step) e.step().accept(this);
		e.set_step(s);

		printCollExprs(e.iterator());
		return e;
	}

	/**
	 * @param e
	 *            is the filter expression.
	 * @return e
	 */
	public Object visit(FilterExpr e) {
		PrimaryExpr pe = (PrimaryExpr) e.primary().accept(this);
		e.set_primary(pe);

		printCollExprs(e.iterator());
		return e;
	}
}
