(:*******************************************************:)
(: Test: K-NamespaceURIFromQNameFunc-5                   :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: A test whose essence is: `namespace-uri-from-QName( QName("example.com/", "pre:lname")) instance of xs:anyURI`. :)
(:*******************************************************:)
namespace-uri-from-QName(
			QName("example.com/", "pre:lname")) instance of xs:anyURI