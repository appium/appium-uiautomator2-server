/********************************************************************************
 * Copyright (c) 2005, 2009 Andrea Bittau, University College London, and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Andrea Bittau - initial API and implementation from the PsychoPath XPath 2.0
 *     David Carver - bug 280987 - fixed literal issues for integer and decimal
 *     Jesper S Moller - bug 283214 - fix IF THEN ELSE parsing and update grammars
 *     Jesper S Moller - bug 286061   correct handling of quoted string 
 *     Jesper Moller - bug 297707 - Missing the empty-sequence() type
 *     Jesper Moller - bug 388504 - XPath scanner does not detect non-ASCII names
 *******************************************************************************/


package org.eclipse.wst.xml.xpath2.processor.internal;

import java_cup.runtime.*;

%%

%class XPathFlex
%unicode
%cup
%line
%column

Whitespace 	= [ \t\r\n]
StringLiteral 	= (\" ((\"\") | [^\"])* \") | (\' ((\'\') | [^\'])* \')
Digits		= [0-9]+
DoubleLiteral	= ((\. {Digits}) | ({Digits} (\. [0-9]*)?)) [eE] [\+\-]?  {Digits}
DecimalLiteral  = (\. {Digits}) | ({Digits} \. [0-9]*)

// NameStartChar	= (	[A-Z] | "_" | [a-z] | [#xC0-#xD6] | [#xD8-#xF6] | [#xF8-#x2FF] | [#x370-#x37D] | [#x37F-#x1FFF] | [#x200C-#x200D] | [#x2070-#x218F] | [#x2C00-#x2FEF] | [#x3001-#xD7FF] | [#xF900-#xFDCF] | [#xFDF0-#xFFFD] | [#x10000-#xEFFFF]
//NCNameChar	   ::=   	{NameStartChar} | "-" | "." | [0-9] | #xB7 | [#x0300-#x036F] | [#x203F-#x2040]

HighSurrogate = [\uD800-\uDBFF]
LowSurrogate = [\uDC00-\uDFFF]
Surrogate = ({HighSurrogate} {LowSurrogate})

//NCNameStartChar		= [A-Z] | "_" | [a-z] | [\u00C0-\u00D6] | [\u00D8-\u00F6] | [\u00F8-\u02FF] | [\u0370-\u037D] | [\u037F-\u1FFF] | [\u200C-\u200D] | [\u2070-\u218F] | [\u2C00-\u2FEF] | [\u3001-\uD7FF] | [\uF900-\uFDCF] | [\uFDF0-\uFFFD]
//NCNameChar      = {NCNameStartChar} | [0-9] | \. | \- 
//NCName		= ( ( {NameStartChar} | Surrogate ) ( {NCNameChar} | Surrogate )*

Letter		= [A-Z] | "_" | [a-z] | [\u00C0-\u00D6] | [\u00D8-\u00F6] | [\u00F8-\u02FF] | [\u0370-\u037D] | [\u037F-\u1FFF] | [\u200C-\u200D] | [\u2070-\u218F] | [\u2C00-\u2FEF] | [\u3001-\uD7FF] | [\uF900-\uFDCF] | [\uFDF0-\uFFFD] | {Surrogate}
NCNameChar      = {Letter} | [0-9] | \. | \- | "_"
NCName		= ( {Letter} | "_") ( {NCNameChar} )*


%{
	public int lineno() { return yyline + 1; }
	public int colno() { return yycolumn + 1; }

	private Symbol symbol(int type) {
		return new Symbol(type, lineno(), colno());
	}
	private Symbol symbol(int type, Object value) {
		return new Symbol(type, lineno(), colno(), value);
	}

	private int commentLevel = 0;
%}

%state COMMENT

%%

<YYINITIAL> {

"(:"			{ commentLevel++; // int overflow =P
			  yybegin(COMMENT); 
			}

"\["	{ return symbol(XpathSym.LBRACKET); }
"\]"	{ return symbol(XpathSym.RBRACKET); }
"\("	{ return symbol(XpathSym.LPAR); }
"\)"	{ return symbol(XpathSym.RPAR); }

"\+"	{ return symbol(XpathSym.PLUS); }
"\-"	{ return symbol(XpathSym.MINUS); }
"\*"	{ return symbol(XpathSym.STAR); }
"\|"	{ return symbol(XpathSym.PIPE); }

"="	{ return symbol(XpathSym.EQUALS); }
"!="	{ return symbol(XpathSym.NOTEQUALS); }
"<"	{ return symbol(XpathSym.LESSTHAN); }
"<="	{ return symbol(XpathSym.LESSEQUAL); }
">"	{ return symbol(XpathSym.GREATER); }
">="	{ return symbol(XpathSym.GREATEREQUAL); }

"<<"	{ return symbol(XpathSym.LESS_LESS); }
">>"	{ return symbol(XpathSym.GREATER_GREATER); }

"/"	{ return symbol(XpathSym.FORWARD_SLASH); }
"//"	{ return symbol(XpathSym.FORWARD_SLASHSLASH); }

"::"	{ return symbol(XpathSym.COLONCOLON); }
"@"	{ return symbol(XpathSym.AT_SYM); }
"\.\."	{ return symbol(XpathSym.DOTDOT); }
":"	{ return symbol(XpathSym.COLON); }
","	{ return symbol(XpathSym.COMMA); }
"\$"	{ return symbol(XpathSym.DOLLAR); }
"\."	{ return symbol(XpathSym.DOT); }
"\?"	{ return symbol(XpathSym.QUESTIONMARK); }


"child"				{ return symbol(XpathSym.CHILD); }
"descendant"			{ return symbol(XpathSym.DESCENDANT); }
"attribute"			{ return symbol(XpathSym.ATTRIBUTE); }
"self"				{ return symbol(XpathSym.SELF); }
"descendant\-or\-self"		{ return symbol(XpathSym.DESCENDANT_OR_SELF); }

"following\-sibling"		{ return symbol(XpathSym.FOLLOWING_SIBLING); }
"following"			{ return symbol(XpathSym.FOLLOWING); }
"namespace"			{ return symbol(XpathSym.NAMESPACE); }
"parent"			{ return symbol(XpathSym.PARENT); }

"ancestor"			{ return symbol(XpathSym.ANCESTOR); }
"preceding\-sibling"		{ return symbol(XpathSym.PRECEDING_SIBLING); }
"preceding"			{ return symbol(XpathSym.PRECEDING); }
"ancestor\-or\-self"		{ return symbol(XpathSym.ANCESTOR_OR_SELF); }

"eq"				{ return symbol(XpathSym.EQ); }
"ne"				{ return symbol(XpathSym.NE); }
"lt"				{ return symbol(XpathSym.LT); }
"le"				{ return symbol(XpathSym.LE); }
"gt"				{ return symbol(XpathSym.GT); }
"ge"				{ return symbol(XpathSym.GE); }

"idiv"				{ return symbol(XpathSym.IDIV); }
"div"				{ return symbol(XpathSym.DIV); }
"mod"				{ return symbol(XpathSym.MOD); }

"union"				{ return symbol(XpathSym.UNION); }
"intersect"			{ return symbol(XpathSym.INTERSECT); }
"except"			{ return symbol(XpathSym.EXCEPT); }

"instance"			{ return symbol(XpathSym.INSTANCE); }
"treat"				{ return symbol(XpathSym.TREAT); }
"castable"			{ return symbol(XpathSym.CASTABLE); }
"cast"				{ return symbol(XpathSym.CAST); }
"as"				{ return symbol(XpathSym.AS); }
"of"				{ return symbol(XpathSym.OF); }
"is"				{ return symbol(XpathSym.IS); }

"for"				{ return symbol(XpathSym.FOR); }
"in"				{ return symbol(XpathSym.IN); }
"return"			{ return symbol(XpathSym.RETURN); }
"satisfies"			{ return symbol(XpathSym.SATISFIES); }
"to"				{ return symbol(XpathSym.TO); }
"some"				{ return symbol(XpathSym.SOME); }
"every"				{ return symbol(XpathSym.EVERY); }
"if"				{ return symbol(XpathSym.IF); }
"then"				{ return symbol(XpathSym.THEN); }
"else"				{ return symbol(XpathSym.ELSE); }
"and"				{ return symbol(XpathSym.AND); }
"or"				{ return symbol(XpathSym.OR); }

"empty-sequence"	{ return symbol(XpathSym.EMPTY_SEQUENCE); }
"item"				{ return symbol(XpathSym.ITEM); }
"node"				{ return symbol(XpathSym.NODE); }
"document\-node"		{ return symbol(XpathSym.DOCUMENT_NODE); }
"text"				{ return symbol(XpathSym.TEXT); }
"comment"			{ return symbol(XpathSym.COMMENT); }
"processing\-instruction"	{ return symbol(XpathSym.PROCESSING_INSTRUCTION); }
"schema\-attribute"		{ return symbol(XpathSym.SCHEMA_ATTRIBUTE); }
"element"			{ return symbol(XpathSym.ELEMENT); }
"schema\-element"		{ return symbol(XpathSym.SCHEMA_ELEMENT); }
"typeswitch"        { return symbol(XpathSym.TYPESWITCH); }

{StringLiteral}		{
				// get rid of quotes
				String str = yytext();
				assert str.length() >= 2;
				return symbol(XpathSym.STRING, org.eclipse.wst.xml.xpath2.processor.internal.utils.LiteralUtils.unquote(str)); 
			}
{Digits}		{ return symbol(XpathSym.INTEGER, new java.math.BigInteger(yytext())); }
{DoubleLiteral}		{ return symbol(XpathSym.DOUBLE, new Double(yytext())); }
{DecimalLiteral}	{ return symbol(XpathSym.DECIMAL, new java.math.BigDecimal(yytext())); }
{NCName}		{ return symbol(XpathSym.NCNAME, yytext()); }




{Whitespace} { /* ignore */ }


.	{ 
		String err = "Unknown character at line " + lineno(); 
		err += " col " + colno();
		err += ": " + yytext(); 
			     
		throw new JFlexError(err); 
	}

}

<COMMENT> {
	"(:"		{ commentLevel++; }
	":)"		{ commentLevel--; 
			  if(commentLevel == 0)
		          	yybegin(YYINITIAL);
			}
	.|\n		{ /* ignore */ }
}
