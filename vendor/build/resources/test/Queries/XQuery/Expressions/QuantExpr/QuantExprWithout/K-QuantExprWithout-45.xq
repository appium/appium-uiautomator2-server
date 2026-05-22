(:*******************************************************:)
(: Test: K-QuantExprWithout-45                           :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: A binding in a 'every' quantification shadows global variables. :)
(:*******************************************************:)
declare variable $i := false();
		every $i in (true(), true()) satisfies $i