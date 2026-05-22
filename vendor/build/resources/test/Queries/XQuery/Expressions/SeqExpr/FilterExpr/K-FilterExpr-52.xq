(:*******************************************************:)
(: Test: K-FilterExpr-52                                 :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:36+02:00                       :)
(: Purpose: Filter a sequence with instance of and a second predicate(#2). :)
(:*******************************************************:)
((0, 1, 2, "a", "b", "c")[. instance of xs:integer][. treat as xs:integer eq 0] treat as xs:integer) eq 0