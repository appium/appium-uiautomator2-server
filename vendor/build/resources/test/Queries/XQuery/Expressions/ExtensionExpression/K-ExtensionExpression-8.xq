(:*******************************************************:)
(: Test: K-ExtensionExpression-8                         :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: A pragma expression containing many comments. :)
(:*******************************************************:)
declare namespace prefix = "http://example.com/NotRecognized";
(::)1(::)eq(::)(#prefix:name ##cont## # # ( "# ) #
		)# )#ent #)(::){(::)1(::)}(::)