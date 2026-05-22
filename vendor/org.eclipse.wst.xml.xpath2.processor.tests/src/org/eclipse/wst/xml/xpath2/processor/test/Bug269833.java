/*******************************************************************************
 * Copyright (c) 2009, 2017 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     IBM Corporation - bug 269833 - initial API and implementation
 *     Mukul Gandhi    - bug 280798 - PsychoPath support for JDK 1.4
 *******************************************************************************/
package org.eclipse.wst.xml.xpath2.processor.test;

import java.net.URL;
import org.apache.xerces.xs.XSModel;
import org.eclipse.wst.xml.xpath2.processor.ResultSequence;

public class Bug269833 extends AbstractPsychoPathTest{
		
	public static void main(String[] args) {
		Bug269833 test = new Bug269833();        
	    try {        
            test.setUp();
            test.testKeywordAsNodeInXpath();
        } catch (Exception e) {            
            e.printStackTrace();
        }        
    }    
    
	protected void setUp() throws Exception {
		super.setUp();
        URL fileURL = new URL("http://resolve-locally/xml/note.xml");
        loadDOMDocument(fileURL);
     }
 	
 	public void testKeywordAsNodeInXpath() throws Exception {  
 		   // Get XML Schema Information for the Document  
 		   XSModel schema = getGrammar();  
 		  
 		   setupDynamicContext(schema);  
 		    
 		     
 		   String xpath = "/note/to";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);
  
 		    	    
 		   String actual = rs.first().getStringValue();
 		   
 		   assertEquals("Self", actual);	     
 		}  
}