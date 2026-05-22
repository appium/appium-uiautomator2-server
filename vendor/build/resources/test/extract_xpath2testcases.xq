(:
/*******************************************************************************
 * Copyright (c) 2009 Jin Mingjan and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     Jin Mingjan - bug 262765 -  initial API and implementation
 *******************************************************************************/
:)

(: xq file for extracting the xpath 2 testcases from XQTSCatalog.xml :)

declare namespace ts = "http://www.w3.org/2005/02/query-test-XQTSCatalog";
declare variable $xqtsDoc := doc("file:///D:/XQTSCatalog.xml");
declare variable $xqPathPrefix := "/Queries/XQuery/";
declare variable $resultPathPrefix := "/ExpectedTestResults/";
declare variable $sourcePathPrefix := "/TestSources/";
declare variable $xqFileExtension := ".xq";
declare variable $sourceFileExtension := ".xml";
declare variable $tcs := $xqtsDoc//ts:test-case[@is-XPath2 = true()];

<testcases-xpath2-report>{
                           for $tc in $tcs
                           return
                             <test-case>
                               <xq-file>
                                 { concat($xqPathPrefix, $tc/@FilePath, $tc/ts:query/@name, $xqFileExtension) }
                               </xq-file>
                               <context-files>
                                 {
                                   let $n := count($tc/ts:input-file)
                                   return
                                     if($n = 1) then <input-context>{ concat($sourcePathPrefix, $tc/ts:input-file, $sourceFileExtension) }</input-context>
                                     else
                                       if($n = 2) then (<input-context>{ concat($sourcePathPrefix, $tc/ts:input-file[1], $sourceFileExtension) }</input-context>, <input-context>{ concat($sourcePathPrefix, $tc/ts:input-file[2], $sourceFileExtension) }</input-context>)
                                       else
                                         if(($n = 0) and ($tc/ts:contextItem)) then <input-context>{ concat($sourcePathPrefix, $tc/ts:contextItem, $sourceFileExtension) }</input-context>
                                         else
                                           if(($n = 0) and (count($tc/ts:input-URI) = 1)) then <input-context>{ concat($sourcePathPrefix, $tc/ts:input-URI, $sourceFileExtension) }</input-context>
                                           else
                                             if(($n = 0) and (count($tc/ts:input-URI) = 2)) then (<input-context>{ concat($sourcePathPrefix, $tc/ts:input-URI[1], $sourceFileExtension) }</input-context>, <input-context>{ concat($sourcePathPrefix, $tc/ts:input-URI[2], $sourceFileExtension) }</input-context>)
                                             else
                                               if($tc/ts:defaultCollection) then <collection>{ data($tc/ts:defaultCollection) }</collection>
                                               else ()
                                 }
                               
                               </context-files>
                               <expected-result>
                                 {
                                   if($tc/ts:output-file) then concat($resultPathPrefix, $tc/@FilePath, $tc/ts:output-file[1])
                                   else data($tc/ts:expected-error[1])
                                 }
                               
                               </expected-result>
                             </test-case>
                         }</testcases-xpath2-report>