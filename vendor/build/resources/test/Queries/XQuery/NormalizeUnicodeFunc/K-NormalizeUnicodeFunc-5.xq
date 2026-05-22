(:*******************************************************:)
(: Test: K-NormalizeUnicodeFunc-5                        :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: A test whose essence is: `normalize-unicode("foo", "NFC") eq "foo"`. :)
(:*******************************************************:)
normalize-unicode("foo", "NFC") eq "foo"