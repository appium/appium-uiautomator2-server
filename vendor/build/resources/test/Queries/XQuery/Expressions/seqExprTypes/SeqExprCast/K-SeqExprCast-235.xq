(:*******************************************************:)
(: Test: K-SeqExprCast-235                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Ensure casting xs:gYear to xs:string, with timezone 'Z' is properly handled. :)
(:*******************************************************:)
xs:string(xs:gYear("1999Z")) eq "1999Z"