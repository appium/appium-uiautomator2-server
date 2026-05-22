(:*******************************************************:)
(: Test: K2-LetExprWithout-1                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-08-04T17:13:26Z                            :)
(: Purpose: A test whose essence is: `deep-equal((<b/>, <b/>, <b/>, <b/>), (for $v1 in (1, 2, 3, 4) let $v2 := <b/> return ($v2))/.)`. :)
(:*******************************************************:)
deep-equal((<b/>, <b/>, <b/>, <b/>),
           (for $v1 in (1, 2, 3, 4)
            let $v2 := <b/>
            return ($v2))/.)