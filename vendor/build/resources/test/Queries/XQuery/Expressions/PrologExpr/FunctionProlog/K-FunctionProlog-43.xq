(:*******************************************************:)
(: Test: K-FunctionProlog-43                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: Cardinality error in argument value.         :)
(:*******************************************************:)

declare function local:myFunction($arg as item(), $arg2 as xs:integer)
{
	$arg, $arg2
};
local:myFunction("3", ())
