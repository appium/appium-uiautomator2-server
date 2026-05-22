(:*******************************************************:)
(: Test: K-SeqExprCast-332                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Simple test of casting a xs:dateTime with UTC timezone to xs:gMonth. :)
(:*******************************************************:)

		   xs:gMonth(xs:dateTime("2002-11-23T22:12:23.867-00:00")) eq xs:gMonth("--11Z")
	