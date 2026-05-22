(:*******************************************************:)
(: Test: K-SeqExprCast-183                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Casting a xs:duration with zeroed time components to xs:string. :)
(:*******************************************************:)
xs:string(xs:dayTimeDuration("-P5DT0H0M0.000S")) eq "-P5D"