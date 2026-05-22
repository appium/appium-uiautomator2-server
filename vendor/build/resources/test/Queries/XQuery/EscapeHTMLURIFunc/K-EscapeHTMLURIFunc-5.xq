(:*******************************************************:)
(: Test: K-EscapeHTMLURIFunc-5                           :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: Combine fn:concat and fn:escape-html-uri.    :)
(:*******************************************************:)
escape-html-uri("javascript:if (navigator.browserLanguage == 'fr') window.open('http://www.example.com/~bébé');")
			eq "javascript:if (navigator.browserLanguage == 'fr') window.open('http://www.example.com/~b%C3%A9b%C3%A9');"