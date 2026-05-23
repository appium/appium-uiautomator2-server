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
 *     Jesper Steen Moller  - bug 340933 - Migrate to new XPath2 API
 *     Jesper Steen Moller - bug 343804 - Updated API information
 *     Lukasz Wycisk - bug 361802 - Default variable namespace � no namespace
 *     
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.xml.XMLConstants;

import org.eclipse.wst.xml.xpath2.api.Function;
import org.eclipse.wst.xml.xpath2.processor.ast.XPath;
import org.eclipse.wst.xml.xpath2.processor.internal.ReverseAxis;
import org.eclipse.wst.xml.xpath2.processor.internal.StaticAttrNameError;
import org.eclipse.wst.xml.xpath2.processor.internal.StaticContextAdapter;
import org.eclipse.wst.xml.xpath2.processor.internal.StaticElemNameError;
import org.eclipse.wst.xml.xpath2.processor.internal.StaticFunctNameError;
import org.eclipse.wst.xml.xpath2.processor.internal.StaticNameError;
import org.eclipse.wst.xml.xpath2.processor.internal.StaticNsNameError;
import org.eclipse.wst.xml.xpath2.processor.internal.StaticTypeNameError;
import org.eclipse.wst.xml.xpath2.processor.internal.StaticVarNameError;
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
import org.eclipse.wst.xml.xpath2.processor.internal.ast.UnExpr;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.UnionExpr;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.VarExprPair;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.VarRef;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.XPathExpr;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.XPathNode;
import org.eclipse.wst.xml.xpath2.processor.internal.ast.XPathVisitor;
import org.eclipse.wst.xml.xpath2.processor.internal.types.QName;
import org.eclipse.wst.xml.xpath2.processor.internal.types.SimpleAtomicItemTypeImpl;
import org.eclipse.wst.xml.xpath2.processor.internal.types.builtin.BuiltinTypeDefinition;
import org.eclipse.wst.xml.xpath2.processor.internal.types.builtin.BuiltinTypeLibrary;

/**
 * This class resolves static names.
 */
public class StaticNameResolver implements XPathVisitor, StaticChecker {
	static class DummyError extends Error {

		/**
		 * 
		 */
		private static final long serialVersionUID = 3898564402981741950L;
	}

	private org.eclipse.wst.xml.xpath2.api.StaticContext _sc;
	private StaticNameError _err;
	
	private Set<javax.xml.namespace.QName> _resolvedFunctions = new HashSet<javax.xml.namespace.QName>();
	private Set<String> _axes = new HashSet<String>();
	private Set<javax.xml.namespace.QName> _freeVariables = new HashSet<javax.xml.namespace.QName>();
	/**
	 * Constructor for static name resolver
	 * 
	 * @param sc
	 *            is the static context.
	 * @since 2.0
	 */
	public StaticNameResolver(final org.eclipse.wst.xml.xpath2.processor.StaticContext sc) {
		_sc = new StaticContextAdapter(sc);
		_err = null;
	}

	/**
	 * @since 2.0
	 */
	public StaticNameResolver(org.eclipse.wst.xml.xpath2.api.StaticContext context) {
		_sc = context;
		_err = null;
	}

	class VariableScope {
		public VariableScope(QName name, org.eclipse.wst.xml.xpath2.api.typesystem.ItemType typeDef, VariableScope nextScope) {
			this.name = name;
			this.typeDef = typeDef;
			this.nextScope = nextScope;
		}
		final public QName name;
		final public org.eclipse.wst.xml.xpath2.api.typesystem.ItemType typeDef;
		final public VariableScope nextScope; 
	}	
	
	/**
	 * @since 2.0
	 */
	public Set<String> getAxes() {
		return _axes;
	}
	
	/**
	 * @since 2.0
	 */
	public Set<javax.xml.namespace.QName> getFreeVariables() {
		return _freeVariables;
	}
	
	/**
	 * @since 2.0
	 */
	public Set<javax.xml.namespace.QName> getResolvedFunctions() {
		return _resolvedFunctions;
	}
	
	private VariableScope _innerScope = null;
	private boolean _rootUsed;

