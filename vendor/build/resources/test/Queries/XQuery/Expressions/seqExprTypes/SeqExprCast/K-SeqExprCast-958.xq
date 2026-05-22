(:*******************************************************:)
(: Test: K-SeqExprCast-958                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Cast a simple xs:untypedAtomic value to "1999-11" . :)
(:*******************************************************:)
xs:gYearMonth(xs:untypedAtomic(
      "1999-11"
    )) eq xs:gYearMonth("1999-11")