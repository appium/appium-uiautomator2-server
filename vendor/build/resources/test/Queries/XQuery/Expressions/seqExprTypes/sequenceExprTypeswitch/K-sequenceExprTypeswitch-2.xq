(:*******************************************************:)
(: Test: K-sequenceExprTypeswitch-2                      :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: A string literal is of type xs:string, even though it can be promoted to xs:anyURI. :)
(:*******************************************************:)

		(typeswitch("a string")
			case xs:anyURI return -1
			case xs:string return 1
			default return -2)
		eq 1
	