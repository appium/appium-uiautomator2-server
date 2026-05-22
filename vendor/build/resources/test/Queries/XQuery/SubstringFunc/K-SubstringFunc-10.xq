(:*******************************************************:)
(: Test: K-SubstringFunc-10                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: A test whose essence is: `substring("12345", 0 div 0E0, 3) eq ""`. :)
(:*******************************************************:)
substring("12345", 0 div 0E0, 3) eq ""