(:*******************************************************:)
(: Test: K-ExtensionExpression-5                         :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: A pragma expression cannot be in the empty namespace even though a prefix is used. :)
(:*******************************************************:)
declare namespace prefix = "";
(# prefix:notRecognized #){1}