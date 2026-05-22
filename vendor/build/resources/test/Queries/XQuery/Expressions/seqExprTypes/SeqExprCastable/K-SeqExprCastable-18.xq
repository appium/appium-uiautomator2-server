(:*******************************************************:)
(: Test: K-SeqExprCastable-18                            :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: When casting to xs:QName the source value can be a xs:QName value. :)
(:*******************************************************:)
QName("", "lname") castable as xs:QName