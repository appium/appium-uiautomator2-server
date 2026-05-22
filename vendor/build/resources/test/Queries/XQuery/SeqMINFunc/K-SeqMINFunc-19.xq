(:*******************************************************:)
(: Test: K-SeqMINFunc-19                                 :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:41+02:00                       :)
(: Purpose: A test whose essence is: `min((xs:float("NaN"), 1, 1, 2, xs:double("NaN"))) instance of xs:double`. :)
(:*******************************************************:)
min((xs:float("NaN"), 1, 1, 2, xs:double("NaN"))) instance of xs:double