(:*******************************************************:)
(: Test: K-FunctionProlog-48                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: It is valid to declare an argument to be of type empty-sequence(). :)
(:*******************************************************:)

declare function local:myFunction($arg as empty-sequence())
{
	$arg
};
empty(local:myFunction(()))
