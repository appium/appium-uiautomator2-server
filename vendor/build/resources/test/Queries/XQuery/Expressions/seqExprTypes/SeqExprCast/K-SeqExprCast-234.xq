(:*******************************************************:)
(: Test: K-SeqExprCast-234                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Ensure casting xs:gYear to xs:string, with timezone '+00:00' is properly handled. :)
(:*******************************************************:)
xs:string(xs:gYear("1999+00:00")) eq "1999Z"