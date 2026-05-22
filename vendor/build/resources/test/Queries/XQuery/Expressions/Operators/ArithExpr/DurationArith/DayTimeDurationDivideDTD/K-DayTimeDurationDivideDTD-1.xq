(:*******************************************************:)
(: Test: K-DayTimeDurationDivideDTD-1                    :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:36+02:00                       :)
(: Purpose: Simple test of dividing a xs:dayTimeDuration with xs:dayTimeDuration. :)
(:*******************************************************:)
(xs:dayTimeDuration("PT8M") div xs:dayTimeDuration("PT2M")) eq 4