(: Name: copynamespace-4 :)
(: Written by: Carmelo Montanez :)
(: Description: Evaluates copy namespace declaration with value set to "preserve inherit".:)

declare namespace foo = "http://example.org";
declare copy-namespaces preserve,inherit;

(: insert-start :)
declare variable $input-context1 external;
(: insert-end :)

let $existingElement := <existingElement xmlns="www.existingnamespace.com">{"Existing Content"}</existingElement>
let $new := <foo:newElement xmlns = "www.mynamespace.com">{$existingElement}</foo:newElement>
for $var in (in-scope-prefixes($new//child::*))
order by $var ascending
return $var