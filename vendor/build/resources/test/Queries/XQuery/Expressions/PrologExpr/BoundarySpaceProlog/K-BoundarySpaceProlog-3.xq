(:*******************************************************:)
(: Test: K-BoundarySpaceProlog-3                         :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: Two 'declare boundary-space' declarations are invalid. :)
(:*******************************************************:)

	(::)declare(::)boundary-space(::)strip(::);
	(::)declare(::)boundary-space(::)preserve(::); 1 eq 1
	