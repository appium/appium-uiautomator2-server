(:*******************************************************:)
(: Test: K-InternalVariablesWith-13                      :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: One prolog variable depending on a user function, but where the user-specified types doesn't match. :)
(:*******************************************************:)
declare variable $var1 as xs:string := local:myFunc();
declare function local:myFunc() as xs:integer
{
	1
};
$var1 eq 1
