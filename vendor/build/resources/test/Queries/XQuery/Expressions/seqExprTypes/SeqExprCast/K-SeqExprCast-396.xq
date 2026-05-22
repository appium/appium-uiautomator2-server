(:*******************************************************:)
(: Test: K-SeqExprCast-396                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Simple test of casting a xs:date with UTC offset to xs:gMonth. :)
(:*******************************************************:)

		   xs:gMonth(xs:date("2002-11-23-13:37")) eq xs:gMonth("--11-13:37")
	