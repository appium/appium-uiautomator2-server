(: Name: Comp-notation-6 :)
(: Description: notation comparison using "ne" :)

(: insert-start :)
import schema namespace myns="http://www.example.com/notation";
declare variable $input-context external;
(: insert-end :)

$input-context//*:NOTATION1 ne $input-context//*:NOTATION4
