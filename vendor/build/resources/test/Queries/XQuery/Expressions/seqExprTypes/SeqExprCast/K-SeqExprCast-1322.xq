(:*******************************************************:)
(: Test: K-SeqExprCast-1322                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: Cast a simple xs:untypedAtomic value to "0FB7" . :)
(:*******************************************************:)
xs:hexBinary(xs:untypedAtomic(
      "0FB7"
    )) eq xs:hexBinary("0FB7")