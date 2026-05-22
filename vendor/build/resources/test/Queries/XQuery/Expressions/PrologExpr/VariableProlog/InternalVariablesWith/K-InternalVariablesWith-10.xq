(:*******************************************************:)
(: Test: K-InternalVariablesWith-10                      :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: A namespace declaration must appear before a variable declaration, and its prefix is not inscope for the variable declaration. :)
(:*******************************************************:)
declare variable $prefix:var1 := 2;
declare namespace prefix = "http://example.com/myNamespace";
true()