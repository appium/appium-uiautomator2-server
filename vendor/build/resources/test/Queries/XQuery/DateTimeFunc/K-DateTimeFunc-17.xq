(:*******************************************************:)
(: Test: K-DateTimeFunc-17                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: Invoke fn:dateTime() where none of its values has a timezone. :)
(:*******************************************************:)
dateTime(xs:date("2004-03-04"),
			    xs:time("08:05:23"))
				eq
				xs:dateTime("2004-03-04T08:05:23")