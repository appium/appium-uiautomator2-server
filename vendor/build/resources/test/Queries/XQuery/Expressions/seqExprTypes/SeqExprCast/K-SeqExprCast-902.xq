(:*******************************************************:)
(: Test: K-SeqExprCast-902                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: The xs:date constructor function must be passed exactly one argument, not two. :)
(:*******************************************************:)
xs:date(
      "2004-10-13"
    ,
                                                     
      "2004-10-13"
    )