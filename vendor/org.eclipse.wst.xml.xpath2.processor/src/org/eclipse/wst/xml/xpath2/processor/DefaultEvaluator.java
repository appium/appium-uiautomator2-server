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
 *     Mukul Gandhi - bug 274805 - improvements to xs:integer data type 
 *     Jesper Steen Moeller - bug 285145 - check arguments to op:to
 *     Jesper Steen Moeller - bug 262765 - fixed node state iteration
 *     Jesper Steen Moller  - bug 275610 - Avoid big time and memory overhead for externals
 *     Jesper Steen Moller  - bug 280555 - Add pluggable collation support
 *     Jesper Steen Moller  - bug 281938 - undefined context should raise error
 *     Jesper Steen Moller  - bug 262765 - use correct 'effective boolean value'
 *     Jesper Steen Moller  - bug 312191 - instance of test fails with partial matches
  *    Mukul Gandhi         - bug 325262 - providing ability to store an XPath2 sequence into
 *                                         an user-defined variable.
 *     Jesper Steen Moller  - bug 340933 - Migrate to new XPath2 API
 *     Jesper Steen Moller - bug 343804 - Updated API information
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.eclipse.wst.xml.xpath2.api.EvaluationContext;
import org.eclipse.wst.xml.xpath2.api.Function;
import org.eclipse.wst.xml.xpath2.api.Item;
import org.eclipse.wst.xml.xpath2.api.ResultBuffer;
import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.api.StaticContext;
import org.eclipse.wst.xml.xpath2.api.typesystem.TypeDefinition;
import org.eclipse.wst.xml.xpath2.api.typesystem.TypeModel;
import org.eclipse.wst.xml.xpath2.processor.ast.XPath;
import org.eclipse.wst.xml.xpath2.processor.internal.Axis;
import org.eclipse.wst.xml.xpath2.processor.internal.DescendantOrSelfAxis;
import org.eclipse.wst.xml.xpath2.processor.internal.DynamicContextAdapter;
import org.eclipse.wst.xml.xpath2.processor.internal.Focus;
import org.eclipse.wst.xml.xpath2.processor.internal.ForwardAxis;
import org.eclipse.wst.xml.xpath2.processor.internal.ParentAxis;
import org.eclipse.wst.xml.xpath2.processor.internal.ReverseAxis;
import org.eclipse.wst.xml.xpath2.processor.internal.SelfAxis;
import org.eclipse.wst.xml.xpath2.processor.internal.SeqType;
import org.eclipse.wst.xml.xpath2.processor.internal.StaticContextAdapter;
import org.eclipse.wst.xml.xpath2.processor.internal.StaticNameError;
import org.eclipse.wst.xml.xpath2.processor.internal.StaticTypeNameError;
import org.eclipse.wst.xml.xpath2.processor.internal.TypeError;
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
import org.eclipse.wst.xml.xpath2.processor.internal.ast.StepExpr;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.StringLiteral;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.SubExpr;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.TextTest;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.TreatAsExpr;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.UnionExpr;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.VarExprPair;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.VarRef;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.XPathExpr;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.XPathNode;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.XPathVisitor;
import org.eclipse.wst.xml.xpath2.processor.internal.function.ConstructorFL;
import org.eclipse.wst.xml.xpath2.processor.internal.function.FnBoolean;
import org.eclipse.wst.xml.xpath2.processor.internal.function.FnData;
import org.eclipse.wst.xml.xpath2.processor.internal.function.FnRoot;
import org.eclipse.wst.xml.xpath2.processor.internal.function.FsDiv;
import org.eclipse.wst.xml.xpath2.processor.internal.function.FsEq;
import org.eclipse.wst.xml.xpath2.processor.internal.function.FsGe;
import org.eclipse.wst.xml.xpath2.processor.internal.function.FsGt;
import org.eclipse.wst.xml.xpath2.processor.internal.function.FsIDiv;
import org.eclipse.wst.xml.xpath2.processor.internal.function.FsLe;
import org.eclipse.wst.xml.xpath2.processor.internal.function.FsLt;
import org.eclipse.wst.xml.xpath2.processor.internal.function.FsMinus;
import org.eclipse.wst.xml.xpath2.processor.internal.function.FsMod;
import org.eclipse.wst.xml.xpath2.processor.internal.function.FsNe;
import org.eclipse.wst.xml.xpath2.processor.internal.function.FsPlus;
import org.eclipse.wst.xml.xpath2.processor.internal.function.FsTimes;
import org.eclipse.wst.xml.xpath2.processor.internal.function.FunctionLibrary;
import org.eclipse.wst.xml.xpath2.processor.internal.function.OpExcept;
import org.eclipse.wst.xml.xpath2.processor.internal.function.OpIntersect;
import org.eclipse.wst.xml.xpath2.processor.internal.function.OpTo;
import org.eclipse.wst.xml.xpath2.processor.internal.function.OpUnion;
import org.eclipse.wst.xml.xpath2.processor.internal.types.AnyAtomicType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.AnyType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.AttrType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.CommentType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.DocType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.ElementType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.NodeType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.NumericType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.PIType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.QName;
import org.eclipse.wst.xml.xpath2.processor.internal.types.TextType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSBoolean;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSInteger;
import org.eclipse.wst.xml.xpath2.processor.internal.types.builtin.BuiltinTypeLibrary;
import org.eclipse.wst.xml.xpath2.processor.util.ResultSequenceUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Default evaluator interface
 */
public class DefaultEvaluator implements XPathVisitor, Evaluator {

	private static final String XML_SCHEMA_NS = "http://www.w3.org/2001/XMLSchema";

	private static final QName ANY_ATOMIC_TYPE = new QName("xs",
			"anyAtomicType", XML_SCHEMA_NS);

	private org.eclipse.wst.xml.xpath2.api.DynamicContext _dc;

	// this is a parameter that may be set on a call...
	// the parameter may become invalid on the next call... i.e. the
	// previous parameter is not saved... so use with care! [remember...
	// this thing is highly recursive]
	private Object _param;
	private EvaluationContext _ec;

	private StaticContext _sc;

	private Focus _focus = new Focus(ResultBuffer.EMPTY);

	Focus focus() { return _focus ; }
	
	void set_focus(Focus f) { _focus = f; }

	static class Pair {
		public Object _one;
		public Object _two;

		public Pair(Object o, Object t) {
			_one = o;
			_two = t;
		}
	}

	private void popScope() {
		if (_innerScope == null) throw new IllegalStateException("Unmatched scope pop");
		_innerScope = _innerScope.nextScope;
	}

	private void pushScope(QName var, org.eclipse.wst.xml.xpath2.api.ResultSequence value) {
		_innerScope = new VariableScope(var, value, _innerScope);		
	}

