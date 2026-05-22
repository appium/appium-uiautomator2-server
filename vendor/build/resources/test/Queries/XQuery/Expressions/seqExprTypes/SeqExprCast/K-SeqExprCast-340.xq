(:*******************************************************:)
(: Test: K-SeqExprCast-340                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Simple test of casting a xs:dateTime with UTC timezone to xs:time. :)
(:*******************************************************:)

		   xs:time(xs:dateTime("2002-11-23T22:12:23.867-00:00")) eq xs:time("22:12:23.867Z")
	