(:*******************************************************:)
(: Test: K-NumericEqual-45                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:36+02:00                       :)
(: Purpose: Test automatic xs:untypedAtomic conversion.  :)
(:*******************************************************:)
count(xs:untypedAtomic("1") to 3) eq 3