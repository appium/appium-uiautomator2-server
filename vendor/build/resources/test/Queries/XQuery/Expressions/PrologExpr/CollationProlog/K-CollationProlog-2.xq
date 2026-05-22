(:*******************************************************:)
(: Test: K-CollationProlog-2                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: A 'declare default collation' that uses a relative URI combined with setting the base-uri, to specify an invalid collation. :)
(:*******************************************************:)

	declare base-uri "http://example.com/";
	declare default collation "collation/codepoint/DOESNOTEXIT/Testing";
	1
	