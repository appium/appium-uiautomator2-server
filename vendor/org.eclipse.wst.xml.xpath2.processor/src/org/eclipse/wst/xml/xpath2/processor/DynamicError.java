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
 *     David Carver (STAR) - bug 273763 - correct error codes.
 *                           bug 280106 - Correct XPDY0021 - XPST0003 
 *     Jesper Steen Moeller - bug 28149 - add more fn:error info
 *     Jesper Steen Moller  - bug 281159 - fix document loading and resolving URIs 
 *     Jesper Steen Moller  - Bug 286062 - Add FOAR0002  
 *     Jesper Steen Moller  - bug 280555 - Add pluggable collation support
 *     Jesper Steen Moller  - bug 262765 - Add FORG0006
 *     Jesper Steen Moller  - bug 290337 - Revisit use of ICU
 *     Mukul Gandhi - bug 280798 - PsychoPath support for JDK 1.4
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor;

import org.eclipse.wst.xml.xpath2.processor.internal.TypeError;

/**
 * Dynamic Error like division by 0 or type errors.
 */
public class DynamicError extends XPathException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6146830764753685791L;

	// errorcode specified in http://www.w3.org/2004/10/xqt-errors i fink
	private String _code;

	// XXX dirty... should fix the error stuff
	// have a type error encapsulated in a dynamic error
	private TypeError _te;

	/**
	 * Constructor for Dynamic Error.
	 * 
	 * @param code
	 *            is the code that is set.
	 * @param err
	 *            is the reason for the error.
	 */
	public DynamicError(String code, String err) {
		super(err);
		_code = code;
		_te = null;
	}

	/**
	 * Constructor for Dynamic Error.
	 * 
	 * @param te
	 *            is the error type.
	 */
	public DynamicError(TypeError te) {
		super(te.reason());
		_te = te;
		_code = te.code();
	}

	/**
	 * Returns the string of the code.
	 * 
	 * @return the code.
	 */
	public String code() {
		if (_te != null)
			return _te.code();
		return _code;
	}

	/**
	 * Returns the dynamic error.
	 * 
	 * @param err
	 *            is the error
	 * @return the DynamicError.
	 */
	public static DynamicError cant_cast(String err) {
		String error = "Can't cast to required type.";

		if (err != null)
			error += " " + err;

		return new DynamicError("FORG0001", error);
	}

	/**
	 * Returns the dynamic error.
	 * 
	 * @throws DynamicError
	 *             a Dynamic Error
	 * @return the DynamicError.
	 */
	public static DynamicError throw_type_error() throws DynamicError {
		throw argument_type_error(null);
	}

	/**
	 * Returns the dynamic error.
	 *
	 * @param type Type found
	 * @return the DynamicError.
	 * @since 1.1
	 */
	public static DynamicError argument_type_error(Class type) {
		return new DynamicError("FORG0006", type != null ?
				"Invalid argument type :" + type.getName() : "Invalid argument type");
	}

	/**
	 * @since 1.1
	 */
	public static DynamicError invalidType() throws DynamicError {
		throw new DynamicError(TypeError.invalid_type(null));
	}

	/**
	 * @since 1.1
	 */
	public static DynamicError inputToLargeForDecimal() throws DynamicError {
		throw new DynamicError("FOCA0001", "Input value too large for decimal");
	}
	/**
	 * Returns the dynamic error.
	 * 
	 * @param desc
	 *            is the description of the error
	 * @return the DynamicError.
	 * @since 1.1
	 */
	public static DynamicError user_error(String ns, String code, String desc) {
		String error = "Error reported by fn:error.";

		if (desc != null)
			error = desc + " (reported by fn:error)";

		// XXX: Need to pass the namespace also...
		return new DynamicError(code, error);
	}

	/**
	 * Returns the dynamic error.
	 * 
	 * @param err
	 *            is the description of the error
	 * @return the DynamicError.
	 */
	public static DynamicError user_error(String err) {
		String error = "Error reported by fn:error.";

		if (err != null)
			error = err + " (reported by fn:error)";

		// XXX: Need to pass the namespace also...
		return new DynamicError("FOER0000", error);
	}
	
	/**
	 * Returns the Dynamic Error for invalid flags in regular expressions
	 * @param err
	 * @return
	 * @since 1.1
	 */
	public static DynamicError regex_flags_error(String err) {
		String error = "Invalid regular expression flag parameter.";

		if (err != null)
			error += " " + err;

		return new DynamicError("FORX0001", error);
		
	}

	/**
	 * Returns the dynamic error.
	 * 
	 * @param err
	 *            is the error
	 * @return the DynamicError.
	 */
	public static DynamicError regex_error(String err) {
		String error = "Invalid regular expression.";

		if (err != null)
			error += " " + err;

		return new DynamicError("FORX0002", error);
	}

	/**
	 * Returns the dynamic error.
	 * 
	 * @param err
	 *            is the error
	 * @return the DynamicError.
	 * @since 1.1
	 */
	public static DynamicError regex_match_zero_length(String err) {
		String error = "Invalid regular expression.";

		if (err != null)
			error += " " + err;

		return new DynamicError("FORX0003", error);
	}

	/**
	 * Returns the dynamic error for an unsupported Unicode codepoint
	 * 
	 * @param err
	 *            is the error
	 * @return the DynamicError.
	 * @since 1.1
	 * 
	 */
	public static DynamicError unsupported_codepoint(String err) {
		String error = "Unsupported codepoint";

		if (err != null)
			error += " " + err;

		return new DynamicError("FOCH0001", error);
	}

	/**
	 * Returns the dynamic error for an unsupported normalization form
	 * 
	 * @param collationName
	 *            is the error
	 * @return the DynamicError.
	 * @since 1.1
	 * 
	 */
	public static DynamicError unsupported_collation(String collationName) {
		String error = "Unsupported collation URI. ";

		if (collationName != null)
			error += " " + collationName;

		return new DynamicError("FOCH0002", error);
	}

	/**
	 * Returns the dynamic error for an unsupported normalization form
	 * 
	 * @param err
	 *            is the error
	 * @return the DynamicError.
	 * @since 1.1
	 * 
	 */
	public static DynamicError unsupported_normalization_form(String err) {
		String error = "Unsupported normalization form: ";

		if (err != null)
			error += " " + err;

		return new DynamicError("FOCH0003", error);
	}

	/**
	 * Returns the dynamic error for an unsupported normalization form
	 * 
	 * @param err
	 *            is the error
	 * @return the DynamicError.
	 * @since 1.1
	 * 
	 */
	public static DynamicError runtime_error(String msg, Throwable err) {
		String error = "Error at runtime: " + msg + ": " + err.getMessage();

		return new DynamicError("FOER0000", error);
	}

	private static DynamicError make_error(String code, String err, String msg) {
		String error = err;

		if (msg != null)
			error += msg;

		return new DynamicError(code, error);
	}

	/**
	 * Returns the error message when reads an Invalid lexical value
	 * 
	 * @param msg
	 *            is the message
	 * @return the make_error
	 */
	public static DynamicError lexical_error(String msg) {
		return make_error("FOCA0002", "Invalid lexical value.", msg);
	}

	/**
	 * Returns the error message when reads an Items not comparable
	 * 
	 * @param msg
	 *            is the message
	 * @return the make_error
	 */
	public static DynamicError not_cmp(String msg) {
		return make_error("FOTY0012", "Items not comparable", msg);
	}

	/**
	 * Returns the error message
	 * 
	 * @param msg
	 *            is the message
	 * @return the make_error
	 */
	public static DynamicError more_one_item(String msg) {
		return make_error(
				"FORG0003",
				"fn:zero-or-one called with a sequence containing more than one item",
				msg);
	}

	/**
	 * Returns the error message
	 * 
	 * @param msg
	 *            is the message
	 * @return the make_error
	 */
	public static DynamicError empty_seq(String msg) {
		return make_error("FORG0004",
				"fn:one-or-more called with a sequence containing no items",
				msg);
	}

	/**
	 * Returns the error message
	 * 
	 * @param msg
	 *            is the message
	 * @return the make_error
	 */
	public static DynamicError not_one(String msg) {
		return make_error(
				"FORG0005",
				"fn:exactly-one called with a sequence containing zero or more than one item",
				msg);
	}

	/**
	 * Returns the error message when reads Invalid argument to fn:collection
	 * 
	 * @param msg
	 *            is the message
	 * @return the make_error
	 * @since 1.1
	 */
	public static DynamicError invalidCollectionArgument() {
		return make_error("FODC0004", "Invalid argument to fn:doc", null);
	}
	
	/**
	 * Returns the error message when reads Invalid argument to fn:doc
	 * 
	 * @param msg
	 *            is the message
	 * @return the make_error
	 */
	public static DynamicError invalid_doc(String msg) {
		return make_error("FODC0005", "Invalid argument to fn:doc", msg);
	}

	/**
	 * Returns the error message when fn:doc cannot load its document
	 * 
	 * @param msg
	 *            is the message
	 * @return the make_error
	 * @since 1.1
	 */
	public static DynamicError doc_not_found(String msg) {
		return make_error("FODC0002", "Document argument fn:doc not found", msg);
	}

	/**
	 * Returns the error message when reads a Division by zero
	 * 
	 * @param msg
	 *            is the message
	 * @return the make_error
	 */
	public static DynamicError div_zero(String msg) {
		return make_error("FOAR0001", "Division by zero", msg);
	}

	/**
	 * Numeric operation overflow/underflow
	 * 
	 * @param msg
	 *            is the message
	 * @return the make_error
	 * @since 1.1
	 */
	public static DynamicError numeric_overflow(String msg) {
		return make_error("FOAR0002", "Numeric overflow/underflow", msg);
	}
	/**
	 * @since 1.1
	 */
	public static DynamicError contextUndefined() {
		return make_error("XPDY0002", "Context is undefined.", "");
	}

	/**
	 * Data is invalid for casting or the data type constructor.
	 * @param msg
	 * @return FORG0001
	 * @since 1.1
	 */
	public static DynamicError invalidForCastConstructor() {
		return make_error(
				"FORG0001",
				"data type invalid for cast or constructor",
				null);
	}
	
	/**
	 * No namespace found for prefix.
	 * 
	 * @return
	 * @since 1.1
	 */
	public static DynamicError invalidPrefix() {
		return make_error("FONS0004", "No namespace found for prefix.", null);
	}
	
	/**
	 * No context document
	 * @return
	 * @since 1.1
	 */
	public static DynamicError noContextDoc() {
		return make_error("FODC0001", "No context document.", null);
	}

	/**
	 * No base-uri defined.
	 * 
	 * @return
	 * @since 1.1
	 */
	public static DynamicError noBaseURI() {
		return make_error("FONS0005", "Base-uri not defined in the static context.", null);
	}
	
	/**
	 * Error resolving relative uri against base-uri.
	 * 
	 * @return
	 * @since 1.1
	 */
	public static DynamicError errorResolvingURI() {
		return make_error("FORG0002", "Invalid argument to fn:resolve-uri().", null);
	}
	
	/**
	 * Invalid Timezone value.
	 * @return
	 * @since 1.1
	 */
	public static DynamicError invalidTimezone() {
		return make_error("FODT0003", "Invalid timezone value.", null);
	}
	
	/**
	 * Overflow/underflow in duration operation.
	 * @return
	 * @since 1.1
	 */
	public static DynamicError overflowUnderflow() {
		return make_error("FODT0002", "Overflow/underflow in duration operation.", null);
	}
	
	/**
	 * Overflow/underflow in duration operation.
	 * @return
	 * @since 1.1
	 */
	public static DynamicError nan() {
		return make_error("FOCA0005", "NaN supplied as float/double value.", null);
	}
	
	/**
	 * Invalid lexical value
	 * 
	 * @since 1.1
	 */
	public static DynamicError invalidLexicalValue() {
		return make_error("FOCA0002", "Invalid lexical value.", null);
	}
	
	/**
	 * Overflow/underflow in date/time operation 
	 * @since 1.1
	 */
	public static DynamicError overflowDateTime() {
		return make_error("FODT0001", "Overflow/underflow in date/time operation", null);
	}
	
	/**
	 * The two arguments to fn:dateTime have inconsistent timezones
	 * 
	 * @since 1.1
	 */
	public static DynamicError inconsistentTimeZone() {
		return make_error("FORG0008", "The two arguments to fn:dateTime have inconsistent timezones", null);
	}
}
