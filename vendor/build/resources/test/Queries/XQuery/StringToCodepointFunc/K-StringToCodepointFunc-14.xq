(:*******************************************************:)
(: Test: K-StringToCodepointFunc-14                      :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: Combine string-to-codepoints() and a predicate. :)
(:*******************************************************:)
string-to-codepoints("Thérèse")[last()] eq 101