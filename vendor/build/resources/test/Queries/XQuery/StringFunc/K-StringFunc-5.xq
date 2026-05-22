(:*******************************************************:)
(: Test: K-StringFunc-5                                  :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: Apply fn:string() on xs:string.              :)
(:*******************************************************:)
string-length(string(xs:string(current-time()))) gt 2