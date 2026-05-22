(:*******************************************************:)
(: Test: K-SeqExprCast-1318                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: The xs:hexBinary constructor function must be passed exactly one argument, not two. :)
(:*******************************************************:)
xs:hexBinary(
      "0FB7"
    ,
                                                     
      "0FB7"
    )