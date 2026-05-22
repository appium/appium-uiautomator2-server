(:*******************************************************:)
(: Test: K-FunctionProlog-45                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: '1' doesn't match the empty-sequence().      :)
(:*******************************************************:)

declare function local:myFunction($arg as empty-sequence())
{
	$arg
};
local:myFunction(1)
