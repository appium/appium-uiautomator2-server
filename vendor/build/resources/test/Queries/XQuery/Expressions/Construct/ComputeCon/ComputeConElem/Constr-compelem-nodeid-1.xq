(: Name: Constr-compelem-nodeid-1 :)
(: Written by: Andreas Behm :)
(: Description: Copied element node has new node identity :)

for $x in <a/>,
    $y in element elem {$x}
return $y/a is $x
