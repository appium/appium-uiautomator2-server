(:*******************************************************:)
(: Test: K-TimezoneFromDateTimeFunc-5                    :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: A test whose essence is: `timezone-from-dateTime(xs:dateTime("2004-10-12T23:43:12Z")) eq xs:dayTimeDuration("PT0S")`. :)
(:*******************************************************:)
timezone-from-dateTime(xs:dateTime("2004-10-12T23:43:12Z")) eq
			xs:dayTimeDuration("PT0S")