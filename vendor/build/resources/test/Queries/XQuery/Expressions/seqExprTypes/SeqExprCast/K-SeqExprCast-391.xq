(:*******************************************************:)
(: Test: K-SeqExprCast-391                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Simple test of casting a xs:date with UTC timezone to xs:gYear. :)
(:*******************************************************:)

		   xs:gYear(xs:date("2002-11-23Z")) eq xs:gYear("2002Z")
	