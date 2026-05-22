(:*******************************************************:)
(: Test: K-TimeEQ-11                                     :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: The operator 'le' is not available between xs:dateTime and xs:date . :)
(:*******************************************************:)
xs:time("12:12:23") le
				       xs:date("1999-12-04")