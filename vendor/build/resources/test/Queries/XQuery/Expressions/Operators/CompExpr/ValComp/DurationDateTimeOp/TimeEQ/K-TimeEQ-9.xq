(:*******************************************************:)
(: Test: K-TimeEQ-9                                      :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: The operator 'eq' is not available between xs:dateTime and xs:date . :)
(:*******************************************************:)
xs:time("12:12:23") eq
				       xs:date("1999-12-04")