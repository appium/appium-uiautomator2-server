(:*******************************************************:)
(: Test: K-FunctionProlog-47                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: 'empty-sequence()? is syntactically invalid. :)
(:*******************************************************:)

declare function local:myFunction($arg as empty-sequence()?)
{
	$arg
};
local:myFunction(())
