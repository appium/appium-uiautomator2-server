(:*******************************************************:)
(: Test: K-SeqExprCast-306                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Ensure casting xs:gMonthDay to xs:string, with timezone '-00:00' is properly handled. :)
(:*******************************************************:)
xs:string(xs:gMonthDay("--01-01-00:00")) eq "--01-01Z"