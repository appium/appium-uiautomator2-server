(:*******************************************************:)
(: Test: K-SeqExprCast-447                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Applying fn:boolean() to a value of type xs:float that has the lexical value ' "3.4e5" ' should result in the boolean value true. :)
(:*******************************************************:)

          boolean(xs:float("3.4e5"))