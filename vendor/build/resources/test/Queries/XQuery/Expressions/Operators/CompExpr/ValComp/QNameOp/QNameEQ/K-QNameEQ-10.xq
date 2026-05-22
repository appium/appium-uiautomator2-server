(:*******************************************************:)
(: Test: K-QNameEQ-10                                    :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Operator 'ge' is not available between values of type xs:QName. :)
(:*******************************************************:)
QName("example.com/", "p:ncname") ge
					   QName("example.com/", "p:ncname")