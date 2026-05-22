(:*******************************************************:)
(: Test: K2-TokenizeFunc-1                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-08-04T17:13:26Z                            :)
(: Purpose: fn:tokenize with a positional predicate.     :)
(:*******************************************************:)
fn:tokenize(("abracadabra", current-time())[1] treat as xs:string,
             "(ab)|(a)")[last()] eq ""