(:*******************************************************:)
(: Test: K-SeqExprCast-194                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: The canonical lexical representation for the xs:dayTimeDuration value P3D is 'P3D'. :)
(:*******************************************************:)
xs:string(xs:dayTimeDuration("P3D")) eq "P3D"