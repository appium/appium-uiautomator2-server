(:*******************************************************:)
(: Test: K-FunctionProlog-30                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: Overloading user functions based on arity.   :)
(:*******************************************************:)

declare function local:myName($var)
{
	$var
};
declare function local:myName()
{
	1
};
(local:myName(4) - 3)  eq local:myName()
