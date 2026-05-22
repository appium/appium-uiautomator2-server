(:*******************************************************:)
(: Test: K-SeqExprCast-397                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Simple test of casting a xs:date with UTC timezone to xs:gMonthDay. :)
(:*******************************************************:)

		   xs:gMonthDay(xs:date("2002-11-23-00:00")) eq xs:gMonthDay("--11-23Z")
	