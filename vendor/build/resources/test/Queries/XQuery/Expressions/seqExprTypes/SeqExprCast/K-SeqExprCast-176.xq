(:*******************************************************:)
(: Test: K-SeqExprCast-176                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Ensure that when casting xs:dayTimeDuration to xs:string, that empty fields are properly serialized. :)
(:*******************************************************:)
xs:string(xs:dayTimeDuration("P0DT00H00M00.000S")) eq "PT0S"