(:*******************************************************:)
(: Test: K-StringJoinFunc-5                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: A test whose essence is: `string-join(('Blow, ', 'blow, ', 'thou ', 'winter ', 'wind!'), '') eq "Blow, blow, thou winter wind!"`. :)
(:*******************************************************:)
string-join(('Blow, ', 'blow, ', 'thou ', 'winter ', 'wind!'), '')
			eq "Blow, blow, thou winter wind!"