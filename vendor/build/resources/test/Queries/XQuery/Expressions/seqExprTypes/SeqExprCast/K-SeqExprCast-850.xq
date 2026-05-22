(:*******************************************************:)
(: Test: K-SeqExprCast-850                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: The xs:time constructor function must be passed exactly one argument, not two. :)
(:*******************************************************:)
xs:time(
      "03:20:00-05:00"
    ,
                                                     
      "03:20:00-05:00"
    )