(:*******************************************************:)
(: Test: K-SeqExprCast-646                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Cast a simple xs:untypedAtomic value to "P1Y2M3DT10H30M" . :)
(:*******************************************************:)
xs:duration(xs:untypedAtomic(
      "P1Y2M3DT10H30M"
    )) eq xs:duration("P1Y2M3DT10H30M")