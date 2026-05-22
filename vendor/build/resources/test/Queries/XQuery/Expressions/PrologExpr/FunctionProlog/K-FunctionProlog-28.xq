(:*******************************************************:)
(: Test: K-FunctionProlog-28                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: A function declaration duplicated; difference in arguments types is insignificant. :)
(:*******************************************************:)

declare function local:myName($myvar as xs:integer)
{
	1
};
declare function local:myName($myvar as xs:nonPositiveInteger)
{
	1
};
1
