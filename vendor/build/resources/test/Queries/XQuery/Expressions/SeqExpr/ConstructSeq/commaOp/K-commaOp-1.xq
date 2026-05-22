(:*******************************************************:)
(: Test: K-commaOp-1                                     :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:36+02:00                       :)
(: Purpose: A heavily nested sequence of expressions with the comma operator. On some implementations this triggers certain optimization paths. :)
(:*******************************************************:)
deep-equal(((1, (2, (3, 4, (5, 6)), 7), 8, (9, 10), 11)),
					      (1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11))