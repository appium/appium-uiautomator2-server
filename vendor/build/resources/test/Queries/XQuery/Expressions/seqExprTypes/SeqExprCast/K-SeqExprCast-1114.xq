(:*******************************************************:)
(: Test: K-SeqExprCast-1114                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: Cast a simple xs:untypedAtomic value to "---03" . :)
(:*******************************************************:)
xs:gDay(xs:untypedAtomic(
      "---03"
    )) eq xs:gDay("---03")