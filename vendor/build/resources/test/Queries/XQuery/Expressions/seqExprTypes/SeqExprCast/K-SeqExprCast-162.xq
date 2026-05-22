(:*******************************************************:)
(: Test: K-SeqExprCast-162                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: The canonical lexical representation for the xs:duration value P31D is 'P31D'. :)
(:*******************************************************:)
xs:string(xs:duration("P31D")) eq "P31D"