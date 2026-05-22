(:*******************************************************:)
(: Test: K-StringJoinFunc-3                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: A test whose essence is: `string-join(('Now', 'is', 'the', 'time', '...'), ' ') eq "Now is the time ..."`. :)
(:*******************************************************:)
string-join(('Now', 'is', 'the', 'time', '...'), ' ')
			eq "Now is the time ..."