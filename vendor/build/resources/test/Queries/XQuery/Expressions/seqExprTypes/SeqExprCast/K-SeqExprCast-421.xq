(:*******************************************************:)
(: Test: K-SeqExprCast-421                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Applying fn:boolean() to a value of type xs:untypedAtomic that has the lexical value ' "an arbitrary string(untypedAtomic source)" ' should result in the boolean value true. :)
(:*******************************************************:)

          boolean(xs:untypedAtomic("an arbitrary string(untypedAtomic source)"))