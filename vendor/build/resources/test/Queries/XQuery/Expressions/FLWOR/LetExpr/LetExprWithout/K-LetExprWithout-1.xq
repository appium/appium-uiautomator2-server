(:*******************************************************:)
(: Test: K-LetExprWithout-1                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: A 'let' variable is out-of-scope due to FLWOR has higher precendence than the comma operator. :)
(:*******************************************************:)
let $i := 5, $j := 20 * $i
return $i, $j