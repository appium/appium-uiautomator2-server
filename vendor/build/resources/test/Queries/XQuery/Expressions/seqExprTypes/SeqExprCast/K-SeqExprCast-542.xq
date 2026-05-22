(:*******************************************************:)
(: Test: K-SeqExprCast-542                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Cast a simple xs:untypedAtomic value to "10.01" . :)
(:*******************************************************:)
xs:decimal(xs:untypedAtomic(
      "10.01"
    )) eq xs:decimal("10.01")