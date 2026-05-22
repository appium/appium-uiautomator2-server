(:*******************************************************:)
(: Test: K-NormalizeSpaceFunc-8                          :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: A test whose essence is: `normalize-space(" 143 1239 fhjkls ") eq "143 1239 fhjkls"`. :)
(:*******************************************************:)
normalize-space("	143 
			1239 fhjkls	") eq "143 1239 fhjkls"