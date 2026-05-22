(:*******************************************************:)
(: Test: K-SeqExprCast-698                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Cast a simple xs:untypedAtomic value to "P1Y12M" . :)
(:*******************************************************:)
xs:yearMonthDuration(xs:untypedAtomic(
      "P1Y12M"
    )) eq xs:yearMonthDuration("P1Y12M")