(:*******************************************************:)
(: Test: K-FunctionProlog-11                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: A user declared function whose return value simply doesn't match the return type(#2). :)
(:*******************************************************:)

declare function local:myFunction() as item()
{
	()
};
local:myFunction()
