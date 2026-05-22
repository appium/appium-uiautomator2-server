(:*******************************************************:)
(: Test: K-FunctionProlog-61                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: Check that a global variable is in scope despite an unused function being declared. :)
(:*******************************************************:)

declare variable $my := 3;
declare function local:myFunction($my, $arg2, $arg4)
{
	1
};
$my eq 3
