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
 *     Jesper Steen Moller - bug 281159 - fix document loading and resolving URIs 
 *     Mukul Gandhi - bug 280798 - PsychoPath support for JDK 1.4
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal.function;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.wst.xml.xpath2.api.EvaluationContext;
import org.eclipse.wst.xml.xpath2.api.ResultBuffer;
import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.internal.SeqType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.DocType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.QName;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSString;
import org.w3c.dom.Document;

/**
 * Summary: This function takes an xs:string as argument and returns a sequence
 * of nodes obtained by interpreting $arg as an xs:anyURI and resolving it
 * according to the mapping specified in Available collections described in
 * Section C.2 Dynamic Context Components. If Available collections provides a
 * mapping from this string to a sequence of nodes, the function returns that
 * sequence. If Available collections maps the string to an empty sequence, 
 * then the function returns an empty sequence. If Available collections
 * provides no mapping for the string, an error is raised [err:FODC0004]. If
 * $arg is not specified, the function returns the sequence of the nodes in the
 * default collection in the dynamic context. See Section C.2 Dynamic Context
 * ComponentsXP. If the value of the default collection is undefined an error
 * is raised [err:FODC0002].
 *
 * If the $arg is a relative xs:anyURI, it is resolved against the value of the
 * base-URI property from the static context. If $arg is not a valid xs:anyURI,
 * an error is raised [err:FODC0004].
 *
 * If $arg is the empty sequence, the function behaves as if it had been called
 * without an argument. See above.
 *
 * By default, this function is ·stable·. This means that repeated calls on the
 * function with the same argument will return the same result. However, for
 * performance reasons, implementations may provide a user option to evaluate
 * the function without a guarantee of stability. The manner in which any such
 * option is provided is ·implementation-defined·. If the user has not selected
 * such an option, a call to this function must either return a stable result or
 * must raise an error: [err:FODC0003].
 */
public class FnCollection extends Function {
	private static Collection _expected_args = null;
	
	public static final String DEFAULT_COLLECTION_URI = "http://www.w3.org/2005/xpath-functions/collection/default";

	/**
	 * Constructor for FnDoc.
	 */
	public FnCollection() {
		super(new QName("collection"), 0, 1);
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
	public ResultSequence evaluate(Collection args, EvaluationContext ec) throws DynamicError {
		return collection(args, ec);
	}

	/**
	 * Doc operation.
	 * 
	 * @param args
	 *            Result from the expressions evaluation.
	 * @param dc
	 *            Result of dynamic context operation.
	 * @throws DynamicError
	 *             Dynamic error.
	 * @return Result of fn:doc operation.
	 */
	public static ResultSequence collection(Collection args, EvaluationContext ec)
			throws DynamicError {
		Collection cargs = Function.convert_arguments(args, expected_args());

		// get args
		Iterator argiter = cargs.iterator();
		ResultSequence arg1 = null;
		
		String uri = DEFAULT_COLLECTION_URI;
		if (argiter.hasNext()) {
			arg1 = (ResultSequence) argiter.next();
			uri = ((XSString) arg1.first()).value();
		}
		
		try {
			new URI(uri);
		} catch (URISyntaxException ex) {
			throw DynamicError.doc_not_found(null);
		}
		
		if (uri.indexOf(":") < 0) {
			throw DynamicError.invalidCollectionArgument();
		}
		

		URI resolved = ec.getDynamicContext().resolveUri(uri);
		if (resolved == null)
			throw DynamicError.invalid_doc(null);

		ResultSequence rs = getCollection(uri, ec);
		if (rs.empty())
			throw DynamicError.doc_not_found(null);

		return rs;
	}

	/**
	 * Obtain a list of expected arguments.
	 * 
	 * @return Result of operation.
	 */
	public synchronized static Collection expected_args() {
		if (_expected_args == null) {
			_expected_args = new ArrayList();
			SeqType arg = new SeqType(new XSString(), SeqType.OCC_QMARK);
			_expected_args.add(arg);
		}

		return _expected_args;
	}
	
	private static ResultSequence getCollection(String uri, EvaluationContext ec) {
		ResultBuffer rs = new ResultBuffer();
		Map/*<String, List<Document>>*/ collectionMap = ec.getDynamicContext().getCollections();
		List/*<Document>*/ docList = (List) collectionMap.get(uri);
		for (int i = 0; i < docList.size(); i++) {
			Document doc = (Document) docList.get(i);
			rs.add(new DocType(doc, ec.getStaticContext().getTypeModel()));
		}
		return rs.getSequence();
		
	}
}
