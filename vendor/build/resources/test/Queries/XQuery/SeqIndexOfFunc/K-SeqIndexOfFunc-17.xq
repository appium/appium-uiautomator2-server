(:*******************************************************:)
(: Test: K-SeqIndexOfFunc-17                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: A test whose essence is: `index-of(xs:untypedAtomic("example.com/"), xs:anyURI("example.com/")) eq 1`. :)
(:*******************************************************:)
index-of(xs:untypedAtomic("example.com/"), xs:anyURI("example.com/")) eq 1