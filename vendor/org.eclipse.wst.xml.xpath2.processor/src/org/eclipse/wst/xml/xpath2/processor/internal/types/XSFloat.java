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
 *     Mukul Gandhi - bug 274805 - improvements to xs:integer data type
 *     Mukul Gandhi - bug 279406 - improvements to negative zero values for xs:float
 *     David Carver - bug 262765 - fixed rounding errors.
 *     David Carver - bug 282223 - fixed casting errors.
 *     Jesper Steen Moller - Bug 286062 - Fix idiv error cases and increase precision  
 *     Jesper Steen Moller - bug 281028 - Added constructor from string
 *     Mukul Gandhi - bug 280798 - PsychoPath support for JDK 1.4
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal.types;

import java.math.BigDecimal;
import java.util.Iterator;

import org.eclipse.wst.xml.xpath2.api.DynamicContext;
import org.eclipse.wst.xml.xpath2.api.Item;
import org.eclipse.wst.xml.xpath2.api.ResultBuffer;
import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.api.typesystem.TypeDefinition;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.ResultSequenceFactory;
import org.eclipse.wst.xml.xpath2.processor.internal.types.builtin.BuiltinTypeLibrary;

/**
 * A representation of the Float datatype
 */
public class XSFloat extends NumericType {

	private static final String XS_FLOAT = "xs:float";
	private Float _value;
	private XPathDecimalFormat format = new XPathDecimalFormat("0.#######E0");
	/**
	 * Initiates a representation of the supplied number
	 * 
	 * @param x
	 *            The number to be stored
	 */
	public XSFloat(float x) {
		_value = new Float(x);
	}

	/**
	 * Initiates a representation of 0
	 */
	public XSFloat() {
		this(0);
	}

	/**
	 * Initialises using a String represented number
	 * 
	 * @param init
	 *            String representation of the number to be stored
	 */
	public XSFloat(String init) throws DynamicError {
		try {
			if (init.equals("-INF")) {
				_value = new Float(Float.NEGATIVE_INFINITY);
			} else if (init.equals("INF")) {
				_value = new Float(Float.POSITIVE_INFINITY);
			} else {
				_value = new Float(init);
			}
		} catch (NumberFormatException e) {
			throw DynamicError.cant_cast(null);
		}
	}
	/**
	 * Retrieves the datatype's full pathname
	 * 
	 * @return "xs:float" which is the datatype's full pathname
	 */
	public String string_type() {
		return XS_FLOAT;
	}

	/**
	 * Retrieves the datatype's name
	 * 
	 * @return "float" which is the datatype's name
	 */
	public String type_name() {
		return "float";
	}

	/**
	 * Retrieves a String representation of the stored number
	 * 
	 * @return String representation of the stored number
	 */
	public String getStringValue() {
		if (zero()) {
		   return "0";
		}
		if (negativeZero()) {
		   return "-0";	
		}
		
		if (nan()) {
		   return "NaN";	
		}
								
		return format.xpathFormat(_value);
	}

	/**
	 * Check for whether this datatype represents NaN
	 * 
	 * @return True is this datatype represents NaN. False otherwise
	 */
	public boolean nan() {
		return Float.isNaN(_value.floatValue());
	}

	/**
	 * Check for whether this datatype represents negative or positive infinity
	 * 
	 * @return True is this datatype represents infinity. False otherwise
	 */
	public boolean infinite() {
		return Float.isInfinite(_value.floatValue());
	}

	/**
	 * Check for whether this datatype represents 0
	 * 
	 * @return True if this datatype represents 0. False otherwise
	 */
	public boolean zero() {
	   return (Float.compare(_value.floatValue(), 0) == 0);
	}
	
	/*
	 * Check for whether this XSFloat represents -0
	 * 
	 * @return True if this XSFloat represents -0. False otherwise.
	 * @since 1.1
	 */
	public boolean negativeZero() {
	   return (Float.compare(_value.floatValue(), -0.0f) == 0);
	}
	
	/**
	 * Creates a new ResultSequence consisting of the retrievable float in the
	 * supplied ResultSequence
	 * 
	 * @param arg
	 *            The ResultSequence from which to extract the float
	 * @return New ResultSequence consisting of the float supplied
	 * @throws DynamicError
	 */
	public ResultSequence constructor(ResultSequence arg) throws DynamicError {
		if (arg.empty())
			return ResultBuffer.EMPTY;

		AnyType aat = (AnyType) arg.first();
		
		if (aat instanceof XSDuration || aat instanceof CalendarType ||
			aat instanceof XSBase64Binary || aat instanceof XSHexBinary ||
			aat instanceof XSAnyURI) {
			throw DynamicError.invalidType();
		}
		
		if (!(aat.string_type().equals("xs:string") || aat instanceof NodeType ||
			aat.string_type().equals("xs:untypedAtomic") ||
			aat.string_type().equals("xs:boolean") ||
			aat instanceof NumericType)) {
			throw DynamicError.cant_cast(null);
		}
		

		try {
			float f;
			if (aat.getStringValue().equals("INF")) {
				f = Float.POSITIVE_INFINITY;
			} else if (aat.getStringValue().equals("-INF")) {
				f = Float.NEGATIVE_INFINITY;
			} else if (aat instanceof XSBoolean) {
				if (aat.getStringValue().equals("true")) {
					f = 1.0f;
				} else {
					f = 0.0f;
				}
			} else {
			    f = Float.valueOf(aat.getStringValue());
			}
			return new XSFloat(f);
		} catch (NumberFormatException e) {
			throw DynamicError.cant_cast(null);
		}

	}

