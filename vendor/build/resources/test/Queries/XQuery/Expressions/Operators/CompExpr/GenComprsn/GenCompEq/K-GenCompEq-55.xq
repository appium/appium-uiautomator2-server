(:*******************************************************:)
(: Test: K-GenCompEq-55                                  :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: General comparison which fails due to invalid operator combination or casting. :)
(:*******************************************************:)

		(xs:untypedAtomic("1"), xs:anyURI("example.com")) =
		(xs:untypedAtomic("2.0"), 3.0)