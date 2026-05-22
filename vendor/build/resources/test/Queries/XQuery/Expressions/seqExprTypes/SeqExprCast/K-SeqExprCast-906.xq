(:*******************************************************:)
(: Test: K-SeqExprCast-906                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Cast a simple xs:untypedAtomic value to "2004-10-13" . :)
(:*******************************************************:)
xs:date(xs:untypedAtomic(
      "2004-10-13"
    )) eq xs:date("2004-10-13")