	private boolean derivesFrom(NodeType at, QName et) {
		TypeDefinition td = _sc.getTypeModel().getType(at.node_value());

		short method = TypeDefinition.DERIVATION_EXTENSION | TypeDefinition.DERIVATION_RESTRICTION;
		return td != null && td.derivedFrom(et.namespace(), et.local(), method);
	}

	private boolean derivesFrom(NodeType at, TypeDefinition td) {
		TypeDefinition nodeTd = _sc.getTypeModel().getType(at.node_value());
		short method = TypeDefinition.DERIVATION_EXTENSION | TypeDefinition.DERIVATION_RESTRICTION;
		return nodeTd != null && nodeTd.derivedFromType(td, method);
	}

	public DefaultEvaluator(org.eclipse.wst.xml.xpath2.processor.DynamicContext dynamicContext, Document doc) {
		this(new StaticContextAdapter(dynamicContext), new DynamicContextAdapter(dynamicContext));
		
		ResultSequence focusSequence = (doc != null) ? new DocType(doc, _sc.getTypeModel()) : ResultBuffer.EMPTY;   
		set_focus(new Focus(focusSequence));
		dynamicContext.set_focus(focus());
	}
	
	/**
	 * @since 2.0
	 */
	public DefaultEvaluator(org.eclipse.wst.xml.xpath2.api.StaticContext staticContext, org.eclipse.wst.xml.xpath2.api.DynamicContext dynamicContext, Object[] contextItems) {
		this(staticContext, dynamicContext);

		// initialize context item with root of document
		ResultBuffer rs = new ResultBuffer();
		for (Object obj : contextItems) {
			if (obj instanceof Node) rs.add(NodeType.dom_to_xpath((Node)obj, _sc.getTypeModel()));
		}

		set_focus(new Focus(rs.getSequence()));
		_param = null;
	}

	private DefaultEvaluator(org.eclipse.wst.xml.xpath2.api.StaticContext staticContext, org.eclipse.wst.xml.xpath2.api.DynamicContext dynamicContext) {
		_sc = staticContext;
		_dc = dynamicContext;
		_ec = new EvaluationContext() {
			
			public org.eclipse.wst.xml.xpath2.api.DynamicContext getDynamicContext() {
				return _dc;
			}
			
			public AnyType getContextItem() {
				return _focus.context_item();
			}
			
			public int getContextPosition() {
				return _focus.position();
			}
		
			public int getLastPosition() {
				return _focus.last();
			}
			
			public StaticContext getStaticContext() {
				return _sc;
			}
		};
	}

	class VariableScope {
		public VariableScope(QName name, org.eclipse.wst.xml.xpath2.api.ResultSequence value, VariableScope nextScope) {
			this.name = name;
			this.value = value;
			this.nextScope = nextScope;
		}
		final public QName name;
		final public org.eclipse.wst.xml.xpath2.api.ResultSequence value;
		final public VariableScope nextScope; 
	}	
	
	private VariableScope _innerScope = null;

	private org.eclipse.wst.xml.xpath2.api.ResultSequence getVariable(QName name) {
		// First, try local scopes
		VariableScope scope = _innerScope;
		while (scope != null) {
			if (name.equals(scope.name)) return scope.value;
			scope = scope.nextScope;
		}
		return (org.eclipse.wst.xml.xpath2.api.ResultSequence) _dc.getVariable(name.asQName());
	}

	// XXX this kinda sux
	// the problem is that visistor interface does not throw exceptions...
	// so we get around it ;D
	private void report_error(DynamicError err) {
		throw err;
	}

	private void report_error(TypeError err) {
		throw new DynamicError(err);
	}

	private void report_error(StaticNameError err) {
		throw err;
	}

	private AnyAtomicType makeAtomic(QName name) {
		FunctionLibrary fl = (FunctionLibrary) _sc.getFunctionLibraries().get(name.namespace());
		if (fl instanceof ConstructorFL) {
			ConstructorFL cfl = (ConstructorFL)fl;
			return cfl.atomic_type(name);
		}
		return null;
	}

	/**
	 * evaluate the xpath node
	 * 
	 * @param node
	 *            is the xpath node.
	 * @throws dynamic
	 *             error.
	 * @return result sequence.
	 */
	public org.eclipse.wst.xml.xpath2.processor.ResultSequence evaluate(XPathNode node) {
		return ResultSequenceUtil.newToOld(evaluate2(node));
	}

	/**
	 * @since 2.0
	 */
	public ResultSequence evaluate2(XPathNode node) {
		return (org.eclipse.wst.xml.xpath2.api.ResultSequence) node.accept(this);
	}
	
	// basically the comma operator...
	private ResultSequence do_expr(Iterator i) {

		ResultSequence rs = null;
		ResultBuffer buffer = null;

		while (i.hasNext()) {
			Expr e = (Expr) i.next();

			ResultSequence result = (ResultSequence) e.accept(this);

			if (rs == null && buffer == null)
				rs = result;
			else {
				if (buffer == null) {
					buffer = new ResultBuffer();
					buffer.concat(rs);
					rs = null;
				}
				buffer.concat(result);
			}
		}

		if (buffer != null) {
			return buffer.getSequence();
		} else if (rs != null) {
			return rs;
		} else return ResultBuffer.EMPTY;
	}

	/**
	 * iterate through xpath expression
	 * 
	 * @param xp
	 *            is the xpath.
	 * @return result sequence.
	 */
	public Object visit(XPath xp) {
		ResultSequence rs = do_expr(xp.iterator());

		return rs;
	}

	private void do_for_each(ListIterator iter,
			Expr finalexpr, ResultBuffer destination) {

		// we have more vars to bind...
		if (iter.hasNext()) {
			VarExprPair ve = (VarExprPair) iter.next();

			// evaluate binding sequence
			ResultSequence rs = (ResultSequence) ve.expr().accept(this);

			// XXX
			if (rs.empty()) {
				iter.previous();
				return;
			}

			QName varname = ve.varname();

			// for each item of binding sequence, bind the range
			// variable and do the expression, concatenating the
			// result

			for (Iterator i = rs.iterator(); i.hasNext();) {
				AnyType item = (AnyType) i.next();

				pushScope(varname, item);
				do_for_each(iter, finalexpr, destination);
				popScope();
			}
			iter.previous();
		}
		// we finally got to do the "last expression"
		else {
			destination.concat((ResultSequence) finalexpr.accept(this));
		}
	}


	// XXX ugly
	// type: 0 = for [return == "correct"]
	// 1 = for all [return false, return empty on true]
	// 2 = there exists [return true, return empty on false]
	private XSBoolean do_for_all(ListIterator iter,
			Expr finalexpr) {

		// we have more vars to bind...
		if (iter.hasNext()) {
			VarExprPair ve = (VarExprPair) iter.next();

			// evaluate binding sequence
			ResultSequence rs = (ResultSequence) ve.expr().accept(this);

			QName varname = ve.varname();

			// for each item of binding sequence, bind the range
			// variable and check the predicate

			try {
				for (Iterator i = rs.iterator(); i.hasNext();) {
					AnyType item = (AnyType) i.next();
	
					pushScope(varname, item);
					XSBoolean effbool = do_for_all(iter, finalexpr);
					popScope();
					
					// ok here we got a "real" result, now figure
					// out what to do with it
					if (!effbool.value())
						return XSBoolean.FALSE;
				}
			} finally {
				iter.previous();
			}
			return XSBoolean.TRUE;
		}
		// we finally got to do the "last expression"
		else {
			return effective_boolean_value((ResultSequence) finalexpr.accept(this));
		}

	}

