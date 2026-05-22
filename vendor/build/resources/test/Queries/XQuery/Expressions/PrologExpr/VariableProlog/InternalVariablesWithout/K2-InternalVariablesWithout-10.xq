(:*******************************************************:)
(: Test: K2-InternalVariablesWithout-10                  :)
(: Written by: Frans Englich                             :)
(: Date: 2006-08-04T17:13:26Z                            :)
(: Purpose: Function arguments shadow global variables.  :)
(:*******************************************************:)
declare variable $local:myVar := local:myFunc(3);
declare function local:myFunc($local:myVar)
{
    $local:myVar
};
local:myFunc(6) eq 6