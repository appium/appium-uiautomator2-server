(:*******************************************************:)
(: Test: K-FunctionProlog-29                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: A function declaration duplicated; difference in arguments name is insignificant. :)
(:*******************************************************:)

declare function local:myName($myvar)
{
	1
};
declare function local:myName($myvar2)
{
	1
};
1