	private org.eclipse.wst.xml.xpath2.api.typesystem.ItemType getVariableType(QName name) {
		VariableScope scope = _innerScope;
		while (scope != null) {
			if (name.equals(scope.name)) return scope.typeDef;
			scope = scope.nextScope;
		}
		return _sc.getInScopeVariables().getVariableType(name.asQName());
	}

	private boolean isVariableCaptured(QName name) {
		VariableScope scope = _innerScope;
		while (scope != null) {
			if (name.equals(scope.name)) return true;
			scope = scope.nextScope;
		}
		return false;
	}

	private boolean isVariableInScope(QName name) {
		return isVariableCaptured(name) || _sc.getInScopeVariables().isVariablePresent(name.asQName());
	}
	
	private void popScope() {
		if (_innerScope == null) throw new IllegalStateException("Unmatched scope pop");
		_innerScope = _innerScope.nextScope;
	}

	private void pushScope(QName var, BuiltinTypeDefinition xsAnytype) {
		_innerScope = new VariableScope(var, new SimpleAtomicItemTypeImpl(xsAnytype), _innerScope);		
	}
 
	private boolean expandQName(QName name, String def, boolean allowWildcards) {
		String prefix = name.prefix();

		if ("*".equals(prefix)) {
			if (! allowWildcards)
				return false;
			name.set_namespace("*");
			return true;
		}

		if (prefix == null || XMLConstants.DEFAULT_NS_PREFIX.equals(prefix)) {
			name.set_namespace(def);
			return true;
		}

		// At this point, we know we have a non-null prefix, so look it up
		String namespaceURI = _sc.getNamespaceContext().getNamespaceURI(prefix);
		if (XMLConstants.NULL_NS_URI.equals(namespaceURI))
			return false;

		name.set_namespace(namespaceURI);
		return true;
	}

	private boolean expandItemQName(QName name) {
		return expandQName(name, _sc.getDefaultNamespace(), true);
	}

	private boolean expandVarQName(QName name) {
		return expandQName(name, null, false);
	}

	/**
	 * Expands a qname and uses the default function namespace if unprefixed.
	 * 
	 * @param name
	 *            qname to expand.
	 * @return true on success.
	 */
	private boolean expandFunctionQName(QName name) {
		return expandQName(name, _sc.getDefaultFunctionNamespace(), false);
	}

	/**
	 * Expands a qname and uses the default type/element namespace if
	 * unprefixed.
	 * 
	 * @param name
	 *            qname to expand.
	 * @return true on success.
	 */
	private boolean expandItemTypeQName(QName name) {
		return expandQName(name, _sc.getDefaultNamespace(), false);
	}
	
	// the problem is that visistor interface does not throw exceptions...
	// so we get around it ;D
	private void reportError(StaticNameError err) {
		_err = err;
		throw new DummyError();
	}

	private void reportBadPrefix(String prefix) {
		reportError(StaticNsNameError.unknown_prefix(prefix));
	}

	/**
	 * Check the XPath node.
	 * 
	 * @param node
	 *            is the XPath node to check.
	 * @throws StaticError
	 *             static error.
	 */
	public void check(XPathNode node) throws StaticError {
		try {
			node.accept(this);
		} catch (DummyError e) {
			throw _err;
		}
	}

	/**
	 * Validate an XPath by visiting all the nodes.
	 * 
	 * @param xp
	 *            is the XPath.
	 * @return null.
	 */
	public Object visit(XPath xp) {
		for (Iterator i = xp.iterator(); i.hasNext();) {
			Expr e = (Expr) i.next();

			e.accept(this);
		}

		return null;
	}

	// does a for and a quantified expression
	// takes the iterator for var expr paris
	private void doForExpr(Iterator iter, Expr expr) {
		int scopes = 0;

		// add variables to scope and check the binding sequence
		while (iter.hasNext()) {
			VarExprPair pair = (VarExprPair) iter.next();

			QName var = pair.varname();
			if (!expandVarQName(var))
				reportBadPrefix(var.prefix());

			Expr e = pair.expr();

			e.accept(this);

			pushScope(var, BuiltinTypeLibrary.XS_ANYTYPE);
			scopes++;
		}

		expr.accept(this);

		// kill the scopes
		for (int i = 0; i < scopes; i++)
			popScope();
	}

