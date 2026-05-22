(:*******************************************************:)
(: Test: K-FunctionProlog-10                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: A user declared function whose return value simply doesn't match the return type. :)
(:*******************************************************:)

declare function local:myFunction() as xs:double
{
	"This is not a double, it's an xs:string."
};
local:myFunction()
