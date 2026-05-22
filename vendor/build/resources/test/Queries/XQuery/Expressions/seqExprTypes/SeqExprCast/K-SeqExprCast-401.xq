(:*******************************************************:)
(: Test: K-SeqExprCast-401                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Simple test of casting a xs:date with UTC timezone to xs:time. :)
(:*******************************************************:)

		   xs:dateTime(xs:date("2002-11-23-00:00"))
		   eq xs:dateTime("2002-11-23T00:00:00.000Z")
	