	/**
	 * Retrieves the actual float value stored
	 * 
	 * @return The actual float value stored
	 */
	public float float_value() {
		return _value.floatValue();
	}

	/**
	 * Equality comparison between this number and the supplied representation.
	 * @param aa
	 *            The datatype to compare with
	 * 
	 * @return True if the two representations are of the same number. False
	 *         otherwise
	 * @throws DynamicError
	 */
	public boolean eq(AnyType aa, DynamicContext dynamicContext) throws DynamicError {
		Item carg = convertArg(aa);
		if (!(carg instanceof XSFloat))
			DynamicError.throw_type_error();

		XSFloat f = (XSFloat) carg;
		if (nan() && f.nan()) {
			return false;
		}
		
		Float thatvalue = new Float(f.float_value());
		Float thisvalue = new Float(float_value());

		return thisvalue.equals(thatvalue);
	}

	/**
	 * Comparison between this number and the supplied representation. 
	 * 
	 * @param arg
	 *            The datatype to compare with
	 * @return True if the supplied representation is a smaller number than the
	 *         one stored. False otherwise
	 * @throws DynamicError
	 */
	public boolean gt(AnyType arg, DynamicContext context) throws DynamicError {
		Item carg = convertArg(arg);
		XSFloat val = (XSFloat) get_single_type(carg, XSFloat.class);
		return float_value() > val.float_value();
	}

	/**
	 * Comparison between this number and the supplied representation. 
	 * 
	 * @param arg
	 *            The datatype to compare with
	 * @return True if the supplied representation is a greater number than the
	 *         one stored. False otherwise
	 * @throws DynamicError
	 */
	public boolean lt(AnyType arg, DynamicContext context) throws DynamicError {
		Item carg = convertArg(arg);
		XSFloat val = (XSFloat) get_single_type(carg, XSFloat.class);
		return float_value() < val.float_value();
	}

	/**
	 * Mathematical addition operator between this XSFloat and the supplied
	 * ResultSequence. 
	 * 
	 * @param arg
	 *            The ResultSequence to perform an addition with
	 * @return A XSFloat consisting of the result of the mathematical addition.
	 */
	public ResultSequence plus(ResultSequence arg) throws DynamicError {
		ResultSequence carg = convertResultSequence(arg);
		Item at = get_single_arg(carg);
		if (!(at instanceof XSFloat))
			DynamicError.throw_type_error();
		XSFloat val = (XSFloat) at;

		return ResultSequenceFactory.create_new(new XSFloat(float_value()
				+ val.float_value()));
	}

	/**
	 * Mathematical subtraction operator between this XSFloat and the supplied
	 * ResultSequence. 
	 * 
	 * @param arg
	 *            The ResultSequence to perform a subtraction with
	 * @return A XSFloat consisting of the result of the mathematical
	 *         subtraction.
	 */
	public ResultSequence minus(ResultSequence arg) throws DynamicError {
		ResultSequence carg = constructor(arg);
		Item at = get_single_arg(carg);
		if (!(at instanceof XSFloat))
			DynamicError.throw_type_error();
		XSFloat val = (XSFloat) at;

		return ResultSequenceFactory.create_new(new XSFloat(float_value()
				- val.float_value()));
	}

	/**
	 * Mathematical multiplication operator between this XSFloat and the
	 * supplied ResultSequence. 
	 * 
	 * @param arg
	 *            The ResultSequence to perform a multiplication with
	 * @return A XSFloat consisting of the result of the mathematical
	 *         multiplication.
	 */
	public ResultSequence times(ResultSequence arg) throws DynamicError {
		ResultSequence carg = constructor(arg);
		XSFloat val = (XSFloat) get_single_type(carg, XSFloat.class);
		return ResultSequenceFactory.create_new(new XSFloat(float_value()
				* val.float_value()));
	}

	/**
	 * Mathematical division operator between this XSFloat and the supplied
	 * ResultSequence. 
	 * 
	 * @param arg
	 *            The ResultSequence to perform a division with
	 * @return A XSFloat consisting of the result of the mathematical division.
	 */
	public ResultSequence div(ResultSequence arg) throws DynamicError {
		ResultSequence carg = convertResultSequence(arg);
		XSFloat val = (XSFloat) get_single_type(carg, XSFloat.class);
		return ResultSequenceFactory.create_new(new XSFloat(float_value()
				/ val.float_value()));
	}

