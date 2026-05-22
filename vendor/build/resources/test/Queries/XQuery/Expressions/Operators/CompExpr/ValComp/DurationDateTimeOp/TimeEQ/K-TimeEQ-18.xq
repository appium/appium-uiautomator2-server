(:*******************************************************:)
(: Test: K-TimeEQ-18                                     :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: The operator 'lt' is not available between xs:dateTime and xs:date . :)
(:*******************************************************:)
xs:date("1999-12-04") lt
				       xs:time("12:12:23")