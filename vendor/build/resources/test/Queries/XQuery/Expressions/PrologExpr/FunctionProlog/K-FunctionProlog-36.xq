(:*******************************************************:)
(: Test: K-FunctionProlog-36                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: The variable '$myArg' is in scope inside the function, but not in the query body. :)
(:*******************************************************:)

declare function local:MyFunction($myArg)
{
	0
};
$myArg
