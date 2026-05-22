(:*******************************************************:)
(: Test: K2-InternalVariablesWithout-9                   :)
(: Written by: Frans Englich                             :)
(: Date: 2006-08-04T17:13:26Z                            :)
(: Purpose: A variable depending on its self through the argument of a user function callsite. :)
(:*******************************************************:)
declare variable $local:myVar := local:myFunc(3);
declare function local:myFunc($arg)
{
local:myFunc($local:myVar)
};
$local:myVar