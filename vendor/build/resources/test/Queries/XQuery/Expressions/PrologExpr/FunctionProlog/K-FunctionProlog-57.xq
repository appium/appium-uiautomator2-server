(:*******************************************************:)
(: Test: K-FunctionProlog-57                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: The type 'none()' isn't available to users.  :)
(:*******************************************************:)

declare function local:error() as none()
{
	1
};
local:error()
