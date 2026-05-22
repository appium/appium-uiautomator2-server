(:*******************************************************:)
(: Test: K-QNameEQ-4                                     :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: A test whose essence is: `QName("example.com/", "p:ncname") ne QName("example.com/", "p:ncnameNope")`. :)
(:*******************************************************:)
QName("example.com/", "p:ncname") ne
			       QName("example.com/", "p:ncnameNope")