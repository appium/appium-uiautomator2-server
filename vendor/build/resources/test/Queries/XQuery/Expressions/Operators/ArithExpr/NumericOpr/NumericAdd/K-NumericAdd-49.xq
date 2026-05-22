(:*******************************************************:)
(: Test: K-NumericAdd-49                                 :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:36+02:00                       :)
(: Purpose: Complex combination of numeric arithmetics in order to stress operator precedence. :)
(:*******************************************************:)
1 + 2 * 4 + (1 + 2 + 3 * 4) eq 24