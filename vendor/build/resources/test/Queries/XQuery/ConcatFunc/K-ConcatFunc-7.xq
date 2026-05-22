(:*******************************************************:)
(: Test: K-ConcatFunc-7                                  :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: A test whose essence is: `concat('a', 'b', 'c', (), 'd', 'e', 'f', 'g', 'h', ' ', 'i', 'j', 'k l') eq "abcdefgh ijk l"`. :)
(:*******************************************************:)
concat('a', 'b', 'c', (), 'd', 'e', 'f', 'g', 'h', ' ', 'i', 'j', 'k l')
					eq "abcdefgh ijk l"