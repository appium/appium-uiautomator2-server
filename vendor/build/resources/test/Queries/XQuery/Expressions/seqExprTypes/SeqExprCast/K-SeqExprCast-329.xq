(:*******************************************************:)
(: Test: K-SeqExprCast-329                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Simple test of casting a xs:dateTime with UTC offset to xs:gYear. :)
(:*******************************************************:)

		   xs:gYear(xs:dateTime("2002-11-23T23:12:23.867-13:37")) eq xs:gYear("2002-13:37")
	