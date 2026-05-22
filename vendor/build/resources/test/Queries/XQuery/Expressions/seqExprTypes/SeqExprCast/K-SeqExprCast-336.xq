(:*******************************************************:)
(: Test: K-SeqExprCast-336                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Simple test of casting a xs:dateTime with UTC timezone to xs:gDay. :)
(:*******************************************************:)

		   xs:gDay(xs:dateTime("2002-11-23T22:12:23.867-00:00")) eq xs:gDay("---23Z")
	