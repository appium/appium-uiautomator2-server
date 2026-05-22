(:*******************************************************:)
(: Test: K2-DirectConElemNamespace-1                     :)
(: Written by: Frans Englich                             :)
(: Date: 2006-08-04T17:13:26Z                            :)
(: Purpose: A namespace declaration inside a direct element constructor is not in-scope for the next operand of the comma operator. :)
(:*******************************************************:)
<name xmlns:ns="http://example.com/NS"/>, ns:nametest