(:*******************************************************:)
(: Test: K-InternalVariablesWith-16                      :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: A prolog variable having a circular dependency, stretching through many functions. :)
(:*******************************************************:)
declare variable $var := local:func1();
declare function local:func1()
{
	local:func2()
};
declare function local:func2()
{
	local:func3()
};
declare function local:func3()
{
	local:func4()
};
declare function local:func4()
{
	$var
};
boolean($var)
