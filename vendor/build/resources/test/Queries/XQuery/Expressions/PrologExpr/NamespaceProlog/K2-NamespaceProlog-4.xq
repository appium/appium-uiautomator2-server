(:*******************************************************:)
(: Test: K2-NamespaceProlog-4                            :)
(: Written by: Frans Englich                             :)
(: Date: 2006-08-04T17:13:26Z                            :)
(: Purpose: When a pre-declared namespace prefix has been undeclared, it is not available. :)
(:*******************************************************:)
declare namespace xs = "";
xs:integer(1)