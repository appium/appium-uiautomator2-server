(:*******************************************************:)
(: Test: K-CopyNamespacesProlog-5                        :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: A 'declare copy-namespaces' declaration specifying preserve and inherit in a wrong order . :)
(:*******************************************************:)

		declare copy-namespaces inherit, preserve; 
		1 eq 1