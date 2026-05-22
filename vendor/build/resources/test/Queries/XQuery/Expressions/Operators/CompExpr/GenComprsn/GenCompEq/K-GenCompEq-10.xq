(:*******************************************************:)
(: Test: K-GenCompEq-10                                  :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Example from the XPath 2.0 specification.    :)
(:*******************************************************:)
not((xs:untypedAtomic("1"), xs:untypedAtomic("2")) =
				   (xs:untypedAtomic("2.0"), 3.0))