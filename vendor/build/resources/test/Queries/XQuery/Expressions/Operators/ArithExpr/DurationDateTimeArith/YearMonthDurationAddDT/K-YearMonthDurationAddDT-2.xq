(:*******************************************************:)
(: Test: K-YearMonthDurationAddDT-2                      :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:36+02:00                       :)
(: Purpose: Simple testing involving operator '+' between xs:yearMonthDuration and xs:dateTime. :)
(:*******************************************************:)
xs:yearMonthDuration("P3Y35M") +
		xs:dateTime("1999-07-19T08:23:01.765") eq xs:dateTime("2005-06-19T08:23:01.765")