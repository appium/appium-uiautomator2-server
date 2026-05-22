(:*******************************************************:)
(: Test: K-NumericGT-22                                  :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:36+02:00                       :)
(: Purpose: ge combined with count().                    :)
(:*******************************************************:)
count((1, 2, 3, timezone-from-time(current-time()), 4)) ge 1