	private XSBoolean do_exists(ListIterator iter,
			Expr finalexpr) {

		// we have more vars to bind...
		if (iter.hasNext()) {
			VarExprPair ve = (VarExprPair) iter.next();

			// evaluate binding sequence
			ResultSequence rs = (ResultSequence) ve.expr().accept(this);

			QName varname = ve.varname();

			// for each item of binding sequence, bind the range
			// variable and check the expression

			try {
				for (Iterator i = rs.iterator(); i.hasNext();) {
					AnyType item = (AnyType) i.next();
	
					pushScope(varname, item);
					XSBoolean effbool = do_exists(iter, finalexpr);
					popScope();
	
					// ok here we got a "real" result, now figure
					// out what to do with it
					if (effbool.value())
						return XSBoolean.TRUE;
				}
			} finally {
				iter.previous();
			}
			
			// since none in this sequence evaluated to true, return false
			return XSBoolean.FALSE;
		}
		// we finally got to do the "last expression"
		else {
			return effective_boolean_value((ResultSequence) finalexpr.accept(this));
		}

	}

	/**
	 * visit for expression
	 * 
	 * @param fex
	 *            is the for expression.
	 * @return a new function.
	 */
	public Object visit(ForExpr fex) {
		// XXX
		List pairs = new ArrayList(fex.ve_pairs());
		ResultBuffer rb = new ResultBuffer(); 
		do_for_each(pairs.listIterator(), fex.expr(), rb);
		return rb.getSequence();
	}

	/**
	 * visit quantified expression
	 * 
	 * @param qex
	 *            is the quantified expression.
	 * @return a new function or null.
	 */
	public Object visit(QuantifiedExpr qex) {
		List pairs = new ArrayList(qex.ve_pairs());

		switch (qex.type()) {
		case QuantifiedExpr.SOME:
			return do_exists(pairs.listIterator(), qex.expr());
		case QuantifiedExpr.ALL:
			return do_for_all(pairs.listIterator(), qex.expr());

		default:
			assert false;
			return null; // unreach
		}
	}

	/**
	 * visit if expression
	 * 
	 * @param ifex
	 *            is the if expression.
	 * @return a ifex.then_clause().accept(this).
	 */
	public Object visit(IfExpr ifex) {
		ResultSequence test_res = do_expr(ifex.iterator());

		XSBoolean res = effective_boolean_value(test_res);

		if (res.value())
			return ifex.then_clause().accept(this);
		else
			return ifex.else_clause().accept(this);
	}

	private boolean[] do_logic_exp(BinExpr e) {
		Collection args = do_bin_args(e);

		Iterator argiter = args.iterator();

		ResultSequence one = (ResultSequence) argiter.next();
		ResultSequence two = (ResultSequence) argiter.next();

		boolean oneb = effective_boolean_value(one).value();
		boolean twob = effective_boolean_value(two).value();

		boolean res[] = { oneb, twob };
		return res;
	}

	/**
	 * visit or expression
	 * 
	 * @param orex
	 *            is the or expression.
	 * @return a new function
	 */
	public Object visit(OrExpr orex) {
		boolean res[] = do_logic_exp(orex);

		return ResultSequenceFactory
				.create_new(new XSBoolean(res[0] || res[1]));
	}

	/**
	 * visit and expression
	 * 
	 * @param andex
	 *            is the and expression.
	 * @return a new function
	 */
	public Object visit(AndExpr andex) {
		boolean res[] = do_logic_exp(andex);

		return ResultSequenceFactory
				.create_new(new XSBoolean(res[0] && res[1]));
	}

	private ResultSequence node_cmp(int type, Collection args) {
		assert args.size() == 2;

		Iterator argsiter = args.iterator();

		ResultSequence one = (ResultSequence) argsiter.next();
		ResultSequence two = (ResultSequence) argsiter.next();

		int size_one = one.size();
		int size_two = two.size();

		if (size_one > 1 || size_two > 1)
			report_error(TypeError.invalid_type(null));

		if (size_one == 0 || size_two == 0)
			return ResultBuffer.EMPTY;

		Item at_one = one.item(0);
		Item at_two = two.item(0);

		if (!(at_one instanceof NodeType) || !(at_two instanceof NodeType))
			report_error(TypeError.invalid_type(null));

		// ok we got the args finally
		NodeType nt_one = (NodeType) at_one;
		NodeType nt_two = (NodeType) at_two;

		boolean answer = false; // we are pessimistic as usual

		// do comparison
		switch (type) {
		case CmpExpr.IS:
			answer = nt_one.node_value() == nt_two.node_value();
			break;

		case CmpExpr.LESS_LESS:
			answer = nt_one.before(nt_two);
			break;

		case CmpExpr.GREATER_GREATER:
			answer = nt_one.after(nt_two);
			break;

		default:
			assert false;
		}

		return XSBoolean.valueOf(answer);
	}

	/**
	 * visit compare expression
	 * 
	 * @param cmpex
	 *            is the compare expression.
	 * @return a new function or null
	 */
	public Object visit(CmpExpr cmpex) {
		try {
			Collection args = do_bin_args(cmpex);

			switch (cmpex.type()) {
			case CmpExpr.EQ:
				return FsEq.fs_eq_value(args, _dc);

			case CmpExpr.NE:
				return FsNe.fs_ne_value(args, _dc);

			case CmpExpr.GT:
				return FsGt.fs_gt_value(args, _dc);

			case CmpExpr.LT:
				return FsLt.fs_lt_value(args, _dc);

			case CmpExpr.GE:
				return FsGe.fs_ge_value(args, _dc);

			case CmpExpr.LE:
				return FsLe.fs_le_value(args, _dc);

			case CmpExpr.EQUALS:
				return FsEq.fs_eq_general(args, _dc);

			case CmpExpr.NOTEQUALS:
				return FsNe.fs_ne_general(args, _dc);

			case CmpExpr.GREATER:
				return FsGt.fs_gt_general(args, _dc);

			case CmpExpr.LESSTHAN:
				return FsLt.fs_lt_general(args, _dc);

			case CmpExpr.GREATEREQUAL:
				return FsGe.fs_ge_general(args, _dc);

			case CmpExpr.LESSEQUAL:
				return FsLe.fs_le_general(args, _dc);

			case CmpExpr.IS:
			case CmpExpr.LESS_LESS:
			case CmpExpr.GREATER_GREATER:
				return node_cmp(cmpex.type(), args);

			default:
				assert false;
			}
		} catch (DynamicError err) {
			report_error(err);
		}
		return null; // unreach
	}

