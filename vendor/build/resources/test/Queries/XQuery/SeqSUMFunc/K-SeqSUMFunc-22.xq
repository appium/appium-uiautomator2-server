(:*******************************************************:)
(: Test: K-SeqSUMFunc-22                                 :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:41+02:00                       :)
(: Purpose: fn:sum() having an input sequence whose static type is xs:anyAtomicType. :)
(:*******************************************************:)
sum(remove((1.0, xs:float(1), 2, xs:untypedAtomic("3")), 1)) eq 6