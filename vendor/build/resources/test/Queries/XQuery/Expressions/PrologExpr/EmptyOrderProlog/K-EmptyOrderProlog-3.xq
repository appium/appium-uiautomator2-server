(:*******************************************************:)
(: Test: K-EmptyOrderProlog-3                            :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: Two 'declare default order empty' declarations are invalid. :)
(:*******************************************************:)

	(::)declare(::)default order empty(::)greatest(::);
	(::)declare(::)default order empty(::)least(::); 1 eq 1
	