(:*******************************************************:)
(: Test: K-SeqExprCast-1058                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: The xs:gMonthDay constructor function must be passed exactly one argument, not two. :)
(:*******************************************************:)
xs:gMonthDay(
      "--11-13"
    ,
                                                     
      "--11-13"
    )