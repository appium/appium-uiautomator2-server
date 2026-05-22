(:*******************************************************:)
(: Test: K-QNameEQ-5                                     :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Operator 'lt' is not available between xs:QName and xs:integer. :)
(:*******************************************************:)
QName("example.com/", "p:ncname") lt 1