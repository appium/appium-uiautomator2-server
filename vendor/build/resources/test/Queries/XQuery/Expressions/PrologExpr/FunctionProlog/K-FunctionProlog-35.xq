(:*******************************************************:)
(: Test: K-FunctionProlog-35                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: A call to a user declared function which almost is spelled correctly(#2). :)
(:*******************************************************:)

declare function local:MyFunction()
{
	1
};
local:myFunctionn()
