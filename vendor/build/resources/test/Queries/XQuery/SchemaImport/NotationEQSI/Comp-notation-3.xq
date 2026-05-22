(: Name: Comp-notation-3 :)
(: Written by: Andreas Behm :)
(: Description: notation comparison :)

(: insert-start :)
import schema namespace myns="http://www.example.com/notation";
declare variable $input-context external;
(: insert-end :)

$input-context//*:NOTATION1 eq $input-context//*:NOTATION4
