(:*******************************************************:)
(: Test: K2-FunctionProlog-7                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-08-04T17:13:26Z                            :)
(: Purpose: A declared return value invokes numeric promotion. :)
(:*******************************************************:)
declare function local:myFunction() as xs:float
{
        4.0
};
(current-time(), 1, 2, "a string", local:myFunction(), 4.0, xs:double("NaN"), current-date())[5]
instance of xs:float