(:*******************************************************:)
(: Test: K-SeqExprCast-446                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Cast a simple xs:untypedAtomic value to "3.4e5" . :)
(:*******************************************************:)
xs:float(xs:untypedAtomic(
      "3.4e5"
    )) eq xs:float("3.4e5")