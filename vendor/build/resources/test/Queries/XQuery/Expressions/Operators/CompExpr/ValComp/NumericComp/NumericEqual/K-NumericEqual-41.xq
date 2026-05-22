(:*******************************************************:)
(: Test: K-NumericEqual-41                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:36+02:00                       :)
(: Purpose: An expression involving the 'eq' operator that trigger certain optimization paths in some implementations. :)
(:*******************************************************:)
count(remove(remove((current-time(), 1), 1), 1)) eq 0