	/**
	 * Validate a for expression.
	 * 
	 * @param fex
	 *            is the for expression.
	 * @return null.
	 */
	public Object visit(ForExpr fex) {

		doForExpr(fex.iterator(), fex.expr());

		return null;
	}

	/**
	 * Validate a quantified expression.
	 * 
	 * @param qex
	 *            is the quantified expression.
	 * @return null.
	 */
	public Object visit(QuantifiedExpr qex) {
		// lets cheat
		doForExpr(qex.iterator(), qex.expr());

		return null;
	}

	private void visitExprs(Iterator i) {
		while (i.hasNext()) {
			Expr e = (Expr) i.next();

			e.accept(this);
		}
	}

	/**
	 * Validate an if expression.
	 * 
	 * @param ifex
	 *            is the if expression.
	 * @return null.
	 */
	public Object visit(IfExpr ifex) {

		visitExprs(ifex.iterator());

		ifex.then_clause().accept(this);

		ifex.else_clause().accept(this);

		return null;
	}

	/**
	 * Validate a binary expression by checking its left and right children.
	 * 
	 * @param name
	 *            is the name of the binary expression.
	 * @param e
	 *            is the expression itself.
	 */
	public void printBinExpr(String name, BinExpr e) {
		e.left().accept(this);
		e.right().accept(this);
	}

	/**
	 * Validate an OR expression.
	 * 
	 * @param orex
	 *            is the expression.
	 * @return null.
	 */
	public Object visit(OrExpr orex) {
		printBinExpr("OR", orex);
		return null;
	}

	/**
	 * Validate an AND expression.
	 * 
	 * @param andex
	 *            is the expression.
	 * @return null.
	 */
	public Object visit(AndExpr andex) {
		printBinExpr("AND", andex);
		return null;
	}

	/**
	 * Validate a comparison expression.
	 * 
	 * @param cmpex
	 *            is the expression.
	 * @return null.
	 */
	public Object visit(CmpExpr cmpex) {
		printBinExpr("CMP" + cmpex.type(), cmpex);
		return null;
	}

	/**
	 * Validate a range expression.
	 * 
	 * @param rex
	 *            is the expression.
	 * @return null.
	 */
	public Object visit(RangeExpr rex) {
		printBinExpr("RANGE", rex);
		return null;
	}

	/**
	 * Validate an additon expression.
	 * 
	 * @param addex
	 *            is the expression.
	 * @return null.
	 */
	public Object visit(AddExpr addex) {
		printBinExpr("ADD", addex);
		return null;
	}

	/**
	 * Validate a subtraction expression.
	 * 
	 * @param subex
	 *            is the expression.
	 * @return null.
	 */
	public Object visit(SubExpr subex) {
		printBinExpr("SUB", subex);
		return null;
	}

	/**
	 * Validate a multiplication expression.
	 * 
	 * @param mulex
	 *            is the expression.
	 * @return null.
	 */
	public Object visit(MulExpr mulex) {
		printBinExpr("MUL", mulex);
		return null;
	}

	/**
	 * Validate a division expression.
	 * 
	 * @param mulex
	 *            is the expression.
	 * @return null.
	 */
	public Object visit(DivExpr mulex) {
		printBinExpr("DIV", mulex);
		return null;
	}

	/**
	 * Validate an integer divison expression.
	 * 
	 * @param mulex
	 *            is the expression.
	 * @return null.
	 */
	public Object visit(IDivExpr mulex) {
		printBinExpr("IDIV", mulex);
		return null;
	}

	/**
	 * Validate a mod expression.
	 * 
	 * @param mulex
	 *            is the expression.
	 * @return null.
	 */
	public Object visit(ModExpr mulex) {
		printBinExpr("MOD", mulex);
		return null;
	}