	/**
	 * visit range expression
	 * 
	 * @param rex
	 *            is the range expression.
	 * @return a new function
	 */
	public Object visit(RangeExpr rex) {
		ResultSequence one = (ResultSequence) rex.left().accept(this);
		ResultSequence two = (ResultSequence) rex.right().accept(this);
		if (one.empty() || two.empty()) return ResultSequenceFactory.create_new(); 
		Collection args = new ArrayList();
		args.add(one);
		args.add(two);

		try {
			return OpTo.op_to(args);
		} catch (DynamicError err) {
			report_error(err);
			return null; // unreach
		}
	}

	private XSBoolean effective_boolean_value(ResultSequence rs) {
		try {
			return FnBoolean.fn_boolean(rs);
		} catch (DynamicError err) {
			report_error(err);
			return null; // unreach
		}
	}

	
	/**
	 * visit and expression
	 * 
	 * @param addex
	 *            is the and expression.
	 * @return a new function
	 */
	public Object visit(AddExpr addex) {
		try {
			Collection args = do_bin_args(addex);
			return FsPlus.fs_plus(args);
		} catch (DynamicError err) {
			report_error(err);
			return null; // unreach
		}
	}

	/**
	 * visit sub expression
	 * 
	 * @param subex
	 *            is the sub expression.
	 * @return a new function
	 */
	public Object visit(SubExpr subex) {
		try {
			Collection args = do_bin_args(subex);
			return FsMinus.fs_minus(args);
		} catch (DynamicError err) {
			report_error(err);
			return null; // unreach
		}
	}

	/**
	 * visit multiply expression
	 * 
	 * @param mulex
	 *            is the mul expression.
	 * @return a new function
	 */
	public Object visit(MulExpr mulex) {
		try {
			Collection args = do_bin_args(mulex);
			return FsTimes.fs_times(args);
		} catch (DynamicError err) {
			report_error(err);
			return null; // unreach
		}
	}

	/**
	 * visit division expression
	 * 
	 * @param mulex
	 *            is the division expression.
	 * @return a new function
	 */
	public Object visit(DivExpr mulex) {
		try {
			Collection args = do_bin_args(mulex);
			return FsDiv.fs_div(args);
		} catch (DynamicError err) {
			report_error(err);
			return null; // unreach
		}
	}

	/**
	 * visit integer division expression
	 * 
	 * @param mulex
	 *            is the integer division expression.
	 * @return a new function
	 */
	public Object visit(IDivExpr mulex) {
		try {
			Collection args = do_bin_args(mulex);
			return FsIDiv.fs_idiv(args);
		} catch (DynamicError err) {
			report_error(err);
			return null; // unreach
		}
	}

	/**
	 * visit modular expression
	 * 
	 * @param mulex
	 *            is the modular expression.
	 * @return a new function
	 */
	public Object visit(ModExpr mulex) {
		try {
			Collection args = do_bin_args(mulex);
			return FsMod.fs_mod(args);
		} catch (DynamicError err) {
			report_error(err);
			return null; // unreach
		}
	}

	private Collection do_bin_args(BinExpr e) {
		ResultSequence one = (ResultSequence) e.left().accept(this);
		ResultSequence two = (ResultSequence) e.right().accept(this);

		Collection args = new ArrayList();
		args.add(one);
		args.add(two);

		return args;
	}

	/**
	 * visit union expression
	 * 
	 * @param unex
	 *            is the union expression.
	 * @return a new function
	 */
	public Object visit(UnionExpr unex) {
		try {
			Collection args = do_bin_args(unex);
			return OpUnion.op_union(args);
		} catch (DynamicError err) {
			report_error(err);
			return null; // unreach
		}
	}

	/**
	 * visit pipe expression
	 * 
	 * @param pipex
	 *            is the pipe expression.
	 * @return a new function
	 */
	// XXX same as above
	public Object visit(PipeExpr pipex) {
		try {
			Collection args = do_bin_args(pipex);
			return OpUnion.op_union(args);
		} catch (DynamicError err) {
			report_error(err);
			return null; // unreach
		}
	}

	/**
	 * visit intersect expression
	 * 
	 * @param iexpr
	 *            is the intersect expression.
	 * @return a new function
	 */
	public Object visit(IntersectExpr iexpr) {
		try {
			Collection args = do_bin_args(iexpr);
			return OpIntersect.op_intersect(args);
		} catch (DynamicError err) {
			report_error(err);
			return null; // unreach
		}
	}

	/**
	 * visit except expression
	 * 
	 * @param eexpr
	 *            is the except expression.
	 * @return a new function
	 */
	public Object visit(ExceptExpr eexpr) {
		try {
			Collection args = do_bin_args(eexpr);
			return OpExcept.op_except(args);
		} catch (DynamicError err) {
			report_error(err);
			return null; // unreach
		}
	}

	/**
	 * visit instance of expression
	 * 
	 * @param ioexp
	 *            is the instance of expression.
	 * @return a new function
	 */
	public Object visit(InstOfExpr ioexp) {
		// get the value
		ResultSequence rs = (ResultSequence) ioexp.left().accept(this);

		// get the sequence type
		SequenceType seqt = (SequenceType) ioexp.right();
		return ResultSequenceFactory.create_new(new XSBoolean(isInstanceOf(rs, seqt)));
	}
		
	private boolean isInstanceOf(ResultSequence rs, SequenceType seqt) {
		Object oldParam = this._param;
		try {
			this._param = new Pair(null, rs);
			int sequenceLength = rs.size();
			// Run the matcher
			seqt.accept(this);
			rs = (ResultSequence) ((Pair)_param)._two;
			int lengthAfter = rs.size();
		
			if (sequenceLength != lengthAfter)
				return false; // Something didn't match, so it's not an instance of it
			
			return seqt.isLengthValid(sequenceLength);
		} finally {
			this._param = oldParam;
		}
	}

	/**
	 * visit treat-as expression
	 * 
	 * @param taexp
	 *            is the treat-as expression.
	 * @return a new function
	 */
	public Object visit(TreatAsExpr taexp) {

		ResultSequence rs = (ResultSequence) taexp.left().accept(this);

		SequenceType seqt = (SequenceType) taexp.right();
		SeqType st = new SeqType(seqt, _sc, rs);

		try {
			st.match(rs);
		} catch (DynamicError err) {
			report_error(err);
		}

		return rs;
	}

	/**
	 * visit castable expression
	 * 
	 * @param cexp
	 *            is the castable expression.
	 * @return a new function
	 */
	public Object visit(CastableExpr cexp) {
		boolean castable = false;
		try {
			CastExpr ce = new CastExpr((Expr) cexp.left(), (SingleType) cexp
					.right());

			visit(ce);
			castable = true;
		} catch (Throwable t) {
			castable = false;
		}

		return ResultSequenceFactory.create_new(new XSBoolean(castable));
	}

