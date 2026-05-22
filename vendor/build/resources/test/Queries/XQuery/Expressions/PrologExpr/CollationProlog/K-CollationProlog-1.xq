(:*******************************************************:)
(: Test: K-CollationProlog-1                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: A 'declare default collation' that uses a relative URI combined with setting the base-uri, to specify the Unicode Codepoint collation. :)
(:*******************************************************:)

	declare base-uri "http://www.w3.org/2005/xpath-functions/";
	declare default collation "collation/codepoint";
	default-collation() eq "http://www.w3.org/2005/xpath-functions/collation/codepoint"
	