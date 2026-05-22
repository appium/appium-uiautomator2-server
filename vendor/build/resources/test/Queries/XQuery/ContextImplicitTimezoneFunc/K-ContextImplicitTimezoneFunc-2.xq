(:*******************************************************:)
(: Test: K-ContextImplicitTimezoneFunc-2                 :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:41+02:00                       :)
(: Purpose: Simple test of implicit-timezone().          :)
(:*******************************************************:)
seconds-from-duration(implicit-timezone()) le 0
				   or
				   seconds-from-duration(implicit-timezone()) gt 0