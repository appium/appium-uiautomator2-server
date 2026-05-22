(:*******************************************************:)
(: Test: K-SeqExprCast-256                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Ensure casting xs:gDay to xs:string, with timezone '+00:00' is properly handled. :)
(:*******************************************************:)
xs:string(xs:gDay("---01+00:00")) eq "---01Z"