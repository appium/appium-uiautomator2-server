(:*******************************************************:)
(: Test: K-NameTest-11                                   :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:36+02:00                       :)
(: Purpose: Invalid string literals and nametests mixed, stressing tokenizer and parser code. :)
(:*******************************************************:)
normalize-unicode("f   oo") eq "f oo