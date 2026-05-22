(:*******************************************************:)
(: Test: K2-sequenceExprTypeswitch-2                     :)
(: Written by: Frans Englich                             :)
(: Date: 2006-08-04T17:13:26Z                            :)
(: Purpose: A default clause must be specified.          :)
(:*******************************************************:)
typeswitch(current-time())
case node() return 0
case xs:integer return 3
case xs:anyAtomicType return true()