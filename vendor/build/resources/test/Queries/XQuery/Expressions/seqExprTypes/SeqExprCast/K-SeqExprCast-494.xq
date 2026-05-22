(:*******************************************************:)
(: Test: K-SeqExprCast-494                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Cast a simple xs:untypedAtomic value to "3.3e3" . :)
(:*******************************************************:)
xs:double(xs:untypedAtomic(
      "3.3e3"
    )) eq xs:double("3.3e3")