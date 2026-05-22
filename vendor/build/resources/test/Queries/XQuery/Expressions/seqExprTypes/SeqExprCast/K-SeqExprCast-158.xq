(:*******************************************************:)
(: Test: K-SeqExprCast-158                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Ensure that when casting xs:duration to xs:string, that empty components are handled properly. :)
(:*******************************************************:)
xs:string(xs:duration("-PT8H23M0S")) eq "-PT8H23M"