(:*******************************************************:)
(: Test: K-FunctionProlog-4                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: A function cannot be declared in the 'http://www.w3.org/2001/XMLSchema-instance' namespace. :)
(:*******************************************************:)

declare namespace my = "http://www.w3.org/2001/XMLSchema-instance";
declare function my:wrongNS()
{
	1
};
1
