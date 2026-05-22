(:*******************************************************:)
(: Test: K-SeqExprCast-802                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Cast a simple xs:untypedAtomic value to "2002-10-10T12:00:00-05:00" . :)
(:*******************************************************:)
xs:dateTime(xs:untypedAtomic(
      "2002-10-10T12:00:00-05:00"
    )) eq xs:dateTime("2002-10-10T12:00:00-05:00")