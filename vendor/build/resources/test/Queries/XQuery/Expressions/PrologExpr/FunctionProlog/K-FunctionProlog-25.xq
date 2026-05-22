(:*******************************************************:)
(: Test: K-FunctionProlog-25                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: A function and a variable can have the same name. :)
(:*******************************************************:)

declare variable $local:myName := 1;
declare function local:myName()
{
	1
};
$local:myName eq local:myName()
