(:*******************************************************:)
(: Test: K2-FunctionProlog-6                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-08-04T17:13:26Z                            :)
(: Purpose: A function requiring xs:integer as return value, but is passed xs:decimal. :)
(:*******************************************************:)
declare function local:myFunction() as xs:integer
{
        1.0
};
local:myFunction()