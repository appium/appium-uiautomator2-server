(:*******************************************************:)
(: Test: K2-ExternalVariablesWith-1                      :)
(: Written by: Frans Englich                             :)
(: Date: 2006-08-04T17:13:26Z                            :)
(: Purpose: The query contains a type error despite the 'treat as' declaration. :)
(:*******************************************************:)
declare variable $var1 as xs:string := 1 treat as item();
$var1