(:*******************************************************:)
(: Test: K-SeqDistinctValuesFunc-15                      :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: fn:distinct-values() applied on an argument of cardinality exactly-one. :)
(:*******************************************************:)
count(distinct-values(current-time())) eq 1