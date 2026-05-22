(:*******************************************************:)
(: Test: K-ValCompTypeChecking-33                        :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:36+02:00                       :)
(: Purpose: An expression involving the ne operator that trigger certain optimization paths in some implementations. :)
(:*******************************************************:)
count((0, current-time())) ne 0