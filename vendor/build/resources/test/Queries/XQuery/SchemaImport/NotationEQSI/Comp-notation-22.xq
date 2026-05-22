(: Name: Comp-notation-22 :)
(: Description: Evaluation of expression of Notation Comparison operator (ne) used as argument to "fn:string" function. :)

(: insert-start :)
import schema namespace myns="http://www.example.com/notation";
declare variable $input-context external;
(: insert-end :)

fn:string($input-context//*:NOTATION1 ne $input-context//*:NOTATION2)