(:*******************************************************:)
(: Test: K-SubstringFunc-13                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: A test whose essence is: `substring("12345", -42, 1 div 0E0) eq "12345"`. :)
(:*******************************************************:)
substring("12345", -42, 1 div 0E0) eq "12345"