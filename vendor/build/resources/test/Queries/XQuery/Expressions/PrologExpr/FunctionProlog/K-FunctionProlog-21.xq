(:*******************************************************:)
(: Test: K-FunctionProlog-21                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: User functions where the middle argument of three arguments is unused. :)
(:*******************************************************:)

declare function local:func($a, $unused, $c)
{
	$a + $c
};
local:func(1, 2, 3) eq 4
