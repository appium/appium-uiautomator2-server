(: Name: internalvar-1 :)
(: Description: Evaluates an external variable named "$local:var" declared as "item()*" :)
(: and multiple embedded comments.                                                      :)

(: insert-start :)
declare(::)variable(::)$(::)local:var(::)as(::)item((:
                :))(::)*(::)external;
(: insert-end :)     
           
(::)1(::)eq(::)1