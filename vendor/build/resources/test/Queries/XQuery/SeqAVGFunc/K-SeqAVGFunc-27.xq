(:*******************************************************:)
(: Test: K-SeqAVGFunc-27                                 :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: A test whose essence is: `avg((1, 2, xs:untypedAtomic("3"))) instance of xs:double`. :)
(:*******************************************************:)
avg((1, 2, xs:untypedAtomic("3"))) instance of xs:double