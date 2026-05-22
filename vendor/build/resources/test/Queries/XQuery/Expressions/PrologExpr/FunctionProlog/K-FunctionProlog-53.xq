(:*******************************************************:)
(: Test: K-FunctionProlog-53                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: A more realistic case involving fn:error().  :)
(:*******************************************************:)

declare namespace my = "http://example.com/MyNamespace/";
declare variable $my:error-qname := QName("http://example.com/MyErrorNS", "my:qName");

declare function my:error($msg as xs:string) as empty-sequence()
{
	error($my:error-qname, concat('No luck: ', $msg))
};
my:error("The message")
