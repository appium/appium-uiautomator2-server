(:*******************************************************:)
(: Test: K-ModuleImport-2                                :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:41+02:00                       :)
(: Purpose: Module import with empty target namespace, and two location hints. :)
(:*******************************************************:)
import(::)module(::) "" at "http://example.com/", "http://example.com/2"; 1 eq 1