	/**
	 * Mathematical integer division operator between this XSFloat and the
	 * supplied ResultSequence. 
	 * 
	 * @param arg
	 *            The ResultSequence to perform an integer division with
	 * @return A XSInteger consisting of the result of the mathematical integer
	 *         division.
	 */
	public ResultSequence idiv(ResultSequence arg) throws DynamicError {
		ResultSequence carg = convertResultSequence(arg);
		XSFloat val = (XSFloat) get_single_type(carg, XSFloat.class);

		if (this.nan() || val.nan())
			throw DynamicError.numeric_overflow("Dividend or divisor is NaN");

		if (this.infinite())
			throw DynamicError.numeric_overflow("Dividend is infinite");

		if (val.zero())
			throw DynamicError.div_zero(null);

		BigDecimal result = BigDecimal.valueOf((new Float((float_value() / 
				                                 val.float_value()))).longValue());
		return ResultSequenceFactory.create_new(new XSInteger(result.toBigInteger()));
	}

	/**
	 * Mathematical modulus operator between this XSFloat and the supplied
	 * ResultSequence. Due to no numeric type promotion or conversion, the
	 * ResultSequence must be of type XSFloat.
	 * 
	 * @param arg
	 *            The ResultSequence to perform a modulus with
	 * @return A XSFloat consisting of the result of the mathematical modulus.
	 */
	public ResultSequence mod(ResultSequence arg) throws DynamicError {
		ResultSequence carg = convertResultSequence(arg);
		XSFloat val = (XSFloat) get_single_type(carg, XSFloat.class);
		return ResultSequenceFactory.create_new(new XSFloat(float_value()
				% val.float_value()));
	}

	/**
	 * Negates the number stored
	 * 
	 * @return A XSFloat representing the negation of the number stored
	 */
	public ResultSequence unary_minus() {
		return ResultSequenceFactory
				.create_new(new XSFloat(-1 * float_value()));
	}

	/**
	 * Absolutes the number stored
	 * 
	 * @return A XSFloat representing the absolute value of the number stored
	 */
	public NumericType abs() {
		return new XSFloat(Math.abs(float_value()));
	}

	/**
	 * Returns the smallest integer greater than the number stored
	 * 
	 * @return A XSFloat representing the smallest integer greater than the
	 *         number stored
	 */
	public NumericType ceiling() {
		return new XSFloat((float) Math.ceil(float_value()));
	}

	/**
	 * Returns the largest integer smaller than the number stored
	 * 
	 * @return A XSFloat representing the largest integer smaller than the
	 *         number stored
	 */
	public NumericType floor() {
		return new XSFloat((float) Math.floor(float_value()));
	}

	/**
	 * Returns the closest integer of the number stored.
	 * 
	 * @return A XSFloat representing the closest long of the number stored.
	 */
	public NumericType round() {
		BigDecimal value = new BigDecimal(float_value());
		BigDecimal round = value.setScale(0, BigDecimal.ROUND_HALF_UP);
		return new XSFloat(round.floatValue());
	}

	/**
	 * Returns the closest integer of the number stored.
	 * 
	 * @return A XSFloat representing the closest long of the number stored.
	 */
	public NumericType round_half_to_even() {
		return round_half_to_even(0);
	}

	/**
	 * Returns the closest integer of the number stored with the specified precision.
	 * 
	 * @param precision An integer precision 
	 * @return A XSFloat representing the closest long of the number stored.
	 */
	public NumericType round_half_to_even(int precision) {
		BigDecimal value = new BigDecimal(_value.floatValue());
		BigDecimal round = value.setScale(precision, BigDecimal.ROUND_HALF_EVEN);
		return new XSFloat(round.floatValue());
	}
	
	protected Item convertArg(AnyType arg) throws DynamicError {
		ResultSequence rs = ResultSequenceFactory.create_new(arg);
		rs = constructor(rs);
		Item carg = rs.first();
		return carg;
	}
	
	private ResultSequence convertResultSequence(ResultSequence arg)
			throws DynamicError {
		ResultSequence carg = arg;
		Iterator it = carg.iterator();
		while (it.hasNext()) {
			AnyType type = (AnyType) it.next();
			if (type.string_type().equals("xs:untypedAtomic") ||
				type.string_type().equals("xs:string")) {
				throw DynamicError.throw_type_error();
			}
		}

		carg = constructor(carg);
		return carg;
	}	
	
	public TypeDefinition getTypeDefinition() {
		return BuiltinTypeLibrary.XS_FLOAT;
	}
	
	public Object getNativeValue() {
		return float_value();
	}
}
