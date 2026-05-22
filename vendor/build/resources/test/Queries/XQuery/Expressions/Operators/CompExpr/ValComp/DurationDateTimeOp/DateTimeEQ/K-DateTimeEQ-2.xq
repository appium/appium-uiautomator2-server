(:*******************************************************:)
(: Test: K-DateTimeEQ-2                                  :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Simple test of 'eq' for xs:dateTime.         :)
(:*******************************************************:)
not(xs:dateTime("2004-08-12T23:01:04.12") eq
			       xs:dateTime("2004-08-12T23:01:04.13"))