(:*******************************************************:)
(: Test: K-ExtensionExpression-7                         :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: A fallback expression must be present when no supported pragmas are specified. :)
(:*******************************************************:)
declare namespace prefix = "http://example.com/NotRecognized";
(#prefix:PragmaNotSupported content #) {}