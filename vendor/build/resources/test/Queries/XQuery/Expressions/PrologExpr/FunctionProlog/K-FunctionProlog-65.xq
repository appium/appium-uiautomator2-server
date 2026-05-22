(:*******************************************************:)
(: Test: K-FunctionProlog-65                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: Since the user function has no declaredreturn type, it is inferred from the body, and therefore its callsite doesn't cause a type error(XPTY0004). :)
(:*******************************************************:)

declare function local:myFunction()
{
	fn:error()
};
QName("http://example.com/ANamespace", local:myFunction())
