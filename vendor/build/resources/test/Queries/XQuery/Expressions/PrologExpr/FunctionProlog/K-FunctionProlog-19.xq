(:*******************************************************:)
(: Test: K-FunctionProlog-19                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: A user function where all its arguments are unused. :)
(:*******************************************************:)

declare function local:func1($a1, $a2, $a3)
{
1
};
local:func1(1, 2, 3) eq 1
