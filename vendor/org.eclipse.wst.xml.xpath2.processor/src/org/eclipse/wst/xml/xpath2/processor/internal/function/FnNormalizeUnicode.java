/*******************************************************************************
 * Copyright (c) 2005, 2011 Jesper Steen Moeller, and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Jesper Steen Moeller - bug 285152 - implement fn:normalize-unicocde
 *     Jesper Steen Moller  - bug 290337 - Revisit use of ICU
 *     Mukul Gandhi - bug 280798 - PsychoPath support for JDK 1.4
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal.function;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.wst.xml.xpath2.api.DynamicContext;
import org.eclipse.wst.xml.xpath2.api.EvaluationContext;
import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.internal.SeqType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.QName;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSString;

import com.ibm.icu.text.Normalizer;

/**
 * <p>
 * Function to normalize unicode.
 * </p>
 * 
 * <p>
 * Usage: fn:normalize-unicode($arg as xs:string?) as xs:string
 * or fn:normalize-unicode($arg as xs:string?, $normalization-form as xs:string) as xs:string
 * </p>
 * 
 * <p>
 * This function returns the normalized value of the first argument in the normalization form specified by the second argument.
 * </p>
 * 
 */
public class FnNormalizeUnicode extends Function {
	private static Collection _expected_args = null;
	private static W3CNormalizer normalizer = null;

	/**
	 * Constructor for FnNormalizeUnicode
	 */
	public FnNormalizeUnicode() {
		super(new QName("normalize-unicode"), 1, 2);
	}

	/**
	 * The common interface which normalizers must adhere to
	 */
	public interface W3CNormalizer {
		String normalize(String argument, String normalizationForm) throws DynamicError;
	};
	
	/**
	 * W3C normalizer implemented via IBM's ICU
	 */
	static class ICUNormalizer implements W3CNormalizer {
		
		private Map modeMap = new HashMap();
		{
			// Can't handle "FULLY-NORMALIZED" yet
			
			modeMap.put("NFC", Normalizer.NFC);
			modeMap.put("NFD", Normalizer.NFD);
			modeMap.put("NFKC", Normalizer.NFKC);
			modeMap.put("NFKD", Normalizer.NFKD);
		}
		
		public String normalize(String argument, String normalizationForm)
				throws DynamicError {
			Normalizer.Mode mode = (Normalizer.Mode)modeMap.get(normalizationForm);
			if (mode != null) {
				return Normalizer.normalize(argument, mode);
			} else {
				throw DynamicError.unsupported_normalization_form(normalizationForm);
			}
		}
	}

	/*
	static class JDK6Normalizer implements W3CNormalizer {
		private Method normalizeMethod;
		private Map formMap = new HashMap();

		public JDK6Normalizer(Class normalizerCls, Class formCls) throws SecurityException, NoSuchMethodException {
			this.normalizeMethod = normalizerCls.getMethod("normalize", CharSequence.class, formCls);
			Enum[] formConstants = formCls.getEnumConstants();
			for (Enum form : formConstants) {
				formMap.put(form.name(), form);
			}
			// Can't handle "FULLY-NORMALIZED" yet
		}
		
		public String normalize(String argument, String normalizationForm)
				throws DynamicError {

			//if (normalizationForm.equals("FULLY-NORMALIZED")) {
			//   We can't handle this one yet
			// }
			Enum form = formMap.get(normalizationForm);
			if (form != null) {
				try {
					return (String)normalizeMethod.invoke(null, argument, form);
				} catch (RuntimeException e) {
					throw DynamicError.runtime_error("java.text.Normalizer.normalize(..., \"" + normalizationForm + "\")", e);
				} catch (IllegalAccessException e) {
					throw DynamicError.runtime_error("java.text.Normalizer.normalize(..., \"" + normalizationForm + "\")", e);
				} catch (InvocationTargetException e) {
					throw DynamicError.runtime_error("java.text.Normalizer.normalize(..., \"" + normalizationForm + "\")", e);
				}
			} else {
				throw DynamicError.unsupported_normalization_form(normalizationForm);
			}			
		}
	}
	*/