	/**
	 * visit cast expression
	 * 
	 * @param cexp
	 *            is the cast expression.
	 * @return a new function
	 */
	public Object visit(CastExpr cexp) {

		ResultSequence rs = (ResultSequence) cexp.left().accept(this);
		SingleType st = (SingleType) cexp.right();

		rs = FnData.atomize(rs);

		if (rs.size() > 1)
			report_error(TypeError.invalid_type(null));

		if (rs.empty()) {
			if (st.qmark())
				return rs;
			else
				report_error(TypeError.invalid_type(null));
		}

		AnyType at = (AnyType) rs.item(0);

		if (!(at instanceof AnyAtomicType))
			report_error(TypeError.invalid_type(null));

		AnyAtomicType aat = (AnyAtomicType) at;
		QName type = st.type();

		// prepare args from function
		Collection args = new ArrayList();
		args.add(ResultSequenceFactory.create_new(aat));

		try {
			Function function = cexp.function();
			if (function == null) {
				function = _sc.resolveFunction(type.asQName(), args.size());
				cexp.set_function(function);
			}
			if (function == null)
				report_error(TypeError.invalid_type(null));
			return function.evaluate(args, _ec);
		} catch (DynamicError err) {
			report_error(err);
			return null; // unreach
		}
	}

	/**
	 * visit minus expression
	 * 
	 * @param e
	 *            is the minus expression.
	 * @return a new function
	 */
	public Object visit(MinusExpr e) {
		ResultSequence rs = (ResultSequence) e.arg().accept(this);

		Collection args = new ArrayList();
		args.add(rs);

		try {
			return FsMinus.fs_minus_unary(args);
		} catch (DynamicError err) {
			report_error(err);
			return null; // unreach
		}
	}

	/**
	 * visit plus expression
	 * 
	 * @param e
	 *            is the plus expression.
	 * @return a new function
	 */
	public Object visit(PlusExpr e) {
		ResultSequence rs = (ResultSequence) e.arg().accept(this);

		Collection args = new ArrayList();
		args.add(rs);

		try {
			return FsPlus.fs_plus_unary(args);
		} catch (DynamicError err) {
			report_error(err);
			return null; // unreach
		}
	}

	// this will evaluate the step expression for the whole focus and return
	// the result.
	//
	// i.e. It will execute the step expression for each item in the focus
	// [each time changing the context item].
	private ResultSequence do_step(StepExpr se) {

		ResultBuffer rs = new ResultBuffer();
		ArrayList results = new ArrayList();
		int type = 0; // 0: don't know yet
		// 1: atomic
		// 2: node

		Focus focus = focus();
		int original_pos = focus.position();

		// execute step for all items in focus
		while (true) {
			results.add(se.accept(this));

			// go to next
			if (!focus.advance_cp())
				break;
		}
		
		// make sure we didn't change focus
		focus.set_position(original_pos);

		boolean node_types = false;

		// check the results
		for (Iterator i = results.iterator(); i.hasNext();) {
			ResultSequence result = (ResultSequence) i.next();

			// make sure results are of same type, and add them in
			for (Iterator j = result.iterator(); j.hasNext();) {
				AnyType item = (AnyType) j.next();

				// first item
				if (type == 0) {
					if (item instanceof AnyAtomicType)
						type = 1;
					else if (item instanceof NodeType)
						type = 2;
					else
						assert false;

				}

				// make sure we got coherent types
				switch (type) {
				// atomic... just concat
				case 1:
					if (!(item instanceof AnyAtomicType))
						report_error(TypeError.mixed_vals(null));
					rs.add(item);
					break;

				case 2:
					node_types = true;
					if (!(item instanceof NodeType))
						report_error(TypeError.mixed_vals(null));
					rs.add(item);
					break;

				default:
					assert false;
				}
			}
		}
		// XXX lame
		if (node_types) {
			rs = NodeType.linarize(rs);
		}
		return rs.getSequence();
	}

	private ResultSequence root_self_node() {
		Axis axis = new SelfAxis();
		ResultBuffer buffer = new ResultBuffer();

		// XXX the cast!!!
		axis.iterate((NodeType) focus().context_item(), buffer, _dc.getLimitNode());

		ResultSequence rs = kind_test(buffer.getSequence(), NodeType.class);

		List records = new ArrayList();
		records.add(rs);
		rs = FnRoot.fn_root(records, _ec);
		return rs;
	}

	private ResultSequence descendant_or_self_node(ResultSequence rs) {
		ResultBuffer res = new ResultBuffer();
		Axis axis = new DescendantOrSelfAxis();

		// for all nodes, get descendant or self nodes
		for (Iterator i = rs.iterator(); i.hasNext();) {
			NodeType item = (NodeType) i.next();

			axis.iterate(item, res, _dc.getLimitNode());
		}

		return res.getSequence();
	}

	/**
	 * visit XPath expression
	 * 
	 * @param e
	 *            is the XPath expression.
	 * @return a new function
	 */
	public Object visit(XPathExpr e) {
		XPathExpr xp = e;

		ResultSequence rs = null;
		Focus original_focus = focus();

		// do all the steps
		while (xp != null) {
			StepExpr se = xp.expr();

			if (se != null) {
				// this is not the first step
				if (rs != null) {
					// XXX ?
					// the expression didn't return any
					// results...
					if (rs.size() == 0)
						break;

					// make sure result of previous step are
					// nodes!
					for (Iterator i = rs.iterator(); i.hasNext();) {
						AnyType item = (AnyType) i.next();

						if (!(item instanceof NodeType)) {
							report_error(TypeError.step_conatins_atoms(null));
							return null; // unreach
						}
					}

					// check if we got a //
					if (xp.slashes() == 2) {
						rs = descendant_or_self_node(rs);

						if (rs.size() == 0)
							break;
					}

					// make result of previous step the new
					// focus
					set_focus(new Focus(rs));

					// do the step for all item in context
					rs = do_step(se);
				}
				// this is first step...
				// note... we may be called from upstream...
				// like in the expression sorbo/*[2] ... we may
				// be called to evaluate the 2... the caller
				// will iterate through the whole outer focus
				// for us
				else {
					// XXX ???
					if (xp.slashes() == 1) {
						rs = root_self_node();
						set_focus(new Focus(rs));

						rs = do_step(se);
					} else if (xp.slashes() == 2) {
						rs = root_self_node();

						rs = descendant_or_self_node(rs);

						set_focus(new Focus(rs));

						rs = do_step(se);
					} else
						rs = (ResultSequence) se.accept(this);
				}
			}
			// the expression is "/"
			else {
				assert xp.slashes() == 1;

				rs = root_self_node();
			}

			xp = xp.next();
		}

		// restore focus
		set_focus(original_focus);

		return rs;
	}

