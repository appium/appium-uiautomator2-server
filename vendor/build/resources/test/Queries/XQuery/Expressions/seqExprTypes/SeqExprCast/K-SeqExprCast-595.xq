(:*******************************************************:)
(: Test: K-SeqExprCast-595                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Applying fn:boolean() to a value of type xs:integer that has the lexical value ' "6789" ' should result in the boolean value true. :)
(:*******************************************************:)

          boolean(xs:integer("6789"))