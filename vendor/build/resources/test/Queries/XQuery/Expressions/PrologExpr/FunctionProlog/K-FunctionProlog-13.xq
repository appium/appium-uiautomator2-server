(:*******************************************************:)
(: Test: K-FunctionProlog-13                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: Namespaces declarations appearing after a function declaration are not in scope inside the function. :)
(:*******************************************************:)

declare function local:computeSum()
{
	$prefix:myVariable
};
declare namespaces prefix = "example.com/Anamespace";
1
