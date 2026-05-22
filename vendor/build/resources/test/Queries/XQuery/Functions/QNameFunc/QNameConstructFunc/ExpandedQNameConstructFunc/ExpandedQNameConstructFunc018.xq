(: Name: ExpandedQNameConstructFunc018 :)
(: Description: Test function fn:QName. Select namespace-URI part from source document :)

(: insert-start :)
declare variable $input-context external;
(: insert-end :)

element {fn:QName( string(($input-context//FolderName)[2]), "people" )}{ "test" }
