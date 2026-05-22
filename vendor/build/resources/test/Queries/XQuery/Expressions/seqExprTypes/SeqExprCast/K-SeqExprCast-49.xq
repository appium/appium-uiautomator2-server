(:*******************************************************:)
(: Test: K-SeqExprCast-49                                :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: A test whose essence is: `"untyped a " cast as xs:untypedAtomic eq xs:untypedAtomic("untyped a ")`. :)
(:*******************************************************:)
"untyped a   " cast as xs:untypedAtomic 
		eq xs:untypedAtomic("untyped a   ")