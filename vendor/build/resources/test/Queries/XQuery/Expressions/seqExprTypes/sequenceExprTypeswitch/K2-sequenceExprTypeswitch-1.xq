(:*******************************************************:)
(: Test: K2-sequenceExprTypeswitch-1                     :)
(: Written by: Frans Englich                             :)
(: Date: 2006-08-04T17:13:26Z                            :)
(: Purpose: Extract the EBV from the result of a typeswitch. :)
(:*******************************************************:)
boolean(typeswitch (current-time(), 1, 3e3, "foo")
          case node() return 0
          case xs:integer return 3
          case xs:anyAtomicType return true()
          default return -1)