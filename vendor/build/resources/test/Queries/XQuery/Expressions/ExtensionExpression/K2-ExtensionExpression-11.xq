(:*******************************************************:)
(: Test: K2-ExtensionExpression-11                       :)
(: Written by: Frans Englich                             :)
(: Date: 2006-08-04T17:13:26Z                            :)
(: Purpose: A single whitespace must separate pragma name and content. :)
(:*******************************************************:)
declare namespace ex = "http://example.com/";
(#ex:myExtensionExpression :)#) {true()}