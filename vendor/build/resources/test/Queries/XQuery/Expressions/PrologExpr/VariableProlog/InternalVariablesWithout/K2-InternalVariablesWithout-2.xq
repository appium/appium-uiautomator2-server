(:*******************************************************:)
(: Test: K2-InternalVariablesWithout-2                   :)
(: Written by: Frans Englich                             :)
(: Date: 2006-08-04T17:13:26Z                            :)
(: Purpose: A variable depending on a recursive function. :)
(:*******************************************************:)

declare variable $local:myVar := local:myFunction();
declare function local:myFunction()
{
        $local:myVar, 1, local:myFunction()
};
$local:myVar