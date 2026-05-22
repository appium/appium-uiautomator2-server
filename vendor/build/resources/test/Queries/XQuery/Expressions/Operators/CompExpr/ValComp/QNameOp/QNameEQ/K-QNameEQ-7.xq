(:*******************************************************:)
(: Test: K-QNameEQ-7                                     :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Operator 'lt' is not available between values of type xs:QName. :)
(:*******************************************************:)
QName("example.com/", "p:ncname") lt
					   QName("example.com/", "p:ncname")