(:*******************************************************:)
(: Test: K-SeqExprCast-180                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Ensure that when casting xs:dayTimeDuration to xs:string, that preceding zeros are handled properly. :)
(:*******************************************************:)
xs:string(xs:dayTimeDuration("P0010DT0010H0010M0010S"))
		eq "P10DT10H10M10S"