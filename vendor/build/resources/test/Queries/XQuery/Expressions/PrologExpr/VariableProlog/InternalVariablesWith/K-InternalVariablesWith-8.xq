(:*******************************************************:)
(: Test: K-InternalVariablesWith-8                       :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: '=' cannot be used to assign values in 'declare variable', it must be ':='. :)
(:*******************************************************:)
declare variable $var1 = 1; 1