(:*******************************************************:)
(: Test: K-sequenceExprTypeswitch-1                      :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: typeswitch test where the sequence types only differs in cardinality. :)
(:*******************************************************:)

		(typeswitch((1, 2))
			case xs:integer return -1
			case xs:integer+ return 1
			default return -2)
		eq 1
	