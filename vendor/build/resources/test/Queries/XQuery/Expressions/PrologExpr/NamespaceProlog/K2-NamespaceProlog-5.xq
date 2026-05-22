(:*******************************************************:)
(: Test: K2-NamespaceProlog-5                            :)
(: Written by: Frans Englich                             :)
(: Date: 2006-08-04T17:13:26Z                            :)
(: Purpose: It is ok to undeclare a non-bound namespace. :)
(:*******************************************************:)

declare namespace thisPrefixIsNotBoundExampleCom = "";
true()
          