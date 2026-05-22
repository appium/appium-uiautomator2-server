(: Name: Constr-cont-nodeid-5 :)
(: Written by: Andreas Behm :)
(: Description: Copied text node has new node identity :)

for $x in <a>text</a>,
    $y in <elem>{$x/text()}</elem>
return $y/text() is $x/text()
