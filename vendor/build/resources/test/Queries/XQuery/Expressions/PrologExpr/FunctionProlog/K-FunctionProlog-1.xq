(:*******************************************************:)
(: Test: K-FunctionProlog-1                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: The 'XPath Data Types' namespace is not reserved anymore, although it was in older drafts. :)
(:*******************************************************:)
declare namespace test = "http://www.w3.org/2005/xpath-datatypes";
		declare function test:myFunction()
		{ 1};
	1 eq 1