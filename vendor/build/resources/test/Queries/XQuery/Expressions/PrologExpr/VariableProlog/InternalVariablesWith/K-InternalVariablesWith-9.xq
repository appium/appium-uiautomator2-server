(:*******************************************************:)
(: Test: K-InternalVariablesWith-9                       :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: A prolog variable depending on a variable which is not in scope. :)
(:*******************************************************:)
declare variable $var1 := $var2; 
declare variable $var2 := 2;
$var1