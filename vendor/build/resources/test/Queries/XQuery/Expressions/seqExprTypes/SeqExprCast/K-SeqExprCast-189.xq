(:*******************************************************:)
(: Test: K-SeqExprCast-189                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: The canonical form of the xs:dayTimeDuration value -PT0S is PT0S. :)
(:*******************************************************:)
xs:string(xs:dayTimeDuration("-PT0S")) eq "PT0S"