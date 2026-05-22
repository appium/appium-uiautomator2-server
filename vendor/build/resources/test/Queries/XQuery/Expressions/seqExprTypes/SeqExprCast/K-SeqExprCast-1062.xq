(:*******************************************************:)
(: Test: K-SeqExprCast-1062                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Cast a simple xs:untypedAtomic value to "--11-13" . :)
(:*******************************************************:)
xs:gMonthDay(xs:untypedAtomic(
      "--11-13"
    )) eq xs:gMonthDay("--11-13")