	/**
	 * visit a forward step expression
	 * 
	 * @param e
	 *            is the forward step.
	 * @return a new function
	 */
	public Object visit(ForwardStep e) {

		// get context node
		AnyType ci = focus().context_item();
		
		if (ci == null) 
			report_error(DynamicError.contextUndefined());

		if (!(ci instanceof NodeType))
			report_error(TypeError.ci_not_node(ci.string_type()));

		NodeType cn = (NodeType) ci;

		// get the nodes on the axis
		ForwardAxis axis = e.iterator();
		ResultBuffer rb = new ResultBuffer();
		axis.iterate(cn, rb, _dc.getLimitNode());
		// get all nodes in the axis, and principal node
		Pair arg = new Pair(axis.principal_node_kind().string_type(), rb.getSequence());

		// do the name test
		_param = arg;
		ResultSequence rs = (ResultSequence) e.node_test().accept(this);

		return rs;
	}

	/**
	 * visit a reverse step expression
	 * 
	 * @param e
	 *            is the reverse step.
	 * @return a new function
	 */
	// XXX unify with top
	public Object visit(ReverseStep e) {
		// get context node
		AnyType ci = focus().context_item();

		if (!(ci instanceof NodeType))
			report_error(TypeError.ci_not_node(ci.string_type()));

		NodeType cn = (NodeType) ci;

		// get the nodes on the axis
		ReverseAxis axis = e.iterator();
		
		ResultBuffer result = new ResultBuffer();
		// short for "gimme da parent"
		if (e.axis() == ReverseStep.DOTDOT) {
			new ParentAxis().iterate(cn, result, _dc.getLimitNode());
			return result.getSequence();
		}

		assert axis != null;

		axis.iterate(cn, result, null);
		// get all nodes in the axis, and principal node
		Pair arg = new Pair(axis.principal_node_kind().string_type(), result.getSequence());

		// do the name test
		_param = arg;
		ResultSequence rs = (ResultSequence) e.node_test().accept(this);

		return rs;
	}

	// XXX this routine sux
	private boolean name_test(NodeType node, QName name, String type) {
		// make sure principal node kind is the same
		if (node == null) {
			return false;
		}
		if (!type.equals(node.string_type())) {
			return false;
		}

		String test_prefix = name.prefix();

		// if unprefixed and principal node kind is element, set default
		// element namespace
		if (test_prefix == null && type.equals("element")) {
			// XXX make a new copy
			name = new QName(null, name.local());
			name.set_namespace(_sc.getDefaultNamespace());

			// if we actually have a namespace, pretend we do =D
			if (name.namespace() != null && name.namespace().length() > 0)
				test_prefix = "";
		}

		QName node_name = node.node_name();

		assert node_name != null;

		// make sure namespace matches
		String node_namespace = node_name.namespace();

		String test_namespace = null;
		if (name.expanded())
			test_namespace = name.namespace();

		// name test has no prefix
		if (test_prefix == null) {
			// ok no namespace... match
			if (node_namespace == null) {
			} else {
				return false;
			}
		}
		// name test has a prefix and is not wildcard
		// XXX AT THIS POINT ALL PREFIXES NEED TO BE RESOLVED!
		else if (!test_namespace.equals("*")) {
			// the node doesn't have a namespace... no match
			if (node_namespace == null) {
				return false;
			}
			// check namespaces
			else {
				if (node_namespace.equals(test_namespace)) {
					// namespace matches
				} else {
					return false;
				}
			}
		}

		// make sure local part matches
		// check for wildcard in localpart
		if (name.local().equals("*"))
			return true;

		// check if local part matches
		if (!name.local().equals(node_name.local())) {
			return false;
		}

		return true;
	}

	/**
	 * visit a name test expression
	 * 
	 * @param e
	 *            is thename test.
	 * @return a result sequence
	 */
	public Object visit(NameTest e) {
		QName name = e.name();

		// get the arguments
		Pair arg = (Pair) _param;
		String type = (String) arg._one;
		ResultSequence rs = (ResultSequence) arg._two;

		ResultBuffer rb = new ResultBuffer();
		
		for (Iterator i = rs.iterator(); i.hasNext();) {
			NodeType nt = (NodeType) i.next();

			// check if node passes name test
			if (name_test(nt, name, type))
				rb.add(nt);
		}
		rs = rb.getSequence();
		arg._two = rs;
		
		return rs;
	}

	/**
	 * visit variable reference
	 * 
	 * @param e
	 *            is the variable reference.
	 * @return a result sequence
	 */
	public Object visit(VarRef e) {
		ResultBuffer rs = new ResultBuffer();

		Object var = getVariable(e.name());

		assert var != null;

		if (var instanceof AnyType) {
		   rs.add((AnyType) var);
		}
		else if (var instanceof ResultSequence) {
		   rs.concat((ResultSequence) var);	
		}

		return rs.getSequence();
	}

	/**
	 * visit string literal.
	 * 
	 * @param e
	 *            is the string literal.
	 * @return a result sequence
	 */
	public Object visit(StringLiteral e) {
		return e.value();
	}

	/**
	 * visit integer literal.
	 * 
	 * @param e
	 *            is the integer literal.
	 * @return a result sequence
	 */
	public Object visit(IntegerLiteral e) {
		return e.value();
	}

	/**
	 * visit double literal.
	 * 
	 * @param e
	 *            is the double literal.
	 * @return a result sequence
	 */
	public Object visit(DoubleLiteral e) {
		return e.value();
	}

	/**
	 * visit decimal literal.
	 * 
	 * @param e
	 *            is the decimal literal.
	 * @return a result sequence
	 */
	public Object visit(DecimalLiteral e) {
		ResultBuffer rs = new ResultBuffer();

		rs.add(e.value());
		return rs.getSequence();
	}

	/**
	 * visit parent expression.
	 * 
	 * @param e
	 *            is the parent expression.
	 * @return a new function
	 */
	public Object visit(ParExpr e) {
		return do_expr(e.iterator());
	}

	/**
	 * visit context item expression.
	 * 
	 * @param e
	 *            is the context item expression.
	 * @return a result sequence
	 */
	public Object visit(CntxItemExpr e) {
		ResultBuffer rs = new ResultBuffer();

		AnyType contextItem = focus().context_item();
		if (contextItem == null) {
			report_error(DynamicError.contextUndefined());
		}
		rs.add(contextItem);
		return rs.getSequence();
	}

	/**
	 * visit function call.
	 * 
	 * @param e
	 *            is the function call.
	 * @return a new function or null
	 */
	public Object visit(FunctionCall e) {
		ArrayList args = new ArrayList();

		for (Iterator i = e.iterator(); i.hasNext();) {
			Expr arg = (Expr) i.next();
			// each argument will produce a result sequence
			args.add((ResultSequence)arg.accept(this));
		}

		try {
			Function function = e.function();
			if (function == null) {
				function = _sc.resolveFunction(e.name().asQName(), args.size());
				e.set_function(function);
			}
			return function.evaluate(args, _ec);
		} catch (DynamicError err) {
			report_error(err);
			return null; // unreach
		}
	}

