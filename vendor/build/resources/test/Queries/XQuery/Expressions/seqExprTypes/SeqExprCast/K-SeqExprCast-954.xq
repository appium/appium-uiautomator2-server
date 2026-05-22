(:*******************************************************:)
(: Test: K-SeqExprCast-954                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: The xs:gYearMonth constructor function must be passed exactly one argument, not two. :)
(:*******************************************************:)
xs:gYearMonth(
      "1999-11"
    ,
                                                     
      "1999-11"
    )