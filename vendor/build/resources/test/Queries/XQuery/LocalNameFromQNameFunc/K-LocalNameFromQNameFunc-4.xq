(:*******************************************************:)
(: Test: K-LocalNameFromQNameFunc-4                      :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: A test whose essence is: `local-name-from-QName( QName("example.com/", "pre:lname")) eq "lname"`. :)
(:*******************************************************:)
local-name-from-QName(
			QName("example.com/", "pre:lname")) eq "lname"