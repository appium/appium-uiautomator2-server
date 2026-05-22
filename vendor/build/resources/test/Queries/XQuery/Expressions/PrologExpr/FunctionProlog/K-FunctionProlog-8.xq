(:*******************************************************:)
(: Test: K-FunctionProlog-8                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: Arguments in functions cannot have default values initialized with ':='(or in any other way). :)
(:*******************************************************:)
declare function local:myFunction($arg := 1)
		{1};
		true()