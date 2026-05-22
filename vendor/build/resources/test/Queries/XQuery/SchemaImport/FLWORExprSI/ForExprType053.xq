(: Name: ForExprType053 :)
(: Description: FLWOR expressions with type declaration. Non-trivial value type promotion: User defined simple type on attribute :)

(: insert-start :)
import schema default element namespace "http://typedecl";

declare variable $input-context external;
(: insert-end :)

for $test as Enumeration in data( $input-context/root/UserDefinedSimpleTypeAttribute/@att )
return $test