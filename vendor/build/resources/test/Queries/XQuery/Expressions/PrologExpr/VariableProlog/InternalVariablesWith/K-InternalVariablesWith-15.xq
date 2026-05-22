(:*******************************************************:)
(: Test: K-InternalVariablesWith-15                      :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: One prolog variable depending on itself.     :)
(:*******************************************************:)
declare variable $var1 := $var1;
true()