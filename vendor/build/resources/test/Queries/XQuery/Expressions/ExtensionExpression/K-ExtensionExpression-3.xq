(:*******************************************************:)
(: Test: K-ExtensionExpression-3                         :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: A simple pragma expression.                  :)
(:*******************************************************:)
declare namespace prefix = "http://example.com/NotRecognized";
(#prefix:pr content #) {1 eq 1}