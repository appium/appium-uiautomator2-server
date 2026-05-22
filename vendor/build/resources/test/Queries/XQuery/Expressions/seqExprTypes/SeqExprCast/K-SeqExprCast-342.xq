(:*******************************************************:)
(: Test: K-SeqExprCast-342                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Ensure casting xs:dateTime to xs:string, with timezone '-00:00' is properly handled. :)
(:*******************************************************:)
xs:string(xs:dateTime("1999-12-01T23:59:12.999-00:00")) eq
		"1999-12-01T23:59:12.999Z"