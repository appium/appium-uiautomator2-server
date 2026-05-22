(:*******************************************************:)
(: Test: K-SeqOneOrMoreFunc-6                            :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: A test whose essence is: `count(one-or-more( (1, 2, 3, "four") )) eq 4`. :)
(:*******************************************************:)
count(one-or-more( (1, 2, 3, "four") )) eq 4