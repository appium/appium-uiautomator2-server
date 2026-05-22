(:*******************************************************:)
(: Test: K2-ExtensionExpression-5                        :)
(: Written by: Frans Englich                             :)
(: Date: 2006-08-04T17:13:26Z                            :)
(: Purpose: Content looking like comments are not recognized as so in pragma content. :)
(:*******************************************************:)
declare namespace ex = "http://example.com/";
(#ex:myExtensionExpression (:(:(:(:(: content #) {true()}