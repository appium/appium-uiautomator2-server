(:*******************************************************:)
(: Test: K-SeqExprCast-695                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Verify with 'instance of' that the xs:yearMonthDuration constructor function produces values of the correct type. The subsequence() function makes it more difficult for optimizers to take short cuts based on static type information. :)
(:*******************************************************:)

        subsequence(("dummy", 1.1, xs:yearMonthDuration("P1Y12M")), 3, 1) instance of xs:yearMonthDuration