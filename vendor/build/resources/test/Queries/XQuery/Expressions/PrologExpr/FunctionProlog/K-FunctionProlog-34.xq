(:*******************************************************:)
(: Test: K-FunctionProlog-34                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: A call to a user declared function which almost is spelled correctly(capitalization wrong). :)
(:*******************************************************:)

declare function local:MyFunction()
{
	1
};
local:myFunction()
