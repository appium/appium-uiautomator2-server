(:*******************************************************:)
(: Test: K-NumericSubtract-33                            :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:36+02:00                       :)
(: Purpose: A test whose essence is: `(xs:untypedAtomic("3") - xs:untypedAtomic("3")) instance of xs:double`. :)
(:*******************************************************:)
(xs:untypedAtomic("3") -
			       xs:untypedAtomic("3")) instance of xs:double