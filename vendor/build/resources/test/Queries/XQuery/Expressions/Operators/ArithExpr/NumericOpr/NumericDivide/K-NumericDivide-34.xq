(:*******************************************************:)
(: Test: K-NumericDivide-34                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:36+02:00                       :)
(: Purpose: A test whose essence is: `(xs:double(3) div xs:untypedAtomic("3")) eq 1`. :)
(:*******************************************************:)
(xs:double(3) div xs:untypedAtomic("3")) eq 1