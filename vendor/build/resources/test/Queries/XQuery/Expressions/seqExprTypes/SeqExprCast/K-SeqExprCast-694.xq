(:*******************************************************:)
(: Test: K-SeqExprCast-694                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: The xs:yearMonthDuration constructor function must be passed exactly one argument, not two. :)
(:*******************************************************:)
xs:yearMonthDuration(
      "P1Y12M"
    ,
                                                     
      "P1Y12M"
    )