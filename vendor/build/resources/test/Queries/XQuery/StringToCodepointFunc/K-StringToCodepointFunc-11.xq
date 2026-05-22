(:*******************************************************:)
(: Test: K-StringToCodepointFunc-11                      :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: A test whose essence is: `string-join(for $code in string-to-codepoints("example.com/") return string($code), "") eq "10112097109112108101469911110947"`. :)
(:*******************************************************:)
string-join(for $code in string-to-codepoints("example.com/")
								return string($code), "")
				       eq "10112097109112108101469911110947"