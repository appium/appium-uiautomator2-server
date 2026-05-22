(:*******************************************************:)
(: Test: K-BaseURIProlog-1                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: Test 'declare base-uri' with fn:static-base-uri(). :)
(:*******************************************************:)

	(::)declare(::)base-uri(::)"http://example.com/declareBaseURITest"(::);
		static-base-uri() eq 'http://example.com/declareBaseURITest'
	