(:*******************************************************:)
(: Test: K-SeqExprCast-257                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Ensure casting xs:gDay to xs:string, with timezone 'Z' is properly handled. :)
(:*******************************************************:)
xs:string(xs:gDay("---01Z")) eq "---01Z"