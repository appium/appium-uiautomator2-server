(:*******************************************************:)
(: Test: K-SeqExprCastable-3                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: '+' nor '?' is allowed as a cardinality specifier in 'castable as'. :)
(:*******************************************************:)
("one", "two") castable as xs:string+