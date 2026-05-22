(:*******************************************************:)
(: Test: K-FunctionProlog-60                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: An argument in a user function is not in scope in the query body. :)
(:*******************************************************:)

declare function local:myFunction($arg, $arg2, $arg3)
{
	1
};
$arg3
