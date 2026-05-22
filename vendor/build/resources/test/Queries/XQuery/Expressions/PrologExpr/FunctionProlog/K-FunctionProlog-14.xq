(:*******************************************************:)
(: Test: K-FunctionProlog-14                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: A user function which when run doesn't match the declared returned type. :)
(:*******************************************************:)

declare function local:myFunction() as xs:integer
{
	subsequence((1, 2, "a string"), 3 ,1)
};
fn:boolean(local:myFunction())
