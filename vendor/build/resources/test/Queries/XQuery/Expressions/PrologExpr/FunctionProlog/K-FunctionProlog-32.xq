(:*******************************************************:)
(: Test: K-FunctionProlog-32                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: One cannot declare a user function as xs:gYear. :)
(:*******************************************************:)

declare function xs:gYear($arg as xs:anyAtomicType?) as xs:gYear?
{
	xs:gYear($arg)
};
1
