(:*******************************************************:)
(: Test: K-NormalizeSpaceFunc-9                          :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: Invoke normalize-space on itself. Implementations supporting the static typing feature may raise XPTY0004. :)
(:*******************************************************:)
normalize-space(normalize-space(("foo", current-time())[1])) eq "foo"