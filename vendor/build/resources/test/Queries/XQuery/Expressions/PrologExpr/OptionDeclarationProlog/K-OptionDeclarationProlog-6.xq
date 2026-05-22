(:*******************************************************:)
(: Test: K-OptionDeclarationProlog-6                     :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: A syntactically invalid option declaration.  :)
(:*******************************************************:)
declare option localpartmissing: 'option value'; 1 eq 1
	