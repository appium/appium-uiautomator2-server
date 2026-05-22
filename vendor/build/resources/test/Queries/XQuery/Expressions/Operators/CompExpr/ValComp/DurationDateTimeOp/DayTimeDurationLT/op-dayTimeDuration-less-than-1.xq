(:*******************************************************:)
(:Test: op-dayTimeDuration-less-than-1                   :)
(:Written By: Carmelo Montanez                           :)
(:Date: June 15, 2005                                    :)
(:Purpose: Evaluates The "dayTimeDuration-less-than" function       :)
(:using multiple lt operators.                           :)
(:*******************************************************:)

((xs:dayTimeDuration("P3DT55H") lt xs:dayTimeDuration("P3DT56H")) lt xs:dayTimeDuration("P5DT15H"))
