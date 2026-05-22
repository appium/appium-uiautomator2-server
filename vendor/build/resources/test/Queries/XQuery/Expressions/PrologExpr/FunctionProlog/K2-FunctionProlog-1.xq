(:*******************************************************:)
(: Test: K2-FunctionProlog-1                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-08-04T17:13:26Z                            :)
(: Purpose: Have two function callsites as arguments to 'eq'. :)
(:*******************************************************:)
declare function local:myFunction($arg)
{
((if($arg eq 1)
then 1
else $arg - 1), current-time())[1] treat as xs:integer
};
local:myFunction(1) eq local:myFunction(2)