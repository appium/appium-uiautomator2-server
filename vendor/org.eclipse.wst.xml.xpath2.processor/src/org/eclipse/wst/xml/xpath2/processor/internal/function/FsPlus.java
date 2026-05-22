/*******************************************************************************
 * Copyright (c) 2005, 2011 Andrea Bittau, University College London, and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Andrea Bittau - initial API and implementation from the PsychoPath XPath 2.0 
 *     Jesper Steen Moeller - bug 285145 - implement full arity checking
 *     Jesper Steen Moller  - Bug 286062 - Add type promotion for numeric operators  
 *     Mukul Gandhi - bug 280798 - PsychoPath support for JDK 1.4
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal.function;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.wst.xml.xpath2.api.EvaluationContext;
import org.eclipse.wst.xml.xpath2.api.Item;
import org.eclipse.wst.xml.xpath2.api.ResultBuffer;
import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.internal.TypeError;
import org.eclipse.wst.xml.xpath2.processor.internal.types.AnyType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.NumericType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.QName;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSDecimal;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSDouble;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSFloat;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSUntypedAtomic;

/**
 * Class for Plus function.
 */
public class FsPlus extends Function {
	/**
	 * Constructor for FsPlus.
	 */
	public FsPlus() {
		super(new QName("plus"), 2);
	}

	/**
	 * Evaluate arguments.
	 * 
	 * @param args
	 *            argument expressions.
	 * @throws DynamicError
	 *             Dynamic error.
	 * @return Result of evaluation.
	 */
	public ResultSequence evaluate(Collection args, EvaluationContext ec) {
		assert args.size() >= min_arity() && args.size() <= max_arity();

		return fs_plus(args);
	}

	/**
	 * Convert and promote arguments for operation.
	 * 
	 * @param args
	 *            input arguments.
	 * @param sc 
	 * @throws DynamicError
	 *             Dynamic error.
	 * @return Result of conversion.
	 */
	private static Collection convert_args(Collection args) throws DynamicError {
		Collection result = new ArrayList();

		// Keep track of numeric types for promotion
		boolean has_float = false;
		boolean has_double = false;
		
		// atomize arguments
		for (Iterator i = args.iterator(); i.hasNext();) {
			org.eclipse.wst.xml.xpath2.api.ResultSequence rs = FnData.atomize((org.eclipse.wst.xml.xpath2.api.ResultSequence) i.next());

			if (rs.empty())
				return new ArrayList();

			if (rs.size() > 1)
				throw new DynamicError(TypeError.invalid_type(null));

			AnyType arg = (AnyType) rs.item(0);

			if (arg instanceof XSUntypedAtomic) {
				arg = new XSDouble(arg.getStringValue());
			}

			if (arg instanceof XSDouble) has_double = true;
			if (arg instanceof XSFloat) has_float = true;
			result.add(ResultBuffer.wrap(arg));
		}

		if (has_double) has_float = false;
		
		if (has_double || has_float) {
			Collection result2 = new ArrayList();

			// promote arguments
			for (Iterator i = result.iterator(); i.hasNext();) {
				org.eclipse.wst.xml.xpath2.api.ResultSequence rs = (org.eclipse.wst.xml.xpath2.api.ResultSequence) i.next();
								
				Item arg = rs.item(0);
				
				if (has_double && (arg instanceof XSFloat)) {
					arg = new XSDouble(((XSFloat)arg).float_value());
				} else if (has_double && (arg instanceof XSDecimal)) {
					arg = new XSDouble(((XSDecimal)arg).getValue().doubleValue());
				} else if (has_float && (arg instanceof XSDecimal)) {
					arg = new XSFloat(((XSDecimal)arg).getValue().floatValue());
				}
				result2.add(arg);
			}
			return result2;
		}
		
		return result;
	}


	/**
	 * General operation on the arguments.
	 * 
	 * @param args
	 *            input arguments.
	 * @param sc 
	 * @throws DynamicError
	 *             Dynamic error.
	 * @return Result of the operation.
	 */
	public static ResultSequence fs_plus(Collection args) throws DynamicError {
		return do_math_op(args, MathPlus.class, "plus");
	}

	/**
	 * Unary operation on the arguments.
	 * 
	 * @param args
	 *            input arguments.
	 * @throws DynamicError
	 *             Dynamic error.
	 * @return Result of the operation.
	 */
	public static ResultSequence fs_plus_unary(Collection args)
			throws DynamicError {

		// make sure we got only one arg
		if (args.size() != 1)
			DynamicError.throw_type_error();
		ResultSequence arg = (ResultSequence) args.iterator().next();

		// make sure we got only one numeric atom
		if (arg.size() != 1)
			DynamicError.throw_type_error();
		Item at = arg.first();
		if (!(at instanceof NumericType))
			DynamicError.throw_type_error();

		// no-op
		return arg;
	}

	// voodoo
	/**
	 * Mathematical operation on the arguments.
	 * 
	 * @param args
	 *            input arguments.
	 * @param type
	 *            type of arguments.
	 * @param mname
	 *            Method name for template simulation.
	 * @param sc 
	 * @throws DynamicError
	 *             Dynamic error.
	 * @return Result of operation.
	 */
	public static ResultSequence do_math_op(Collection args, Class type,
			String mname) throws DynamicError {

		// sanity check args + convert em
		if (args.size() != 2)
			DynamicError.throw_type_error();

		Collection cargs = convert_args(args);

		if (cargs.size() == 0)
			return ResultBuffer.EMPTY;

		// make sure arugments are good [at least the first one]
		Iterator argi = cargs.iterator();
		Item arg = ((org.eclipse.wst.xml.xpath2.api.ResultSequence) argi.next()).item(0);
		org.eclipse.wst.xml.xpath2.api.ResultSequence arg2 = (org.eclipse.wst.xml.xpath2.api.ResultSequence) argi.next();

		if (!(type.isInstance(arg)))
			DynamicError.throw_type_error();

		// here is da ownage
		try {
			Class margsdef[] = { ResultSequence.class };
			Method method = null;

			method = type.getMethod(mname, margsdef);

			Object margs[] = { arg2 };
			return (ResultSequence) method.invoke(arg, margs);

		} catch (NoSuchMethodException err) {
			System.out.println("NoSuchMethodException: " + err.getMessage());
			assert false;
		} catch (IllegalAccessException err) {
			System.out.println("IllegalAccessException: " + err.getMessage());
			assert false;
		} catch (InvocationTargetException err) {
			Throwable ex = err.getTargetException();
			if (ex instanceof DynamicError) {
				throw (DynamicError) ex;
			} else {
				throw new RuntimeException(ex);
			}
		}
		return null; // unreach!
	}
}
