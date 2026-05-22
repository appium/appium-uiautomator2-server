(: Name: Comp-notation-1 :)
(: Written by: Andreas Behm :)
(: Description: notation comparison :)

(: insert-start :)
import schema namespace myns="http://www.example.com/notation";
declare variable $input-context external;
(: insert-end :)

fn:not($input-context//*:NOTATION1 eq $input-context//*:NOTATION3)
