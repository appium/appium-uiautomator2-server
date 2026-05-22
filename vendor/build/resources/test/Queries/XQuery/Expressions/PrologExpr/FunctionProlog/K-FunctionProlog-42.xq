(:*******************************************************:)
(: Test: K-FunctionProlog-42                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: Item type error in argument value.           :)
(:*******************************************************:)

declare function local:myFunction($arg as item(), $arg2 as xs:integer)
{
	$arg, $arg2
};
local:myFunction("3", "3")
