(:*******************************************************:)
(: Test: K-FunctionProlog-49                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: empty-sequence() as return type, but body doesn't match when run. :)
(:*******************************************************:)

declare function local:myFunction($arg) as empty-sequence()
{
	$arg
};
local:myFunction(1)
