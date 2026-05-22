(: Name: Comp-notation-13 :)
(: Description: Evaluation of Notation Comparison operator (eq) and used in boolean expression with "fn:true" and "and" . :)

(: insert-start :)
import schema namespace myns="http://www.example.com/notation";
declare variable $input-context external;
(: insert-end :)

($input-context//*:NOTATION1 eq $input-context//*:NOTATION2) and fn:true()