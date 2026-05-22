(:*******************************************************:)
(:Test: CastAs672                                        :)
(:Written By: Carmelo Montanez                           :)
(:Date: July 13, 2006                                    :)
(:Purpose: Evaluates casting a sequence of strings as an xs:ENTITY :)
(:*******************************************************:)

(: insert-start :)
declare variable $input-context external;
(: insert-end :)

let $var := ("a","b","c") cast as xs:ENTITY 
return $var instance of xs:ENTITY