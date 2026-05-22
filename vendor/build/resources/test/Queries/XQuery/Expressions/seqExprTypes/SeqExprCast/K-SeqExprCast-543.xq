(:*******************************************************:)
(: Test: K-SeqExprCast-543                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Applying fn:boolean() to a value of type xs:decimal that has the lexical value ' "10.01" ' should result in the boolean value true. :)
(:*******************************************************:)

          boolean(xs:decimal("10.01"))