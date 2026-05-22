(:*******************************************************:)
(: Test: K-SeqExprCast-1010                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Cast a simple xs:untypedAtomic value to "1999" . :)
(:*******************************************************:)
xs:gYear(xs:untypedAtomic(
      "1999"
    )) eq xs:gYear("1999")