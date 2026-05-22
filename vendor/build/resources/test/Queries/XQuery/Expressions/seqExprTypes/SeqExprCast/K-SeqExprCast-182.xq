(:*******************************************************:)
(: Test: K-SeqExprCast-182                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Ensure that when casting xs:dayTimeDuration to xs:string, that empty components are handled properly. :)
(:*******************************************************:)
xs:string(xs:dayTimeDuration("-PT8H23M0S")) eq "-PT8H23M"