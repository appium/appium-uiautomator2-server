(:*******************************************************:)
(: Test: K-EncodeURIfunc-4                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: Combine fn:concat and fn:encode-for-uri.     :)
(:*******************************************************:)
concat("http://www.example.com/", encode-for-uri("~bébé")) 
			eq "http://www.example.com/~b%C3%A9b%C3%A9"