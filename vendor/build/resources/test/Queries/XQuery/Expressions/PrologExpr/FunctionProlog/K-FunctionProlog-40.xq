(:*******************************************************:)
(: Test: K-FunctionProlog-40                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: Type error in body of user function caused by the argument value. :)
(:*******************************************************:)

declare function local:myFunction($local:myVar)
{
	$local:myVar + 1
};
local:myFunction(1), local:myFunction("this will fail")
