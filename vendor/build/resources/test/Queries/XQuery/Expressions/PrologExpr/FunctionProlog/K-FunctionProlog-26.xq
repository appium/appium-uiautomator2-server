(:*******************************************************:)
(: Test: K-FunctionProlog-26                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: A function declaration duplicated.           :)
(:*******************************************************:)

declare function local:myName()
{
	1
};
declare function local:myName()
{
	1
};
1
