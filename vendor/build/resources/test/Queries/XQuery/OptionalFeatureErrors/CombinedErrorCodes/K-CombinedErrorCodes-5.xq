(:*******************************************************:)
(: Test: K-CombinedErrorCodes-5                          :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:36+02:00                       :)
(: Purpose: Schema import binding to a namespace, and has three location hints. :)
(:*******************************************************:)
import(::)schema(::)namespace(::)prefix(::)=(::)"http://example.com/NSNOTRECOGNIZED"(::)
		at(::)"http://example.com/DOESNOTEXIST",(::)"http://example.com/2DOESNOTEXIST",
		"http://example.com/3DOESNOTEXIST"; 1 eq 1
	