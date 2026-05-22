(:*******************************************************:)
(: Test: K-SeqAVGFunc-12                                 :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: A test whose essence is: `avg((xs:float(1), xs:integer(0), xs:untypedAtomic(3))) instance of xs:double`. :)
(:*******************************************************:)
avg((xs:float(1), xs:integer(0), xs:untypedAtomic(3))) instance of xs:double