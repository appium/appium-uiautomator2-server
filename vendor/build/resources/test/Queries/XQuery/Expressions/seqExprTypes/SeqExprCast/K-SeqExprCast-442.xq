(:*******************************************************:)
(: Test: K-SeqExprCast-442                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: The xs:float constructor function must be passed exactly one argument, not two. :)
(:*******************************************************:)
xs:float(
      "3.4e5"
    ,
                                                     
      "3.4e5"
    )