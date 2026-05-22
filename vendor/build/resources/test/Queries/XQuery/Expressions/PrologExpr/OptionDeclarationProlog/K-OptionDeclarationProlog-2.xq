(:*******************************************************:)
(: Test: K-OptionDeclarationProlog-2                     :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: Five identical options appearing after each other. :)
(:*******************************************************:)
declare(::)option(::)local:opt(::)"option value"(::);
		 declare(::)option(::)local:opt(::)"option value"(::);
		 declare(::)option(::)local:opt(::)"option value"(::);
		 declare(::)option(::)local:opt(::)"option value"(::);
		 declare(::)option(::)local:opt(::)"option value"(::);(::)1(::)eq(::)1
	