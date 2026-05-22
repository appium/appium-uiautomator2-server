(:*******************************************************:)
(: Test: K-FunctionProlog-44                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: Item type error in argument value. xs:decimal doesn't match xs:integer. :)
(:*******************************************************:)

declare function local:myFunction($arg as item()?, $arg2 as xs:integer)
{
	$arg, $arg2
};
local:myFunction((), 4.1)
