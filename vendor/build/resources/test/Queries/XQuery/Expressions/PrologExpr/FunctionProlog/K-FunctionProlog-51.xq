(:*******************************************************:)
(: Test: K-FunctionProlog-51                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: empty-sequence() as return type, and a body containing fn:error(). :)
(:*******************************************************:)

declare function local:myFunction() as empty-sequence()
{
	fn:error()
};
local:myFunction()