	/**
	 * visit single type.
	 * 
	 * @param e
	 *            is the single type.
	 * @return null
	 */
	public Object visit(SingleType e) {
		return null;
	}

	/**
	 * visit sequence type.
	 * 
	 * @param e
	 *            is the sequence type.
	 * @return null
	 */
	public Object visit(SequenceType e) {
		ItemType it = e.item_type();

		if (it != null)
			it.accept(this);

		return null;
	}

	/**
	 * visit item type.
	 * 
	 * @param e
	 *            is the item type.
	 * @return null
	 */
	public Object visit(ItemType e) {

		switch (e.type()) {
		case ItemType.ITEM:
			break;
		case ItemType.QNAME:

			boolean ok = false;
			TypeModel model = _sc.getTypeModel();
			if (model != null) {
				ok = _sc.getTypeModel().lookupType(e.qname().namespace(), e.qname().local()) != null;
			}
			if (! ok ) {
				ok = BuiltinTypeLibrary.BUILTIN_TYPES.lookupType(e.qname().namespace(), e.qname().local()) != null;
			}
			if (! ok) report_error(new StaticTypeNameError("Type not defined: "
					+ e.qname().string()));
			
			ResultSequence arg = (ResultSequence) ((Pair) _param)._two;
			((Pair) _param)._two = item_test(arg, e.qname());
			break;

		case ItemType.KINDTEST:
			((Pair) _param)._two = e.kind_test().accept(this);
			break;
		}

		return null;
	}

	private ResultSequence item_test(ResultSequence rs, QName qname) {
		ResultBuffer rb = new ResultBuffer();
		for (Iterator i = rs.iterator(); i.hasNext();) {
			AnyType item = (AnyType) i.next();
			
			if (item instanceof NodeType) {
				NodeType node = ((NodeType)item);
				if (derivesFrom(node, qname)) rb.add(node);
			} else {
				// atomic of some sort
				if (qname.equals(ANY_ATOMIC_TYPE)) {
					rb.add(item);
					continue; // match !
				}
				
				final AnyAtomicType aat = makeAtomic(qname);
				if (aat.getClass().isInstance(item)) rb.add(item);
				
				// fall through => non-match
			}
		}
		return rb.getSequence();
	}

    private ResultSequence kind_test(ResultSequence rs, Class kind) {
    	ResultBuffer rb = new ResultBuffer();
		for (Iterator i = rs.iterator(); i.hasNext();) {
			Item item = (Item) i.next();
			if (kind.isInstance(item))
				rb.add(item);
		}
		return rb.getSequence();
	}

	/**
	 * visit any kind test.
	 * 
	 * @param e
	 *            is the any kind test.
	 * @return a new function
	 */
	public Object visit(AnyKindTest e) {
		ResultSequence arg = (ResultSequence) ((Pair) _param)._two;

		return kind_test(arg, NodeType.class);
	}

	/**
	 * visit document test.
	 * 
	 * @param e
	 *            is the document test.
	 * @return result sequence
	 */
	public Object visit(DocumentTest e) {
		ResultSequence arg = (ResultSequence) ((Pair) _param)._two;
		int type = e.type();

		// filter doc nodes
		ResultSequence rs = kind_test(arg, DocType.class);

		if (type == DocumentTest.NONE)
			return rs;

		// for all docs, find the ones with exactly one element, and do
		// the element test
		for (Iterator i = rs.iterator(); i.hasNext();) {
			DocType doc = (DocType) i.next();
			int elem_count = 0;
			ElementType elem = null;

			// make sure doc has only 1 element
			NodeList children = doc.node_value().getChildNodes();
			for (int j = 0; j < children.getLength(); j++) {
				Node child = children.item(j);

				// bingo
				if (child.getNodeType() == Node.ELEMENT_NODE) {
					elem_count++;

					if (elem_count > 1)
						break;

					elem = new ElementType((Element) child, _sc.getTypeModel());
				}
			}

			// this doc is no good... send him to hell
			if (elem_count != 1) {
				i.remove();
				continue;
			}

			assert elem != null;

			// setup parameter for element test
			ResultSequence res = new ResultBuffer.SingleResultSequence(elem);
			_param = new Pair("element", res);

			// do name test
			res = null;
			if (type == DocumentTest.ELEMENT)
				res = (ResultSequence) e.elem_test().accept(this);
			else if (type == DocumentTest.SCHEMA_ELEMENT)
				res = (ResultSequence) e.schema_elem_test().accept(this);
			else
				assert false;

			// check if element survived nametest
			if (res.size() != 1)
				i.remove();
		}

		return rs;
	}

	/**
	 * visit text test.
	 * 
	 * @param e
	 *            is the text test.
	 * @return a new function
	 */
	public Object visit(TextTest e) {
		ResultSequence arg = (ResultSequence) ((Pair) _param)._two;

		((Pair) _param)._two = kind_test(arg, TextType.class);
		return ((Pair) _param)._two;
	}

	/**
	 * visit comment test.
	 * 
	 * @param e
	 *            is the text test.
	 * @return a new function
	 */
	public Object visit(CommentTest e) {
		ResultSequence arg = (ResultSequence) ((Pair) _param)._two;

		return kind_test(arg, CommentType.class);
	}

	/**
	 * visit PI test.
	 * 
	 * @param e
	 *            is the PI test.
	 * @return a argument
	 */
	public Object visit(PITest e) {
		ResultSequence arg = (ResultSequence) ((Pair) _param)._two;

		String pit_arg = e.arg();

		// match any pi
		if (pit_arg == null)
			return kind_test(arg, PIType.class);

    	ResultBuffer rb = new ResultBuffer();
		for (Iterator i = arg.iterator(); i.hasNext();) {
			AnyType item = (AnyType) i.next();

			// match PI
			if (item instanceof PIType) {
				PIType pi = (PIType) item;

				// match target
				if (pit_arg.equals(pi.value().getTarget()))
					rb.add(pi);
			}
		}
		arg = rb.getSequence();
		((Pair) _param)._two = arg;
		return arg;
	}

	/**
	 * visit attribute test.
	 * 
	 * @param e
	 *            is the attribute test.
	 * @return a result sequence
	 */
	public Object visit(AttributeTest e) {
		// filter out all attrs
		ResultSequence rs = kind_test((ResultSequence) ((Pair) _param)._two,
				AttrType.class);

		ResultBuffer rb = new ResultBuffer();
		
		QName name = e.name();
		QName type = e.type();

		for (Iterator i = rs.iterator(); i.hasNext();) {
			NodeType node = (NodeType) i.next();
			// match the name if it's not a wild card
			if (name != null && !e.wild()) {
				if (!name_test(node, name, "attribute"))
					continue;
			}
			// match the type
			if (type != null) {
				// check if element derives from
				if (! derivesFrom(node, type))
					continue;
			}
			rb.add(node);
		}
		((Pair) _param)._two = rb.getSequence();
		return ((Pair) _param)._two;
	}

