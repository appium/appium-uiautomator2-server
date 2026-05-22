(:*******************************************************:)
(: Test: K-InternalVariablesWith-11                      :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: One prolog variable initialized via another. :)
(:*******************************************************:)
declare variable $var1 := 2;
declare variable $var2 := $var1;
$var2 eq 2