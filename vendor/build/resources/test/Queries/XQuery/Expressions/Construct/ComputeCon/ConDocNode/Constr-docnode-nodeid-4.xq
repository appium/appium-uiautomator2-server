(: Name: Constr-docnode-nodeid-4 :)
(: Written by: Andreas Behm :)
(: Description: Copied pi node has new node identity :)

for $x in <?pi content?>,
    $y in document {$x}
return $y/processing-instruction() is $x
