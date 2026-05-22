(:*******************************************************:)
(: Test: K-NumericIntegerDivide-51                       :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:36+02:00                       :)
(: Purpose: Invoke 'idiv' where an untypedAtomic conversion fails. :)
(:*******************************************************:)
(xs:float(9) idiv xs:untypedAtomic("five")) eq 1