(:*******************************************************:)
(: Test: K-CombinedErrorCodes-7                          :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:36+02:00                       :)
(: Purpose: ':=' cannot be used to assing namespaces in 'import schema'. :)
(:*******************************************************:)
import schema namespace NCName := "http://example.com/Dummy"; 1