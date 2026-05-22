(:*******************************************************:)
(: Test: K-SeqDistinctValuesFunc-9                       :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: A test whose essence is: `count(distinct-values((1, 2.0, 3, 2))) eq 3`. :)
(:*******************************************************:)
count(distinct-values((1, 2.0, 3, 2))) eq 3