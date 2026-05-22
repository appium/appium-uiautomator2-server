(:*******************************************************:)
(: Test: K-SeqExprCast-139                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: A test whose essence is: `xs:string(xs:base64Binary(xs:hexBinary("ffff34564321deac9876"))) eq "//80VkMh3qyYdg=="`. :)
(:*******************************************************:)
xs:string(xs:base64Binary(xs:hexBinary("ffff34564321deac9876")))
		eq "//80VkMh3qyYdg=="