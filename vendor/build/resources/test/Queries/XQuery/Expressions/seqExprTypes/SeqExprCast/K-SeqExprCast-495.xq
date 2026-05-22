(:*******************************************************:)
(: Test: K-SeqExprCast-495                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Applying fn:boolean() to a value of type xs:double that has the lexical value ' "3.3e3" ' should result in the boolean value true. :)
(:*******************************************************:)

          boolean(xs:double("3.3e3"))