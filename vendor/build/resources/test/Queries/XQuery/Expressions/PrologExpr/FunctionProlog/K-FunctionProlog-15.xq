(:*******************************************************:)
(: Test: K-FunctionProlog-15                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: A user function whose return type doesn't match the body, which can be statically inferred. :)
(:*******************************************************:)

declare function local:myFunction() as xs:anyURI
{
	1
};
true()
