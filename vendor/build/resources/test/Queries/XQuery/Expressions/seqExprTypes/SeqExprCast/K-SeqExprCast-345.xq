(:*******************************************************:)
(: Test: K-SeqExprCast-345                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Ensure when casting xs:dateTime to xs:string that milli seconds have no trailing zeros. :)
(:*******************************************************:)
xs:string(xs:dateTime("2002-02-15T21:01:23.100")) eq
						 "2002-02-15T21:01:23.1"