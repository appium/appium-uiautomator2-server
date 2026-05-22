(:*******************************************************:)
(: Test: K-SeqExprCast-160                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: The canonical lexical representation for the xs:duration value P365D is 'P365D'. :)
(:*******************************************************:)
xs:string(xs:duration("P365D")) eq "P365D"