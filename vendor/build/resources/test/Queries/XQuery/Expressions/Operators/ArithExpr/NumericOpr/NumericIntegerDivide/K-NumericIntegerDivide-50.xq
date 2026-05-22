(:*******************************************************:)
(: Test: K-NumericIntegerDivide-50                       :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:36+02:00                       :)
(: Purpose: Invoke 'idiv' where an untypedAtomic conversion fails. :)
(:*******************************************************:)
(xs:untypedAtomic("nine") idiv xs:float(5)) eq 1