(:*******************************************************:)
(: Test: K-GenCompGT-19                                  :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: >= combined with count().                    :)
(:*******************************************************:)
count((1, 2, 3, timezone-from-time(current-time()), 4)) >= 0