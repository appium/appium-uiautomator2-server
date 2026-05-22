(:*******************************************************:)
(: Test: K-FunctionProlog-31                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: One cannot declare a user function as fn:count. :)
(:*******************************************************:)

declare function fn:count($var)
{
	fn:count($var)
};
1
