(: insert-start :)
declare variable $input-context external;
(: insert-end :)

<result>
  {
    let $x := $input-context//xref[@xrefid = "top4"],
        $t := $input-context//title[. << $x]
    return $t[last()]
  }
</result> 