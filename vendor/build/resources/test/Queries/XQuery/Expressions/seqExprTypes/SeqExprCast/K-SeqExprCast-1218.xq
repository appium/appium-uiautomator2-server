(:*******************************************************:)
(: Test: K-SeqExprCast-1218                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: Cast a simple xs:untypedAtomic value to "true" . :)
(:*******************************************************:)
xs:boolean(xs:untypedAtomic(
      "true"
    )) eq xs:boolean("true")