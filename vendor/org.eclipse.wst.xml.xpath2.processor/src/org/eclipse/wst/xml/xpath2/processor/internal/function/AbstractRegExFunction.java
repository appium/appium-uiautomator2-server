/*******************************************************************************
 * Copyright (c) 2009, 2011 Standards for Technology in Automotive Retail and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     David Carver - bug 262765 - initial API and implementation
 *     Mukul Gandhi - bug 280798 - PsychoPath support for JDK 1.4
 *******************************************************************************/
package org.eclipse.wst.xml.xpath2.processor.internal.function;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.wst.xml.xpath2.processor.internal.types.QName;

public abstract class AbstractRegExFunction extends Function {
	protected static final String validflags = "smix";

	public AbstractRegExFunction(QName name, int arity) {
		super(name, arity);
	}
	
	public AbstractRegExFunction(QName name, int min_arity, int max_arity) {
		super(name, min_arity, max_arity);
	}
	
	protected static boolean matches(String pattern, String flags, String src) {
		boolean fnd = false;
		if (pattern.indexOf("-[") != -1) {
			pattern = pattern.replaceAll("\\-\\[", "&&[^");
		}
		Matcher m = compileAndExecute(pattern, flags, src);
		while (m.find()) {
			fnd = true;
		}
		return fnd;
	}
	
	protected static Matcher regex(String pattern, String flags, String src) {
		Matcher matcher = compileAndExecute(pattern, flags, src);
		return matcher;
	}
	
	private static Matcher compileAndExecute(String pattern, String flags, String src) {
		int flag = Pattern.UNIX_LINES;
		if (flags != null) {
			if (flags.indexOf("m") >= 0) {
				flag = flag | Pattern.MULTILINE;
			}
			if (flags.indexOf("s") >= 0) {
				flag = flag | Pattern.DOTALL;
			}
			if (flags.indexOf("i") >= 0) {
				flag = flag | Pattern.CASE_INSENSITIVE;
			}
			
			if (flags.indexOf("x") >= 0) {
				flag = flag | Pattern.COMMENTS;
			}
		}
		
		Pattern p = Pattern.compile(pattern, flag);
		return p.matcher(src);
	}

}
