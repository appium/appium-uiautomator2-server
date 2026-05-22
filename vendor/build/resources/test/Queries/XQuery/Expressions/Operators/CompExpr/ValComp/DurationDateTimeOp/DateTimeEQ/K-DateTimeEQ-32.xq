(:*******************************************************:)
(: Test: K-DateTimeEQ-32                                 :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: The operator 'gt' is not available between xs:dateTime and xs:date . :)
(:*******************************************************:)
xs:time("22:12:04") gt
				       xs:dateTime("1999-12-04T12:12:23")