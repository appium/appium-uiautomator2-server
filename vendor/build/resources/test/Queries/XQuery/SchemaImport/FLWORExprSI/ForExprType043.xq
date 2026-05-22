(: Name: ForExprType043 :)
(: Description: FLWOR expressions with type declaration (element type). Union type integer|decimal -> decimal :)

(: insert-start :)
import schema default element namespace "http://typedecl";

declare variable $input-context external;
(: insert-end :)

for $test as element(*,xs:decimal) in $input-context/root/UnionType/*
return $test