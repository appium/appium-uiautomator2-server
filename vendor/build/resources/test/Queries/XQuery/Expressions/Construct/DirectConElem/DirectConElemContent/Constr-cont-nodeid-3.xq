(: Name: Constr-cont-nodeid-3 :)
(: Written by: Andreas Behm :)
(: Description: Copied comment node has new node identity :)

for $x in <!--comment-->,
    $y in <elem>{$x}</elem>
return $y/comment() is $x
