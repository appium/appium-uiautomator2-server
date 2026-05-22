(:*******************************************************:)
(: Test: K-FunctionProlog-22                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: User functions where the first argument of three arguments is unused. :)
(:*******************************************************:)

declare function local:func($unused, $b, $c)
{
	$b + $c
};
local:func(1, 2, 3) eq 5
