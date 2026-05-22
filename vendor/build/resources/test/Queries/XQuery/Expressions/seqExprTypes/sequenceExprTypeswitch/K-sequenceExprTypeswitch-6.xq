(:*******************************************************:)
(: Test: K-sequenceExprTypeswitch-6                      :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: A typeswitch scenario involving empty-sequence(). Both the 'xs:integer*' branch and the 'empty-sequnec()' branch are valid. :)
(:*******************************************************:)

		(typeswitch(())
			case xs:integer* return 1
			case empty-sequence() return 1
			default return -2)
		eq 1
	