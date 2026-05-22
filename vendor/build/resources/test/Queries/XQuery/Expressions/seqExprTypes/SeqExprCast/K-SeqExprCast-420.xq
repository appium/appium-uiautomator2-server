(:*******************************************************:)
(: Test: K-SeqExprCast-420                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Cast a simple xs:untypedAtomic value to "an arbitrary string(untypedAtomic source)" . :)
(:*******************************************************:)
xs:untypedAtomic(xs:untypedAtomic(
      "an arbitrary string(untypedAtomic source)"
    )) eq xs:untypedAtomic("an arbitrary string(untypedAtomic source)")