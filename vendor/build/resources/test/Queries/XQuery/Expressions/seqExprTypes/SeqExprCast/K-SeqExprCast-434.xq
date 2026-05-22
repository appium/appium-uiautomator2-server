(:*******************************************************:)
(: Test: K-SeqExprCast-434                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Cast a simple xs:untypedAtomic value to "an arbitrary string" . :)
(:*******************************************************:)
xs:string(xs:untypedAtomic(
      "an arbitrary string"
    )) eq xs:string("an arbitrary string")