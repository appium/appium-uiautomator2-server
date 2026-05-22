(:*******************************************************:)
(: Test: K-SeqExprCast-1111                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: Verify with 'instance of' that the xs:gDay constructor function produces values of the correct type. The subsequence() function makes it more difficult for optimizers to take short cuts based on static type information. :)
(:*******************************************************:)

        subsequence(("dummy", 1.1, xs:gDay("---03")), 3, 1) instance of xs:gDay