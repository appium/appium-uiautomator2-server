(:*******************************************************:)
(: Test: K-SeqExprCast-330                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Simple test of casting a xs:dateTime with UTC timezone to xs:gYearMonth. :)
(:*******************************************************:)

		   xs:gYearMonth(xs:dateTime("2002-11-23T23:12:23.867-00:00")) eq xs:gYearMonth("2002-11Z")
	