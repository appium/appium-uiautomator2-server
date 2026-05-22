(:*******************************************************:)
(: Test: K-FunctionProlog-67                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: A call to a user function where the argument in the callsite corresponding to a used argument contains a type error. :)
(:*******************************************************:)

declare function local:myFunction($usedArg)
{
	$usedArg
};
local:myFunction(1 + "a string")
