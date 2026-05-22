(:*******************************************************:)
(: Test: K-SeqExprCast-1366                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: Casting from xs:hexBinary to xs:QName isn't allowed. :)
(:*******************************************************:)
xs:hexBinary("0FB7") cast as xs:QName