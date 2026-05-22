(: Name: ForExprType026 :)
(: Description: FLWOR expressions with type declaration (attribute type). Match user defined simple type on attribute :)


(: insert-start :)
import schema default element namespace "http://typedecl";

declare variable $input-context external;
(: insert-end :)

for $test as attribute( att,Enumeration ) in $input-context/root/UserDefinedSimpleTypeAttribute/@att
return data( $test )
