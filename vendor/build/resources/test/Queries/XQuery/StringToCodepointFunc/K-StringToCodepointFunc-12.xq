(:*******************************************************:)
(: Test: K-StringToCodepointFunc-12                      :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: Combine fn:deep-equal and string-to-codepoints(). :)
(:*******************************************************:)
deep-equal(string-to-codepoints("Thérèse"),
						  (84, 104, 233, 114, 232, 115, 101))