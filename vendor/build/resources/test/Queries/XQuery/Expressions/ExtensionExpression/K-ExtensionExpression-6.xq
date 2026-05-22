(:*******************************************************:)
(: Test: K-ExtensionExpression-6                         :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: A pragma expression containing complex content. :)
(:*******************************************************:)
declare namespace prefix = "http://example.com/NotRecognized";
1 eq (#prefix:notRecognized ##cont## # # ( "# ) # )# )#ent #) {1}