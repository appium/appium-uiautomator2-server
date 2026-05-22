(:*******************************************************:)
(: Test: K-SeqDistinctValuesFunc-6                       :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: A test whose essence is: `deep-equal(distinct-values( ("1", 1, 2, 1, 1, 3, 1, 1, 3, xs:anyURI("example.com/"), xs:anyURI("example.com/"))), ("1", 1, 2, 3, xs:anyURI("example.com/")))`. :)
(:*******************************************************:)
deep-equal(distinct-values(
			("1", 1, 2, 1, 1, 3, 1, 1, 3, xs:anyURI("example.com/"), xs:anyURI("example.com/"))),
			("1", 1, 2, 3, xs:anyURI("example.com/")))