(:*******************************************************:)
(: Test: K-SeqExprCast-590                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: The xs:integer constructor function must be passed exactly one argument, not two. :)
(:*******************************************************:)
xs:integer(
      "6789"
    ,
                                                     
      "6789"
    )