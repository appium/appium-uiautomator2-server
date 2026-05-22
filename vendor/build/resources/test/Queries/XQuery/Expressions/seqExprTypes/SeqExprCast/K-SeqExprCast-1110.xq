(:*******************************************************:)
(: Test: K-SeqExprCast-1110                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: The xs:gDay constructor function must be passed exactly one argument, not two. :)
(:*******************************************************:)
xs:gDay(
      "---03"
    ,
                                                     
      "---03"
    )