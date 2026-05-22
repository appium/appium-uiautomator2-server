(:*******************************************************:)
(: Test: K-SeqExprCast-270                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Ensure casting xs:gMonth to xs:string, with timezone '-00:00' is properly handled. :)
(:*******************************************************:)
xs:string(xs:gMonth("--01-00:00")) eq "--01Z"