	/**
	 * Validate a union expression.
	 * 
	 * @param unex
	 *            is the expression.
	 * @return null.
	 */
	public Object visit(UnionExpr unex) {
		printBinExpr("UNION", unex);
		return null;
	}

	/**
	 * Validate a piped expression.
	 * 
	 * @param pipex
	 *            is the expression.
	 * @return null.
	 */
	public Object visit(PipeExpr pipex) {
		printBinExpr("PIPE", pipex);
		return null;
	}

	/**
	 * Validate an intersection expression.
	 * 
	 * @param iexpr
	 *            is the expression.
	 * @return null.
	 */
	public Object visit(IntersectExpr iexpr) {
		printBinExpr("INTERSECT", iexpr);
		return null;
	}

	/**
	 * Validate an except expression.
	 * 
	 * @param eexpr
	 *            is the expression.
	 * @return null.
	 */
	public Object visit(ExceptExpr eexpr) {
		printBinExpr("INT_EXCEPT", eexpr);
		return null;
	}

	/**
	 * Validate an 'instance of' expression.
	 * 
	 * @param ioexp
	 *            is the expression.
	 * @return null.
	 */
	public Object visit(InstOfExpr ioexp) {
		printBinExpr("INSTANCEOF", ioexp);
		return null;
	}

	/**
	 * Validate a 'treat as' expression.
	 * 
	 * @param taexp
	 *            is the expression.
	 * @return null.
	 */
	public Object visit(TreatAsExpr taexp) {
		printBinExpr("TREATAS", taexp);
		return null;
	}

	/**
	 * Validate a castable expression.
	 * 
	 * @param cexp
	 *            is the expression.
	 * @return null.
	 */
	public Object visit(CastableExpr cexp) {
		printBinExpr("CASTABLE", cexp);
		return null;
	}

	/**
	 * Validate a cast expression.
	 * 
	 * @param cexp
	 *            is the expression.
	 * @return null.
	 */
	public Object visit(CastExpr cexp) {
		printBinExpr("CAST", cexp);
		
		SingleType st = (SingleType) cexp.right();
		QName type = st.type();
		
		javax.xml.namespace.QName qName = type.asQName();
		Function f = _sc.resolveFunction(qName, 1);
		if (f == null)
			reportError(new StaticFunctNameError("Type does not exist: "
					+ type.toString()));
		cexp.set_function(f);
		_resolvedFunctions.add(qName);

		return null;
	}

	/**
	 * Validate a unary expression by checking its one child.
	 * 
	 * @param name
	 *            is the name of the expression.
	 * @param e
	 *            is the expression itself.
	 */
	public void printUnExpr(String name, UnExpr e) {
		e.arg().accept(this);

	}

	/**
	 * Validate a minus expression.
	 * 
	 * @param e
	 *            is the expression.
	 * @return null.
	 */
	public Object visit(MinusExpr e) {
		printUnExpr("MINUS", e);
		return null;
	}

	/**
	 * Validate a plus expression.
	 * 
	 * @param e
	 *            is the expression.
	 * @return null.
	 */
	public Object visit(PlusExpr e) {
		printUnExpr("PLUS", e);
		return null;
	}

	/**
	 * Validate an xpath expression.
	 * 
	 * @param e
	 *            is the expression.
	 * @return null.
	 */
	public Object visit(XPathExpr e) {
		XPathExpr xp = e;
		boolean firstStep = true;
		
		while (xp != null) {
			if (firstStep && xp.slashes() != 0)
				_rootUsed = true;
			
			firstStep = false;
			StepExpr se = xp.expr();

			if (se != null)
				se.accept(this);

			xp = xp.next();
		}
		return null;
	}

	/**
	 * Validate a forward step.
	 * 
	 * @param e
	 *            is the expression.
	 * @return null.
	 */
	public Object visit(ForwardStep e) {
		e.node_test().accept(this);

		_axes.add(e.iterator().name());
		
		return null;
	}

	/**
	 * Validate a reverse step.
	 * 
	 * @param e
	 *            is the expression.
	 * @return null.
	 */
	public Object visit(ReverseStep e) {

		NodeTest nt = e.node_test();
		if (nt != null)
			nt.accept(this);
		
		ReverseAxis iterator = e.iterator();
		if (iterator != null)
			_axes.add(iterator.name());

		return null;
	}

