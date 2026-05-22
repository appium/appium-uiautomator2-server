(:*******************************************************:)
(: Test: K-SeqExprCast-339                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Simple test of casting a xs:dateTime with UTC offset to xs:date. :)
(:*******************************************************:)

		   xs:date(xs:dateTime("2002-11-23T22:12:23.867-13:37")) eq xs:date("2002-11-23-13:37")
	