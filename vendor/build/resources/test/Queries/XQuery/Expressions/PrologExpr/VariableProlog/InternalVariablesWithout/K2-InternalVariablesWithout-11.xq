(:*******************************************************:)
(: Test: K2-InternalVariablesWithout-11                  :)
(: Written by: Frans Englich                             :)
(: Date: 2006-08-04T17:13:26Z                            :)
(: Purpose: A variable initialized with a function that doesn't exist. :)
(:*******************************************************:)
declare variable $local:myVar := local:thisFunctionDoesNotExist();
1