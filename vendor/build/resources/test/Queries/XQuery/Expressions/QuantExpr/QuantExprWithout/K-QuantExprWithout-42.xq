(:*******************************************************:)
(: Test: K-QuantExprWithout-42                           :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: A binding in a 'some' quantification shadows global variables. :)
(:*******************************************************:)
declare variable $i := false();
		some $i in (true(), true(), true()) satisfies $i