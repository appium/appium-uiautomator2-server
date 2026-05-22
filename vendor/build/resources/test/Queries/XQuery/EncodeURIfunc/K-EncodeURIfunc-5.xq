(:*******************************************************:)
(: Test: K-EncodeURIfunc-5                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: Combine fn:concat and fn:encode-for-uri.     :)
(:*******************************************************:)
concat("http://www.example.com/", encode-for-uri("100% organic")) 
			eq "http://www.example.com/100%25%20organic"