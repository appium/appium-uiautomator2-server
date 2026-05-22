(:*******************************************************:)
(: Test: K-FunctionProlog-58                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: A call to a user function where the argument in the callsite corresponding to an unused argument contains a type error. :)
(:*******************************************************:)

declare function local:myFunction($unusedArg)
{
	true()
};
local:myFunction(1 + "a string")
