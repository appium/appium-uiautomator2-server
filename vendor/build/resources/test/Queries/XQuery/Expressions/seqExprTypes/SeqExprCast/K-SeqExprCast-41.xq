(:*******************************************************:)
(: Test: K-SeqExprCast-41                                :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: A test whose essence is: `" white space left alone" cast as xs:string eq xs:string(" white space left alone")`. :)
(:*******************************************************:)
"  white space	left alone" cast as xs:string eq 
					xs:string("  white space	left alone")