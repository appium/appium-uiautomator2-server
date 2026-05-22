(:*******************************************************:)
(: Test: K-SeqExprCast-403                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Ensure casting xs:date to xs:string, with timezone '-00:00' is properly handled. :)
(:*******************************************************:)
xs:string(xs:date("1999-12-01-00:00")) eq "1999-12-01Z"