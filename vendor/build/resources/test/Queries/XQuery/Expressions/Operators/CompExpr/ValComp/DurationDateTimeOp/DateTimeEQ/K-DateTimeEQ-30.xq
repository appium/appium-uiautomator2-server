(:*******************************************************:)
(: Test: K-DateTimeEQ-30                                 :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: The operator 'lt' is not available between xs:dateTime and xs:date . :)
(:*******************************************************:)
xs:time("22:12:04") lt
				       xs:dateTime("1999-12-04T12:12:23")