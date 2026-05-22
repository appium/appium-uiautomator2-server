(:*******************************************************:)
(: Test: K-SeqAVGFunc-10                                 :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: A test whose essence is: `avg((xs:float(1), xs:integer(0), xs:float(5))) eq 2.0`. :)
(:*******************************************************:)
avg((xs:float(1), xs:integer(0), xs:float(5))) eq 2.0