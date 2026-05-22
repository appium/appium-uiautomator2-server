(:*******************************************************:)
(: Test: K-FunctionProlog-50                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: Too few arguments passed to a user function. :)
(:*******************************************************:)

declare function local:myFunction($arg)
{
	$arg
};
local:myFunction()