	static class FailingNormalizer implements W3CNormalizer {
		
		public String normalize(String argument, String normalizationForm)
				throws DynamicError {
			throw DynamicError.unsupported_normalization_form("Can't normalize to form " + normalizationForm + ": No ICU Library or Java 6 found. 'normalize-unicode' requires either 'com.ibm.icu.text.Normalizer' or 'java.text.Normalizer' on the classpath");
		}
	}
	
	/**
	 * Evaluate the arguments.
	 * 
	 * @param args
	 *            are evaluated.
	 * @throws DynamicError
	 *             Dynamic error.
	 * @return The evaluation of the space in the arguments being normalized.
	 */
	public ResultSequence evaluate(Collection args, EvaluationContext ec) throws DynamicError {
		return normalize_unicode(args, ec.getDynamicContext());
	}

	/**
	 * Normalize unicode codepoints in the argument.
	 * 
	 * @param args
	 *            are used to obtain the input string and optionally the normalization type from.
	 * @throws DynamicError
	 *             Dynamic error.
	 * @return The result of normalizing the space in the arguments.
	 */
	public static ResultSequence normalize_unicode(Collection args, DynamicContext d_context)
			throws DynamicError {
		assert args.size() >= 1 && args.size() <= 2;

		Collection cargs = Function.convert_arguments(args, expected_args());
		
		Iterator cargsIterator = cargs.iterator();
		ResultSequence arg1 = (ResultSequence) cargsIterator.next();


		String normalizationType = "NFC";
		if (cargsIterator.hasNext()) {
			ResultSequence arg2 = (ResultSequence)cargsIterator.next();
			// Trim and convert to upper as per the spec
			if (arg2.empty()) {
				normalizationType = "";
			} else {
				normalizationType = ((XSString) arg2.first()).value().trim().toUpperCase();
			}
		}
		
		String argument = "";
		if (! arg1.empty()) argument = ((XSString) arg1.first()).value();
				
		String normalized = normalizationType.equals("") ? argument : getNormalizer().normalize(argument, normalizationType);
		return new XSString(normalized);
	}

	private static W3CNormalizer getNormalizer() {
		if (normalizer == null) {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			
			normalizer = createICUNormalizer(classLoader);
			/*
			if (normalizer == null) {
				normalizer = createJDKNormalizer(classLoader);
			}
			*/
			if (normalizer == null) {
				normalizer = new FailingNormalizer();
			}
		}
		return normalizer;
	}

	/*
	private static W3CNormalizer createJDKNormalizer(ClassLoader classLoader) {
		// If that fails, we'll check for the Java 6 Normalizer class
		try {
			Class normalizerClass = classLoader.loadClass("java.text.Normalizer");
			Class formClass = classLoader.loadClass("java.text.Normalizer$Form");
			
			return new JDK6Normalizer(normalizerClass, formClass);
		} catch (ClassNotFoundException e) {
		} catch (SecurityException e) {
		} catch (NoSuchMethodException e) {
		}
		return null;
	}
	*/

	private static W3CNormalizer createICUNormalizer(ClassLoader classLoader) {
		// First attempt is to try the IBM ICU library
		try {
			if (classLoader.loadClass("com.ibm.icu.text.Normalizer") != null) {
				return new ICUNormalizer();
			}
		} catch (ClassNotFoundException e) {
		}
		return null;
	}
	
	/**
	 * Calculate the expected arguments.
	 * 
	 * @return The expected arguments.
	 */
	public synchronized static Collection expected_args() {
		if (_expected_args == null) {
			_expected_args = new ArrayList();
			_expected_args.add(new SeqType(new XSString(), SeqType.OCC_QMARK));
			_expected_args.add(new SeqType(new XSString(), SeqType.OCC_NONE));
		}

		return _expected_args;
	}
}
