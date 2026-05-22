(:*******************************************************:)
(: Test: K-SeqExprCast-349                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Ensure when casting xs:dateTime to xs:string that a milli seconds part of '11' is handled properly. :)
(:*******************************************************:)
xs:string(xs:dateTime("2002-02-15T21:01:23.11")) eq
						 "2002-02-15T21:01:23.11"