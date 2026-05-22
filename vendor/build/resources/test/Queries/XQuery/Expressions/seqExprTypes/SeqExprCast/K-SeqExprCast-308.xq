(:*******************************************************:)
(: Test: K-SeqExprCast-308                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Ensure casting xs:gMonthDay to xs:string, with timezone 'Z' is properly handled. :)
(:*******************************************************:)
xs:string(xs:gMonthDay("--01-01Z")) eq "--01-01Z"