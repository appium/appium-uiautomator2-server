(:*******************************************************:)
(: Test: K-CodepointToStringFunc-23                      :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: A test whose essence is: `codepoints-to-string(65533) eq "&#xFFFD;"`. :)
(:*******************************************************:)
codepoints-to-string(65533) eq "&#xFFFD;"