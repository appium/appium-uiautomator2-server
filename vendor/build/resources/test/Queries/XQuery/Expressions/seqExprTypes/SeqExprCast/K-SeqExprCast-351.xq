(:*******************************************************:)
(: Test: K-SeqExprCast-351                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Simple xs:dateTime involving time with no milli seconds. :)
(:*******************************************************:)
string(xs:dateTime("2000-08-01T12:44:05")) eq
		 "2000-08-01T12:44:05"