(:*******************************************************:)
(: Test: K-SeqExprCast-367                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Ensure casting xs:time to xs:string, with timezone '-00:00' is properly handled. :)
(:*******************************************************:)
xs:string(xs:time("23:59:12.999-00:00")) eq "23:59:12.999Z"