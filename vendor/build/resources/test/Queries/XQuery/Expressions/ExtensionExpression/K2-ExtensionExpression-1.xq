(:*******************************************************:)
(: Test: K2-ExtensionExpression-1                        :)
(: Written by: Frans Englich                             :)
(: Date: 2006-08-04T17:13:26Z                            :)
(: Purpose: An extension expression cannot be in an undeclared namespace. :)
(:*******************************************************:)

declare namespace xs = "";
(#xs:name content #) {1}