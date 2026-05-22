(:*******************************************************:)
(: Test: K-SeqExprCast-746                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: The xs:dayTimeDuration constructor function must be passed exactly one argument, not two. :)
(:*******************************************************:)
xs:dayTimeDuration(
      "P3DT2H"
    ,
                                                     
      "P3DT2H"
    )