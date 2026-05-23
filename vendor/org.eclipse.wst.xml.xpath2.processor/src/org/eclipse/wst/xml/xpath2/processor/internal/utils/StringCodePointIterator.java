/*******************************************************************************
 * Copyright (c) 2009, 2011 Jesper Steen Moeller and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Jesper Steen Moeller - bug 282096 - initial API and implementation
 *     Mukul Gandhi - bug 280798 - PsychoPath support for JDK 1.4
 *******************************************************************************/
 
package org.eclipse.wst.xml.xpath2.processor.internal.utils;

import com.ibm.icu.lang.UCharacter;

public final class StringCodePointIterator implements CodePointIterator
{
    private String text;
    private int end;
    // invariant: 0 <= pos <= end
    private int pos;
	private int cpPos;

    /**
     * Constructs an iterator with an initial index of 0.
     */
    public StringCodePointIterator(String text)
    {
        if (text == null)
            throw new NullPointerException();
        
        this.text = text;
        this.end = text.length();
        if (end > 0 && UCharacter.isHighSurrogate(text.charAt(end-1)))
            throw new IllegalArgumentException("Invalid UTF-16 sequence ending with a high surrogate");
       
        this.pos = 0;
        this.cpPos = 0;
    }

    /**
     * Reset this iterator to point to a new string.  This package-visible
     * method is used by other java.text classes that want to avoid allocating
     * new StringCodePointIterator objects every time their setText method
     * is called.
     *
     * @param  text   The String to be iterated over
     * @since 1.2
     */
    public void setText(String text) {
        if (text == null)
            throw new NullPointerException();
        this.text = text;
        this.end = text.length();
        this.pos = 0;
        this.cpPos = 0;
    }

    /**
     * Implements CodePointIterator.first() for String.
     * @see CodePointIterator#first
     */
    public int first()
    {
        pos = 0;
        cpPos = 0;
        return current();
    }

    /**
     * Implements CodePointIterator.last() for String.
     * @see CodePointIterator#last
     */
    public int last()
    {
        pos = end;
        cpPos = UCharacter.codePointCount(text, 0, pos);
        return previous();
     }

    /**
     * Implements CodePointIterator.current() for String.
     * @see CodePointIterator#current
     */
    public int current()
    {
        if (pos < end) {
            char ch1 =  text.charAt(pos);
            if (UCharacter.isHighSurrogate(ch1)) 
               return UCharacter.toCodePoint(ch1, text.charAt(pos+1));
            
            return ch1;
        }
        else {
            return DONE;
        }
    }

    /**
     * Implements CodePointIterator.next() for String.
     * @see CodePointIterator#next
     */
    public int next()
    {
        if (pos < end - 1) {
            pos++;
            if (UCharacter.isLowSurrogate(text.charAt(pos))) pos++;
            cpPos++;
            return current();
        }
        else {
        	pos = end;
            return DONE;
        }
    }

    /**
     * Implements CodePointIterator.previous() for String.
     * @see CodePointIterator#previous
     */
    public int previous()
    {
        if (pos > 0) {
            pos--;
            if (UCharacter.isLowSurrogate(text.charAt(pos))) pos--; 
            cpPos--;
            return current();
        }
        else {
            return DONE;
        }
    }

    /**
     * Implements CodePointIterator.getIndex() for String.
     * @see CodePointIterator#getIndex
     */
    public int getIndex()
    {
        return cpPos;
    }

    /**
     * Compares the equality of two StringCodePointIterator objects.
     * @param obj the StringCodePointIterator object to be compared with.
     * @return true if the given obj is the same as this
     * StringCodePointIterator object; false otherwise.
     */
	public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (!(obj instanceof StringCodePointIterator))
            return false;

        StringCodePointIterator that = (StringCodePointIterator) obj;

        if (hashCode() != that.hashCode())
            return false;
        if (!text.equals(that.text))
            return false;
        if (pos != that.pos || end != that.end)
            return false;
        return true;
    }

    /**
     * Computes a hashcode for this iterator.
     * @return A hash code
     */
	public int hashCode()
    {
        return text.hashCode() ^ pos ^ end;
    }

    /**
     * Creates a copy of this iterator.
     * @return A copy of this
     */
	public Object clone()
    {
        try {
            StringCodePointIterator other
            = (StringCodePointIterator) super.clone();
            return other;
        }
        catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
    }

}
