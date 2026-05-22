(:*******************************************************:)
(: Test: K-ForExprWithout-52                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: A binding in a for-expression shadows global variables. :)
(:*******************************************************:)
declare variable $i := false();
declare variable $t := false();
deep-equal(
for $i in (true(), true()), $t in (true(), true()) return ($i, $t),
(true(), true(), true(), true(), true(), true(), true(), true()))