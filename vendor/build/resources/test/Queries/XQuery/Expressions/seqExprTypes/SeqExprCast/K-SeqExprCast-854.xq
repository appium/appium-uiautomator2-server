(:*******************************************************:)
(: Test: K-SeqExprCast-854                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Cast a simple xs:untypedAtomic value to "03:20:00-05:00" . :)
(:*******************************************************:)
xs:time(xs:untypedAtomic(
      "03:20:00-05:00"
    )) eq xs:time("03:20:00-05:00")