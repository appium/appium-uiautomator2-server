(:*******************************************************:)
(: Test: K-SeqExprCast-1266                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: The xs:base64Binary constructor function must be passed exactly one argument, not two. :)
(:*******************************************************:)
xs:base64Binary(
      "aaaa"
    ,
                                                     
      "aaaa"
    )