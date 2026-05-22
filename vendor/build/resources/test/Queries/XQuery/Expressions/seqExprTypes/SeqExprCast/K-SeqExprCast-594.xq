(:*******************************************************:)
(: Test: K-SeqExprCast-594                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Cast a simple xs:untypedAtomic value to "6789" . :)
(:*******************************************************:)
xs:integer(xs:untypedAtomic(
      "6789"
    )) eq xs:integer("6789")