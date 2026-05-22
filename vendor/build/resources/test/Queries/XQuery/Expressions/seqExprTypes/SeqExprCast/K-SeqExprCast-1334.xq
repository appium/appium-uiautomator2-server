(:*******************************************************:)
(: Test: K-SeqExprCast-1334                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: Casting from xs:hexBinary to xs:integer isn't allowed. :)
(:*******************************************************:)
xs:hexBinary("0FB7") cast as xs:integer