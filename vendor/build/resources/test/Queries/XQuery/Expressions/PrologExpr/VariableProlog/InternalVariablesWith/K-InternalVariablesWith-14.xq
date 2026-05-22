(:*******************************************************:)
(: Test: K-InternalVariablesWith-14                      :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: One prolog variable depending on a user function, but where the types doesn't match. :)
(:*******************************************************:)
declare variable $var1 as xs:string := local:myFunc();
declare function local:myFunc()
{
	1
};
$var1 eq 1
