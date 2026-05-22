(:*******************************************************:)
(: Test: K-SeqExprCast-1006                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: The xs:gYear constructor function must be passed exactly one argument, not two. :)
(:*******************************************************:)
xs:gYear(
      "1999"
    ,
                                                     
      "1999"
    )