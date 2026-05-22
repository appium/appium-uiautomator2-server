(:*******************************************************:)
(: Test: K-FunctionProlog-66                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: A function call that reminds of the range expression. :)
(:*******************************************************:)

declare default function namespace "http://www.w3.org/2005/xquery-local-functions";
declare function local:is() {1};
is() eq 1
