(: Name: orderDecl-19:)
(: Description: Simple ordering mode test.  Mode set to "ordered" in the prolog, with two FLOWR expression, where only ones overrides the prolog.:)

declare ordering unordered;

(: insert-start :)
declare variable $input-context1 external;
(: insert-end :)

ordered
 { for $x in $input-context1/works//day
    return $x
}

for $y in $input-context1/works//day
    return $y
