(:*******************************************************:)
(: Test: K-SeqExprCast-71                                :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Casting xs:untypedAtomic to xs:QName is an error. :)
(:*******************************************************:)
xs:untypedAtomic("ncname") cast as xs:QName?