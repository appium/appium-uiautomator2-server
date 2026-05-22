(:*******************************************************:)
(: Test: K-TimezoneFromDateFunc-5                        :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: A test whose essence is: `timezone-from-date(xs:date("2004-10-12Z")) eq xs:dayTimeDuration("PT0S")`. :)
(:*******************************************************:)
timezone-from-date(xs:date("2004-10-12Z")) eq
			xs:dayTimeDuration("PT0S")