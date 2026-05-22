(:*******************************************************:)
(: Test: K-SeqExprCast-165                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: The canonical form of the xs:duration value -PT0S is PT0S. :)
(:*******************************************************:)
xs:string(xs:duration("-PT0S")) eq "PT0S"