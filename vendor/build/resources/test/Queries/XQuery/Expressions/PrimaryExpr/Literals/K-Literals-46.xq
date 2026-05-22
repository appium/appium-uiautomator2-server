(:*******************************************************:)
(: Test: K-Literals-46                                   :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:36+02:00                       :)
(: Purpose: Test containing all predefined character references and one hexa and decimal character reference. :)
(:*******************************************************:)
"&lt; &gt; &amp; &quot; &apos; &#x48; &#48;" eq
		"< > &amp; "" ' &#x48; &#48;"
	