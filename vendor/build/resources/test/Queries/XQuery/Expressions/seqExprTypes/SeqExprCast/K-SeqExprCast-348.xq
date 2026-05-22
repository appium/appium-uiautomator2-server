(:*******************************************************:)
(: Test: K-SeqExprCast-348                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Ensure when casting xs:dateTime to xs:string that a milli seconds part of '435' is handled properly. :)
(:*******************************************************:)
xs:string(xs:dateTime("2002-02-15T21:01:23.435")) eq
						 "2002-02-15T21:01:23.435"