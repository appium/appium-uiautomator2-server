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
 *     David Carver - bug 262765 - eased restriction on data type...convert numerics to XSDouble.
 *     Jesper S Moller - bug 285806 - fixed fn:subsequence for indexes starting before 1
 *     Mukul Gandhi - bug 280798 - PsychoPath support for JDK 1.4
 *     Mukul Gandhi - bug 338999 - improving compliance of function 'fn:subsequence'. implementing full arity support.
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal.function;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.wst.xml.xpath2.api.Item;
import org.eclipse.wst.xml.xpath2.api.ResultBuffer;
import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.internal.types.AnyType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.NumericType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.QName;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSDouble;

/**
 * Returns the contiguous sequence of items in the value of $sourceSeq beginning
 * at the position indicated by the value of $startingLoc and continuing for the
 * number of items indicated by the value of $length. More specifically, returns
 * the items in $sourceString whose position $p obeys: - fn:round($startingLoc)
 * <= $p < fn:round($startingLoc) + fn:round($length)
 */
public class FnSubsequence extends Function {
	
	/**
	 * Constructor for FnSubsequence.
	 */
	public FnSubsequence() {
		super(new QName("subsequence"), 2, 3);
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
	public ResultSequence evaluate(Collection args, org.eclipse.wst.xml.xpath2.api.EvaluationContext ec) throws DynamicError {
		return subsequence(args);
	}

	/**
	 * Subsequence operation.
	 * 
	 * @param args
	 *            Result from the expressions evaluation.
	 * @throws DynamicError
	 *             Dynamic error.
	 * @return Result of fn:subsequence operation.
	 */
	public static ResultSequence subsequence(Collection args) throws DynamicError {		
		ResultBuffer rs = new ResultBuffer();

		// get args
		Iterator citer = args.iterator();
		
		ResultSequence seq = (ResultSequence) citer.next();		
		if (seq.empty())
			return ResultBuffer.EMPTY;
		
		ResultSequence startLoc = (ResultSequence) citer.next();
		ResultSequence length = null; 		
		if (citer.hasNext()) {
			length = (ResultSequence) citer.next(); 
		}

		Item at = startLoc.first();
		if (!(at instanceof NumericType)) {
			DynamicError.throw_type_error();
		}

		at = new XSDouble(at.getStringValue());

		int start = (int) ((XSDouble) at).double_value();
        int effectiveNoItems = 0; // no of items beyond index >= 1 that are added to the result
        
	    if (length != null) {
	    	// the 3rd argument is present
			if (length.size() != 1)
				DynamicError.throw_type_error();
			at = length.first();
			if (!(at instanceof NumericType)) {
				DynamicError.throw_type_error();
			}
			at = new XSDouble(at.getStringValue());
			int len = (int) ((XSDouble) at).double_value();
			if (len < 0) {
				DynamicError.throw_type_error();	
			}

			if (start <= 0) {				
				effectiveNoItems = start + len - 1;	
				start = 1;
			}
			else {
				effectiveNoItems = len;
			}
		}
	    else {
	    	// 3rd argument is absent
	    	if (start <= 0) {
	    		start = 1;
	    		effectiveNoItems = seq.size(); 
	    	}
	    	else {
	    		effectiveNoItems = seq.size() - start + 1; 
	    	}
	    }
		
		int pos = 1; // index running parallel to the iterator
		int addedItems = 0;
		if (effectiveNoItems > 0) {
			for (Iterator seqIter = seq.iterator(); seqIter.hasNext();) {
				at = (AnyType) seqIter.next();
				if (start <= pos && addedItems < effectiveNoItems) {				
					rs.add(at);
					addedItems++;
				}
				pos++;
			}
		}
		
		return rs.getSequence();
	}
	
}