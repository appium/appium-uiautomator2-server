(:*******************************************************:)
(: Test: K-SeqExprCast-155                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Ensure that when casting xs:duration to xs:string, preceding zeros are handled properly. :)
(:*******************************************************:)
xs:string(xs:duration("P0010Y0010M0010DT0010H0010M0010S"))
		eq "P10Y10M10DT10H10M10S"