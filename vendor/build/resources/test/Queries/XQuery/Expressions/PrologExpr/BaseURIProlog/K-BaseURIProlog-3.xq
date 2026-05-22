(:*******************************************************:)
(: Test: K-BaseURIProlog-3                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: Two 'declare base-uri' expressions, where the URIs are equal. :)
(:*******************************************************:)

	(::)declare(::)base-uri(::)"http://example.com/declareBaseURITest"(::);
	(::)declare(::)base-uri(::)"http://example.com/declareBaseURITest"(::);
	1 eq 1
	