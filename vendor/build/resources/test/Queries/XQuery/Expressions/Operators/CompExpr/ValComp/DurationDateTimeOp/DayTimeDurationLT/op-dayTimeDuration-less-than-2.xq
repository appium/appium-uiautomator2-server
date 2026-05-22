(:*******************************************************:)
(:Test: op-dayTimeDuration-less-than-2                 :)
(:Written By: Carmelo Montanez                           :)
(:Date: June 13, 2005                                    :)
(:Purpose: Evaluates The "dayTimeDuration-less-than" function       :)
(:As part of a conditional expression (le operator) :)
(:*******************************************************:)
if(xs:dayTimeDuration("P3DT55H") le xs:dayTimeDuration("P20Y17M"))
then
test passed