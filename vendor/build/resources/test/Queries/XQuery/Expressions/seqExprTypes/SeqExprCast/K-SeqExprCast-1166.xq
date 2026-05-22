(:*******************************************************:)
(: Test: K-SeqExprCast-1166                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: Cast a simple xs:untypedAtomic value to "--11" . :)
(:*******************************************************:)
xs:gMonth(xs:untypedAtomic(
      "--11"
    )) eq xs:gMonth("--11")