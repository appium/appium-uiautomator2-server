(:*******************************************************:)
(: Test: K-SeqExprCast-642                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: The xs:duration constructor function must be passed exactly one argument, not two. :)
(:*******************************************************:)
xs:duration(
      "P1Y2M3DT10H30M"
    ,
                                                     
      "P1Y2M3DT10H30M"
    )