	/**
	 * Validate a name test.
	 * 
	 * @param e
	 *            is the expression.
	 * @return null.
	 */
	public Object visit(NameTest e) {
		QName name = e.name();

		if (!expandItemQName(name))
			reportBadPrefix(name.prefix());

		return null;
	}

	/**
	 * Validate a variable reference.
	 * 
	 * @param e
	 *            is the expression.
	 * @return null.
	 */
	public Object visit(VarRef e) {
		QName var = e.name();
		
		if (!expandVarQName(var))
			reportBadPrefix(var.prefix());

		if (! isVariableInScope(var))
			reportError(new StaticNameError(StaticNameError.NAME_NOT_FOUND));
		
		if (getVariableType(var) == null)
			reportError(new StaticNameError(StaticNameError.NAME_NOT_FOUND));

		// The variable is good. If it was not captured, it must be referring to an external var
		if (! isVariableCaptured(var)) _freeVariables.add(var.asQName());
		
		return null;
	}

	/**
	 * Validate a string literal.
	 * 
	 * @param e
	 *            is the expression.
	 * @return null.
	 */
	public Object visit(StringLiteral e) {
		return null;
	}

	/**
	 * Validate an integer literal.
	 * 
	 * @param e
	 *            is the expression.
	 * @return null.
	 */
	public Object visit(IntegerLiteral e) {
		return null;
	}

	/**
	 * Validate a double literal.
	 * 
	 * @param e
	 *            is the expression.
	 * @return null.
	 */
	public Object visit(DoubleLiteral e) {
		return null;
	}

	/**
	 * Validate a decimal literal.
	 * 
	 * @param e
	 *            is the expression.
	 * @return null.
	 */
	public Object visit(DecimalLiteral e) {
		return null;
	}

	/**
	 * Validate a parenthesized expression.
	 * 
	 * @param e
	 *            is the expression.
	 * @return null.
	 */
	public Object visit(ParExpr e) {
		visitExprs(e.iterator());
		return null;
	}

	/**
	 * Validate a context item expression.
	 * 
	 * @param e
	 *            is the expression.
	 * @return null.
	 */
	public Object visit(CntxItemExpr e) {
		return null;
	}

	/**
	 * Validate a function call.
	 * 
	 * @param e
	 *            is the expression.
	 * @return null.
	 */
	public Object visit(FunctionCall e) {
		QName name = e.name();

		if (!expandFunctionQName(name))
			reportBadPrefix(name.prefix());

		javax.xml.namespace.QName qName = name.asQName();
		Function f = _sc.resolveFunction(qName, e.arity());
		if (f == null)
			reportError(new StaticFunctNameError("Function does not exist: "
					+ name.string() + " arity: " + e.arity()));
		e.set_function(f);
		_resolvedFunctions.add(qName);
		
		visitExprs(e.iterator());
		return null;
	}

	/**
	 * Validate a single type.
	 * 
	 * @param e
	 *            is the expression.
	 * @return null.
	 */
	public Object visit(SingleType e) {
		QName type = e.type();
		if (!expandItemTypeQName(type))
			reportBadPrefix(type.prefix());

		return null;
	}

	/**
	 * Validate a sequence type.
	 * 
	 * @param e
	 *            is the expression.
	 * @return null.
	 */
	public Object visit(SequenceType e) {
		ItemType it = e.item_type();

		if (it != null)
			it.accept(this);

		return null;
	}

	/**
	 * Validate an item type.
	 * 
	 * @param e
	 *            is the expression.
	 * @return null.
	 */
	public Object visit(ItemType e) {

		switch (e.type()) {
		case ItemType.ITEM:
			break;
		case ItemType.QNAME:
			QName type = e.qname();
			if (!expandItemTypeQName(type))
				reportBadPrefix(type.prefix());

			if (BuiltinTypeLibrary.BUILTIN_TYPES.lookupType(e.qname().namespace(), e.qname().local()) == null) {
				if (_sc.getTypeModel() == null || _sc.getTypeModel().lookupType(e.qname().namespace(), e.qname().local()) == null)
					reportError(new StaticTypeNameError("Type not defined: "
							+ e.qname().string()));
			}
			break;

		case ItemType.KINDTEST:
			e.kind_test().accept(this);
			break;
		}

		return null;
	}

