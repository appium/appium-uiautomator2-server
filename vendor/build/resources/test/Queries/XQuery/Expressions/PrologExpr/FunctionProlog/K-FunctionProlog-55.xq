(:*******************************************************:)
(: Test: K-FunctionProlog-55                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: A more realistic case involving fn:error(), #3. :)
(:*******************************************************:)

declare namespace my = "http://example.com/MyNamespace/";
declare variable $my:error-qname := QName("http://example.com/MyErrorNS", "my:qName");

declare function my:error($choice, $msg as xs:string) as empty-sequence()
{
	if($choice) then
		error($my:error-qname, concat('No luck: ', $msg))
	else
		()
};
empty((my:error(false(), "msg"), my:error(false(), "The message")))
