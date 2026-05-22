(:*******************************************************:)
(: Test: K-NormalizeSpaceFunc-7                          :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: A test whose essence is: `normalize-space("f o o ") eq "f o o"`. :)
(:*******************************************************:)
normalize-space("f	  o  	o ") eq "f o o"