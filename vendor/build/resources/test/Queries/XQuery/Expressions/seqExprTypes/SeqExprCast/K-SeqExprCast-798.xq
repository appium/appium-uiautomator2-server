(:*******************************************************:)
(: Test: K-SeqExprCast-798                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: The xs:dateTime constructor function must be passed exactly one argument, not two. :)
(:*******************************************************:)
xs:dateTime(
      "2002-10-10T12:00:00-05:00"
    ,
                                                     
      "2002-10-10T12:00:00-05:00"
    )