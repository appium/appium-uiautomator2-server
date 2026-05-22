(:*******************************************************:)
(: Test: K-QuantExprWith-21                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Every-quantification with type-declaration. An implementation supporting the static typing feature may raise XPTY0004. :)
(:*******************************************************:)
every $a as empty-sequence() in (), $b as xs:integer in $a satisfies $b