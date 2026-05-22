(:*******************************************************:)
(: Test: K-FunctionProlog-63                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: A function call that could be a call to a hypothetical user function(#2). :)
(:*******************************************************:)

declare namespace my = "http://example.com/ANamespace";
my:function(1)
