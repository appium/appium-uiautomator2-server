(:*******************************************************:)
(: Test: K-SeqExprCast-1219                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: Applying fn:boolean() to a value of type xs:boolean that has the lexical value ' "true" ' should result in the boolean value true. :)
(:*******************************************************:)

          boolean(xs:boolean("true"))