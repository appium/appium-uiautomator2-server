(:*******************************************************:)
(: Test: K-CombinedErrorCodes-1                          :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:36+02:00                       :)
(: Purpose: Schema import binding to no namespace, and no location hint. :)
(:*******************************************************:)
import(::)schema(::) "http://example.com/NSNOTRECOGNIZED"; 1 eq 1
	