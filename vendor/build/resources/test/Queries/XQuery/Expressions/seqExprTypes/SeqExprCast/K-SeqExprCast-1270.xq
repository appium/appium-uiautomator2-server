(:*******************************************************:)
(: Test: K-SeqExprCast-1270                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: Cast a simple xs:untypedAtomic value to "aaaa" . :)
(:*******************************************************:)
xs:base64Binary(xs:untypedAtomic(
      "aaaa"
    )) eq xs:base64Binary("aaaa")