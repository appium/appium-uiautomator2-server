(:*******************************************************:)
(: Test: K-FunctionProlog-27                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: A function declaration duplicated; difference in return types is insignificant. :)
(:*******************************************************:)

declare function local:myName() as xs:integer
{
	1
};
declare function local:myName() as xs:nonPositiveInteger
{
	1
};
1
