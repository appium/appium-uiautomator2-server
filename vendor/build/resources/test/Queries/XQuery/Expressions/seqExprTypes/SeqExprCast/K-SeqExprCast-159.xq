(:*******************************************************:)
(: Test: K-SeqExprCast-159                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Casting a xs:duration with zeroed time components to xs:string. :)
(:*******************************************************:)
xs:string(xs:duration("-P2000Y11M5DT0H0M0.000S")) eq "-P2000Y11M5D"