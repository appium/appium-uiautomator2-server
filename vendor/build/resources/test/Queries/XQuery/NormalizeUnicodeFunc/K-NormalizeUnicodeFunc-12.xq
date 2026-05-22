(:*******************************************************:)
(: Test: K-NormalizeUnicodeFunc-12                       :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: A test whose essence is: `normalize-unicode("f oo", "NFC") eq "f oo"`. :)
(:*******************************************************:)
normalize-unicode("f		oo", "NFC")
			eq "f		oo"