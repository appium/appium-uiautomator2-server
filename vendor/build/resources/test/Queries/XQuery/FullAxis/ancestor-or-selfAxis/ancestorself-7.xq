(: Name: ancestorself-7 :)
(: Description: Evaluation of the ancestor-or-self axis that is part of an "node before" expression both operands are differents (return false). :)

(: insert-start :)
declare variable $input-context1 external;
(: insert-end :)

($input-context1/works/employee[1]/ancestor-or-self::works) << ($input-context1/works/employee[1])