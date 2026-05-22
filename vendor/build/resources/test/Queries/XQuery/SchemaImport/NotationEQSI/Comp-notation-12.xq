(: Name: Comp-notation-12 :)
(: Description: Evaluation of Notation Comparison operator (ne) and used expression as argument to fn:boolean function. :)

(: insert-start :)
import schema namespace myns="http://www.example.com/notation";
declare variable $input-context external;
(: insert-end :)

fn:boolean($input-context//*:NOTATION1 ne $input-context//*:NOTATION2)