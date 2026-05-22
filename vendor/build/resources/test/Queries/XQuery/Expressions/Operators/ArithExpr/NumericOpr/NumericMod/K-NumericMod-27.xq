(:*******************************************************:)
(: Test: K-NumericMod-27                                 :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:36+02:00                       :)
(: Purpose: A test whose essence is: `(xs:decimal(5) mod xs:untypedAtomic("3")) eq 2`. :)
(:*******************************************************:)
(xs:decimal(5) mod xs:untypedAtomic("3")) eq 2