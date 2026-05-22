(:*******************************************************:)
(: Test: K-IRIToURIfunc-4                                :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: Invoke fn:normalize-space() on the return value of fn:iri-to-uri(). Implementations supporting the static typing feature may raise XPTY0004. :)
(:*******************************************************:)
normalize-space(iri-to-uri(("somestring", current-time())[1])) eq "somestring"