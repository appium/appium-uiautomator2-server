(:*******************************************************:)
(: Test: K-SeqExprCast-750                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Cast a simple xs:untypedAtomic value to "P3DT2H" . :)
(:*******************************************************:)
xs:dayTimeDuration(xs:untypedAtomic(
      "P3DT2H"
    )) eq xs:dayTimeDuration("P3DT2H")