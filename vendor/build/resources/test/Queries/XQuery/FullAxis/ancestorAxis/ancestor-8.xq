(: Name: ancestor-8 :)
(: Description: Evaluation of the ancestor axis that is part of an "node after" expression (returns true). :)

(: insert-start :)
declare variable $input-context1 external;
(: insert-end :)

($input-context1/works/employee[1]) >> ($input-context1/works/employee[1]/ancestor::works)