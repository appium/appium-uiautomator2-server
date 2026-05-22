(:*******************************************************:)
(: Test: K-NumericAdd-64                                 :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:36+02:00                       :)
(: Purpose: Invoke operator '+' where one of the operands, using subsequence(), evaluates to an invalid cardinality. :)
(:*******************************************************:)
subsequence("a string", 1, 1) + 1