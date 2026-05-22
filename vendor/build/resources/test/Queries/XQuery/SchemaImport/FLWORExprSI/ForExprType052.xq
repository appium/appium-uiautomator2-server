(: Name: ForExprType052 :)
(: Description: FLWOR expressions with type declaration. Non-trivial value type promotion: User defined simple type :)

(: insert-start :)
import schema default element namespace "http://typedecl";

declare variable $input-context external;
(: insert-end :)

for $test as Enumeration in data( $input-context/root/UserDefinedSimpleType )
return $test