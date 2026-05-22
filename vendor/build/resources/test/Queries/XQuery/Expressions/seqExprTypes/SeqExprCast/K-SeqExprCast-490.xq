(:*******************************************************:)
(: Test: K-SeqExprCast-490                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: The xs:double constructor function must be passed exactly one argument, not two. :)
(:*******************************************************:)
xs:double(
      "3.3e3"
    ,
                                                     
      "3.3e3"
    )