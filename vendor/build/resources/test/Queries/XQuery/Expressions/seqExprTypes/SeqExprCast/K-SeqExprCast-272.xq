(:*******************************************************:)
(: Test: K-SeqExprCast-272                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Ensure casting xs:gMonth to xs:string, with timezone 'Z' is properly handled. :)
(:*******************************************************:)
xs:string(xs:gMonth("--01Z")) eq "--01Z"