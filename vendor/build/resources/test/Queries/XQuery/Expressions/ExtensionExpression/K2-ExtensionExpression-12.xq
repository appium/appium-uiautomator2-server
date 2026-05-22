(:*******************************************************:)
(: Test: K2-ExtensionExpression-12                       :)
(: Written by: Frans Englich                             :)
(: Date: 2006-08-04T17:13:26Z                            :)
(: Purpose: No whitespace is required between pragma content and name if the content is empty. :)
(:*******************************************************:)
declare namespace ex = "http://example.com/";
(#ex:myExtensionExpression#) {true()}