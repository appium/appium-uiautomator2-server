(:*******************************************************:)
(: Test: K-ForExprWithout-46                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Type check: $foo is of type xs:string, which cannot be added to xs:integer 1. :)
(:*******************************************************:)
for $foo in "foo" return 1 + $foo