(:*******************************************************:)
(: Test: K-FunctionProlog-17                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: When declaring a function, the paranteses must be present even though it doesn't have any arguments. :)
(:*******************************************************:)

declare function local:myFunction
{
	1
};
true()
