(: Name: ForExprType023 :)
(: Description: FLWOR expressions with type declaration (attribute type). Simple attribute name test (no type) :)

(: insert-start :)
declare default element namespace "http://typedecl";

declare variable $input-context external;
(: insert-end :)

for $test as attribute(integer) in ( $input-context/root/InterleaveType2/@integer )
return data( $test )
