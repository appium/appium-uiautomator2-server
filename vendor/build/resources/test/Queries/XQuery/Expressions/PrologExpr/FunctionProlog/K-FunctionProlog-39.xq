(:*******************************************************:)
(: Test: K-FunctionProlog-39                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: Function arguments shadows global variables. :)
(:*******************************************************:)

declare variable $local:myVar := 1;
declare function local:myFunction($local:myVar)
{
	$local:myVar
};
$local:myVar eq 1 and local:myFunction(2) eq 2
