(:*******************************************************:)
(: Test: K-YearsFromDurationFunc-7                       :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: Simple test invoking years-from-duration() on an negative xs:duration. :)
(:*******************************************************:)
years-from-duration(xs:duration("-P3Y4M4DT1H23M2.34S")) eq -3