	/**
	 * Validate an any kind test.
	 * 
	 * @param e
	 *            is the expression.
	 * @return null.
	 */
	public Object visit(AnyKindTest e) {
		return null;
	}

	/**
	 * Validate a document test.
	 * 
	 * @param e
	 *            is the expression.
	 * @return null.
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
	 * Validate a text test.
	 * 
	 * @param e
	 *            is the expression.
	 * @return null.
	 */
	public Object visit(TextTest e) {
		return null;
	}

	/**
	 * Validate a comment test.
	 * 
	 * @param e
	 *            is the expression.
	 * @return null.
	 */
	public Object visit(CommentTest e) {
		return null;
	}

	/**
	 * Validate a processing instructing test.
	 * 
	 * @param e
	 *            is the expression.
	 * @return null.
	 */
	public Object visit(PITest e) {
		String arg = e.arg();
		if (arg == null)
			arg = "";

		return null;
	}

	/**
	 * Validate an attribute test.
	 * 
	 * @param e
	 *            is the expression.
	 * @return null.
	 */
	// XXX NO CHECK ?
	public Object visit(AttributeTest e) {
		QName name = e.name();
		if (name != null) {
			if (!expandItemQName(name))
				reportBadPrefix(name.prefix());
		}
		
		name = e.type();
		if (name != null) {
			if (!expandItemTypeQName(name))
				reportBadPrefix(name.prefix());
		}
		return null;
	}

	/**
	 * Validate a schema attribute test.
	 * 
	 * @param e
	 *            is the expression.
	 * @return null.
	 */
	public Object visit(SchemaAttrTest e) {
		QName name = e.arg();

		if (!expandItemQName(name))
			reportBadPrefix(name.prefix());

		if (_sc.getTypeModel().lookupAttributeDeclaration(name.namespace(), name.local()) == null)
			reportError(new StaticAttrNameError("Attribute not decleared: "
					+ name.string()));

		return null;
	}

	/**
	 * Validate an element test.
	 * 
	 * @param e
	 *            is the expression.
	 * @return null.
	 */
	// XXX NO SEMANTIC CHECK?!
	public Object visit(ElementTest e) {
		if (e.name() != null) {
			if (!expandItemTypeQName(e.name()))
				reportBadPrefix(e.name().prefix());
		}
		
		if (e.type() != null) {
			if (!expandItemTypeQName(e.type()))
				reportBadPrefix(e.type().prefix());
		}
		return null;
	}

	/**
	 * Validate a schema element test.
	 * 
	 * @param e
	 *            is the expression.
	 * @return null.
	 */
	public Object visit(SchemaElemTest e) {
		QName elem = e.name();

		if (!expandItemQName(elem))
			reportBadPrefix(elem.prefix());

		if (_sc.getTypeModel().lookupElementDeclaration(elem.namespace(), elem.local()) == null)
			reportError(new StaticElemNameError("Element not declared: "
					+ elem.string()));
		return null;
	}

	private void visitCollExprs(Iterator i) {
		while (i.hasNext()) {
			Collection exprs = (Collection) i.next();

			visitExprs(exprs.iterator());
		}
	}

	/**
	 * Validate an axis step.
	 * 
	 * @param e
	 *            is the expression.
	 * @return null.
	 */
	public Object visit(AxisStep e) {

		e.step().accept(this);

		visitCollExprs(e.iterator());
		return null;
	}

	/**
	 * Validate a filter expression.
	 * 
	 * @param e
	 *            is the expression.
	 * @return null.
	 */
	public Object visit(FilterExpr e) {
		e.primary().accept(this);

		visitCollExprs(e.iterator());
		return null;
	}

	/**
	 * @since 2.0
	 */
	public boolean isRootUsed() {
		return _rootUsed;
	}
}
