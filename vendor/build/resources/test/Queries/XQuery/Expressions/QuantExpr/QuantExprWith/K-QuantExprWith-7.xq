(:*******************************************************:)
(: Test: K-QuantExprWith-7                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Every-quantification; the empty-sequence() cannot have an occurrence indicator. :)
(:*******************************************************:)
every $a as empty-sequence()? in (1, 2) satisfies $a