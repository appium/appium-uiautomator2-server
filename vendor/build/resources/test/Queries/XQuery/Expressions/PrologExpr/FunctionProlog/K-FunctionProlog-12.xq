(:*******************************************************:)
(: Test: K-FunctionProlog-12                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: Variables appearing after a function declaration is not in scope inside the function. :)
(:*******************************************************:)

declare function local:computeSum()
{
	$myVariable
};
declare variable $myVariable := 1;
1
