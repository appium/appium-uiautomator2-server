(:*******************************************************:)
(: Test: K-SeqExprCast-287                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Ensure casting xs:gYearMonth to xs:string, with timezone 'Z' is properly handled. :)
(:*******************************************************:)
xs:string(xs:gYearMonth("1999-01Z")) eq "1999-01Z"