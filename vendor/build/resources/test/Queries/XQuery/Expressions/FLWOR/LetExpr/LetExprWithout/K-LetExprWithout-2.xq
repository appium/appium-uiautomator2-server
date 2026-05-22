(:*******************************************************:)
(: Test: K-LetExprWithout-2                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: A value in a 'let' variable cannot be assigned to with '=', it must be ':='. :)
(:*******************************************************:)
let $i = 5 return 3