(:*******************************************************:)
(: Test: K2-ExternalVariablesWith-2                      :)
(: Written by: Frans Englich                             :)
(: Date: 2006-08-04T17:13:26Z                            :)
(: Purpose: Use a range variable inside the assignment expression of a global variable. :)
(:*******************************************************:)
declare variable $var1 := let $var1 := 1 return 1;
$var1 eq 1