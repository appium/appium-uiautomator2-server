(:*******************************************************:)
(: Test: K2-ExtensionExpression-3                        :)
(: Written by: Frans Englich                             :)
(: Date: 2006-08-04T17:13:26Z                            :)
(: Purpose: Whitespace is allowed but not required if there is no pragma content. :)
(:*******************************************************:)
declare namespace ex = "http://example.com/";
(#ex:myExtensionExpression           #) {true()}