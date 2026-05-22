(:*******************************************************:)
(: Test: K-SeqExprCast-538                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: The xs:decimal constructor function must be passed exactly one argument, not two. :)
(:*******************************************************:)
xs:decimal(
      "10.01"
    ,
                                                     
      "10.01"
    )