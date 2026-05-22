(:*******************************************************:)
(: Test: K-SeqExprCast-161                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: The canonical lexical representation for the xs:duration value P12M is 'P1Y'. :)
(:*******************************************************:)
xs:string(xs:duration("P12M")) eq "P1Y"