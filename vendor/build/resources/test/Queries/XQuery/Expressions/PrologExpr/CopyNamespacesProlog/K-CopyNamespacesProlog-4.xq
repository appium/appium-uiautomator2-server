(:*******************************************************:)
(: Test: K-CopyNamespacesProlog-4                        :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: A 'declare copy-namespaces' declaration specifying no-preserve and no-inherit in a wrong order . :)
(:*******************************************************:)

		declare copy-namespaces no-inherit, no-preserve; 
		1 eq 1