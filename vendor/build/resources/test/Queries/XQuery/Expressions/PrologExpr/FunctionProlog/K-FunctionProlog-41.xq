(:*******************************************************:)
(: Test: K-FunctionProlog-41                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: Type error(cardinality) in return value of user function caused by the argument value. :)
(:*******************************************************:)

declare function local:myFunction($local:arg) as item()
{
	1, $local:arg
};
local:myFunction(()), local:myFunction(1)
