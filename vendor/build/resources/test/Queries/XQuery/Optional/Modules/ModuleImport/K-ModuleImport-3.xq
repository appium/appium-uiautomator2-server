(:*******************************************************:)
(: Test: K-ModuleImport-3                                :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:41+02:00                       :)
(: Purpose: ':=' cannot be used to assing namespaces in 'import module'. :)
(:*******************************************************:)
import module namespace NCName := "http://example.com/Dummy"; 1