(:*******************************************************:)
(: Test: K-SeqExprCast-430                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: The xs:string constructor function must be passed exactly one argument, not two. :)
(:*******************************************************:)
xs:string(
      "an arbitrary string"
    ,
                                                     
      "an arbitrary string"
    )