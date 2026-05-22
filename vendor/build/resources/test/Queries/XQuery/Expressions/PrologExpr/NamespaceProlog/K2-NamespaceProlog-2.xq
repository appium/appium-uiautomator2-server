(:*******************************************************:)
(: Test: K2-NamespaceProlog-2                            :)
(: Written by: Frans Englich                             :)
(: Date: 2006-08-04T17:13:26Z                            :)
(: Purpose: A namespace declaration cannot occur twice for the same prefix, no matter what. :)
(:*******************************************************:)

declare namespace myPrefix = "";
declare namespace myPrefix = "http://example.com/";
1
          