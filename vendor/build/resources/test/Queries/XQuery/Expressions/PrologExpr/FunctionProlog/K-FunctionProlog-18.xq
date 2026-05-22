(:*******************************************************:)
(: Test: K-FunctionProlog-18                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: Two user functions using global variables.   :)
(:*******************************************************:)

declare variable $var1 := 1;
declare function local:func1()
{
	$var1
};

declare variable $var2 := 2;
declare function local:func2()
{
	$var2
};
1 eq local:func1() and 2 eq local:func2()
