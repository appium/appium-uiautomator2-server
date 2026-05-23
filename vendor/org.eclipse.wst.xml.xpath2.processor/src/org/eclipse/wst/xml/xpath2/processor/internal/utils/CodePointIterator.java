/*******************************************************************************
 * Copyright (c) 2009 Jesper Steen Moeller and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Jesper Steen Moeller - bug 282096 - initial API and implementation
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal.utils;

public interface CodePointIterator extends Cloneable
{
    /**
     * Sentinel value returned from iterator when the end is reached.
     * The value is -1 which will never be a valid codepoint.
     */
    public static final int DONE = -1;

    /**
     * Resets the position to 0 and returns the first code point.
     * @return the first code point in the text, or DONE if the text is empty
     */
    public int first();

    /**
     * Sets the position to the last possible position (or 0 if the text is empty)
     * and returns the code point at that position.
     * @return the last code point in the text, or DONE if the text is empty
     * @see #getEndIndex()
     */
    public int last();

    /**
     * Gets the code point at the current position (as returned by getIndex()).
     * @return the code point at the current position or DONE if the current
     * position is off the end of the text.
     * @see #getIndex()
     */
    public int current();

    /**
     * Increments the iterator's code point index by one and returns the code point
     * at the new index.  If the resulting index is at the end of the string, the
     * index is not incremented, and DONE is returned.
     * @return the code point at the new position or DONE if the new
     * position is after the text range.
     */
    public int next();

    /**
     * Decrements the iterator's index by one and returns the character
     * at the new index. If the current index is 0, the index
     * remains at 0 and a value of DONE is returned.
     *
     * @return the code point at the new position (or DONE if the current
     * position is 0)
     */
    public int previous();

    /**
     * Returns the current index (as a codepoint, not a string index).
     * @return the current index.
     */
    public int getIndex();

    /**
     * Create a copy of this code point iterator
     * @return A copy of this
     */
    public Object clone();

}
