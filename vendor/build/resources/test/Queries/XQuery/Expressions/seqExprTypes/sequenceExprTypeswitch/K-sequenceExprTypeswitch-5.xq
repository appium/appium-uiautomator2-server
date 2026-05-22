(:*******************************************************:)
(: Test: K-sequenceExprTypeswitch-5                      :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: A typeswitch scenario which in some implementations trigger certain optimization code paths. :)
(:*******************************************************:)

		(typeswitch(((1, current-time())[1]))
			case element() return -1
			case xs:integer return 1
			default return -2)
		eq 1
	