(:*******************************************************:)
(: Test: K2-SeqExprCastable-2                            :)
(: Written by: Frans Englich                             :)
(: Date: 2006-08-04T17:13:26Z                            :)
(: Purpose: Testing castability to xs:QName where the cardinality is wrong(#2). :)
(:*******************************************************:)
(QName("http://example.com/ANamespace", "ncname"),
 QName("http://example.com/ANamespace", "ncname2"),
 QName("http://example.com/ANamespace", "ncname3")) castable as xs:QName?