	/**
	 * visit schema attribute test.
	 * 
	 * @param e
	 *            is the schema attribute test.
	 * @return a result sequence
	 */
	public Object visit(SchemaAttrTest e) {
		// filter out all attrs
		ResultSequence rs = kind_test((ResultSequence) ((Pair) _param)._two,
				AttrType.class);

		// match the name
		QName name = e.arg();
		for (Iterator i = rs.iterator(); i.hasNext();) {
			if (!name_test((NodeType) i.next(), name, "attribute"))

				i.remove();
		}

		// check the type
		TypeDefinition et = _sc.getTypeModel().lookupAttributeDeclaration(name.namespace(), name.local());
		for (Iterator i = rs.iterator(); i.hasNext();) {
			NodeType node = (NodeType) i.next();

			if (! derivesFrom(node, et))
				i.remove();

		}

		return rs;
	}

	/**
	 * visit element test.
	 * 
	 * @param e
	 *            is the element test.
	 * @return a result sequence
	 */
	public Object visit(ElementTest e) {
		// filter out all elements
		ResultSequence rs = kind_test((ResultSequence) ((Pair) _param)._two,
				ElementType.class);

		// match the name if it's not a wild card
		ResultBuffer rb = new ResultBuffer();
		QName nameTest = e.name();
		QName typeTest = e.type();
		for (Iterator i = rs.iterator(); i.hasNext();) {
			NodeType node = (NodeType) i.next();

			if (nameTest != null && !e.wild()) {
				// skip if there's a name test and the name does not match
				if (!name_test((ElementType) node, nameTest, "element")) continue;
			}
			if (typeTest != null) {
				// check if element derives from
				if (! derivesFrom(node, typeTest)) continue;
				
				// nilled may be true or false
				if (! e.qmark()) {
					XSBoolean nilled = (XSBoolean) node.nilled().first();
					if (nilled.value()) continue;
				}
			}
			rb.add(node);
		}
		((Pair) _param)._two = rb.getSequence();
		return ((Pair) _param)._two;
	}

	/**
	 * visit schema element test.
	 * 
	 * @param e
	 *            is the schema element test.
	 * @return a result sequence
	 */
	public Object visit(SchemaElemTest e) {
		// filter out all elements
		ResultSequence rs = kind_test((ResultSequence) ((Pair) _param)._two,
				ElementType.class);

		// match the name
		// XXX substitution groups
		QName name = e.name();
		for (Iterator i = rs.iterator(); i.hasNext();) {
			if (!name_test((ElementType) i.next(), name, "element"))

				i.remove();
		}

		// check the type
		TypeDefinition et = _sc.getTypeModel().lookupElementDeclaration(name.namespace(), name.local());
		for (Iterator i = rs.iterator(); i.hasNext();) {
			NodeType node = (NodeType) i.next();

			if (! derivesFrom(node, et)) {
				i.remove();
				continue;
			}

			XSBoolean nilled = (XSBoolean) node.nilled().first();
			// XXX or, in schema it is nillable
			if (nilled.value())
				i.remove();
		}

		return rs;
	}

	private boolean predicate_truth(ResultSequence rs) {
		// rule 1 of spec... if numeric type:
		// if num eq position then true else false
		if (rs.size() == 1) {
			AnyType at = (AnyType) rs.item(0);

			if (at instanceof NumericType) {
				try {
					return FsEq.fs_eq_fast(at, new XSInteger(BigInteger.valueOf(focus().position())), _dc);
				} catch (DynamicError err) {
					report_error(err);

					// unreach
					assert false;
					return false;
				}
			}
		}

		// rule 2
		XSBoolean ret = effective_boolean_value(rs);

		return ret.value();
	}

	// do the predicate for all items in focus
	private ResultSequence do_predicate(Collection exprs) {
		ResultBuffer rs = new ResultBuffer();

		Focus focus = focus();
		int original_cp = focus.position();

		// optimization
		// check if predicate is single numeric constant
		if (exprs.size() == 1) {
			Expr expr = (Expr) exprs.iterator().next();

			if (expr instanceof XPathExpr) {
				XPathExpr xpe = (XPathExpr) expr;
				if (xpe.next() == null && xpe.slashes() == 0
						&& xpe.expr() instanceof FilterExpr) {
					FilterExpr fex = (FilterExpr) xpe.expr();
					if (fex.primary() instanceof IntegerLiteral) {
						int pos = (((IntegerLiteral) fex.primary()).value()
								.int_value()).intValue();

						if (pos <= focus.last() && pos > 0) {
							focus.set_position(pos);
							rs.add(focus.context_item());
						}
						focus.set_position(original_cp);
						return rs.getSequence();
					}
				}
			}
		}

		// go through all elements
		while (true) {
			// do the predicate
			// XXX saxon doesn't allow for predicates to have
			// commas... but XPath 2.0 spec seems to do
			ResultSequence res = do_expr(exprs.iterator());

			// if predicate is true, the context item is definitely
			// in the sequence
			if (predicate_truth(res))
				rs.add(focus().context_item());

			if (!focus.advance_cp())
				break;

		}

		// restore
		focus.set_position(original_cp);

		return rs.getSequence();
	}

	/**
	 * visit axis step.
	 * 
	 * @param e
	 *            is the axis step.
	 * @return a result sequence
	 */
	public Object visit(AxisStep e) {
		ResultSequence rs = (ResultSequence) e.step().accept(this);

		if (e.predicate_count() == 0)
			return rs;

		// I take it predicates are logical ANDS...
		Focus original_focus = focus();

		// go through all predicates
		for (Iterator i = e.iterator(); i.hasNext();) {
			// empty results... get out of here ? XXX
			if (rs.size() == 0)
				break;

			set_focus(new Focus(rs));
			rs = do_predicate((Collection) i.next());

		}

		// restore focus [context switching ;D ]
		set_focus(original_focus);
		return rs;
	}

	/**
	 * visit filter expression
	 * 
	 * @param e
	 *            is the filter expression.
	 * @return a result sequence
	 */
	// XXX unify with top ?
	public Object visit(FilterExpr e) {
		ResultSequence rs = (ResultSequence) e.primary().accept(this);

		// if no predicates are present, then the result is the same as
		// the primary expression
		if (e.predicate_count() == 0)
			return rs;

		Focus original_focus = focus();

		// go through all predicates
		for (Iterator i = e.iterator(); i.hasNext();) {
			if (rs.size() == 0)
				break;

			set_focus(new Focus(rs));
			rs = do_predicate((Collection) i.next());

		}

		// restore focus [context switching ;D ]
		set_focus(original_focus);
		return rs;
	}

}
