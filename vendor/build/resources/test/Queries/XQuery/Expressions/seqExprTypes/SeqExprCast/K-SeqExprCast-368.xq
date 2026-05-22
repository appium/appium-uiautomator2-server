(:*******************************************************:)
(: Test: K-SeqExprCast-368                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Ensure casting xs:time to xs:string, with timezone '+00:00' is properly handled. :)
(:*******************************************************:)
xs:string(xs:time("23:59:12.432+00:00")) eq "23:59:12.432Z"