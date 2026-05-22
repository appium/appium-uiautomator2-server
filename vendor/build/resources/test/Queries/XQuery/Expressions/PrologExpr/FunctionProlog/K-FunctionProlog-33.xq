(:*******************************************************:)
(: Test: K-FunctionProlog-33                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: A type error inside a function.              :)
(:*******************************************************:)

declare function local:myFunction()
{
	"a string" + 1
};
true()
