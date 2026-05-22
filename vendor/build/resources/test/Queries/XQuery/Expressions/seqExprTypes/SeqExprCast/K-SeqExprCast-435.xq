(:*******************************************************:)
(: Test: K-SeqExprCast-435                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Applying fn:boolean() to a value of type xs:string that has the lexical value ' "an arbitrary string" ' should result in the boolean value true. :)
(:*******************************************************:)

          boolean(xs:string("an arbitrary string"))