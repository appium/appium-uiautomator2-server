(:*******************************************************:)
(: Test: K-SeqExprCast-157                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Ensure that when casting xs:duration to xs:string, that empty fields are properly serialized. :)
(:*******************************************************:)
xs:string(xs:duration("P0Y0M0DT00H00M00.000S")) eq "PT0S"