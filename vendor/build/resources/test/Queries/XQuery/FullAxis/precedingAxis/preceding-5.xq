(: Name: preceding-5 :)
(: Description: Evaluation of the preceding axis that is part of an "node before" expression (return true). :)

(: insert-start :)
declare variable $input-context1 external;
(: insert-end :)

($input-context1/works[1]/employee[2]/preceding::employee) << ($input-context1/works[1]/employee[2])