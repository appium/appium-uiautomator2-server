(:*******************************************************:)
(: Test: K2-sequenceExprTypeswitch-3                     :)
(: Written by: Frans Englich                             :)
(: Date: 2006-08-04T17:13:26Z                            :)
(: Purpose: Parenteses must be specified for the expression that's switched. :)
(:*******************************************************:)
typeswitch 1
case node() return 0
case xs:integer return 3
case xs:anyAtomicType return true()