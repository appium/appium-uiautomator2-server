/*
 * <tt>ASCIICodeToKeyEventConstantTranslator.java</tt>
 *
 * VKB (Virtual KeyBoard) is free software: you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * VKB (Virtual KeyBoard) is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for
 * more details.
 * You should have received a copy of the GNU General Public License along with
 * VKB (Virtual KeyBoard).  If not, see <http://www.gnu.org/licenses/>.
 *
 * @author  Tobias Groch <tgroch@stud.hs-bremen.de>
 * @author  Florian Wolters <flwolters@stud.hs-bremen.de>
 * @license http://gnu.org/licenses/gpl.txt GNU General Public License
 * @version SVN: Id:$
 * @since   File available since Release 1.0.0
 */

package io.appium.uiautomator2.utils.w3c;

import android.support.annotation.Nullable;
import android.view.KeyEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * The class <tt>CodeToKeyEventConstantTranslator</tt> translates the unicode
 * code of the key that was pressed into the corresponding key code constant of
 * <tt>{@link KeyEvent}</tt>.
 *
 * @author Tobias Groch <tgroch@stud.hs-bremen.de>
 * @author Florian Wolters <flwolters@stud.hs-bremen.de>
 * @version Release: @package_version@
 * @since Class available since Release 1.0.0
 */
public final class ASCIICodeToKeyEventConstantTranslator {

    /**
     * Contains the mappings to convert the ASCII code of the key that was
     * pressed into the corresponding key code constant of <tt>{@link
     * KeyEvent}</tt>.
     */
    private static final Map<Integer, Integer> _MAP = new HashMap<>();

    static {
        // Control Characters

        ASCIICodeToKeyEventConstantTranslator._MAP.put(
                ASCIICharacters.BS, KeyEvent.KEYCODE_DEL
        );
        ASCIICodeToKeyEventConstantTranslator._MAP.put(
                ASCIICharacters.HT, KeyEvent.KEYCODE_TAB
        );
        ASCIICodeToKeyEventConstantTranslator._MAP.put(
                ASCIICharacters.LF, KeyEvent.KEYCODE_ENTER
        );
        ASCIICodeToKeyEventConstantTranslator._MAP.put(
                ASCIICharacters.ESC, KeyEvent.KEYCODE_ESCAPE
        );
        ASCIICodeToKeyEventConstantTranslator._MAP.put(
                ASCIICharacters.DEL, KeyEvent.KEYCODE_FORWARD_DEL
        );

        // Graphic Characters

        // '0'-'9'
        ASCIICodeToKeyEventConstantTranslator._mapCharRange('0', '9', 41);
        // 'A'-'Z'
        ASCIICodeToKeyEventConstantTranslator._mapCharRange('A', 'Z', 36);
        // 'a'-'z'
        ASCIICodeToKeyEventConstantTranslator._mapCharRange('a', 'z', 68);

        ASCIICodeToKeyEventConstantTranslator._MAP.put(
                ASCIICharacters.APOSTROPHE, KeyEvent.KEYCODE_APOSTROPHE
        );
        ASCIICodeToKeyEventConstantTranslator._MAP.put(
                ASCIICharacters.ASTERISK, KeyEvent.KEYCODE_STAR
        );
        ASCIICodeToKeyEventConstantTranslator._MAP.put(
                ASCIICharacters.CLOSING_BRACKET, KeyEvent.KEYCODE_RIGHT_BRACKET
        );
        ASCIICodeToKeyEventConstantTranslator._MAP.put(
                ASCIICharacters.CLOSING_PARENTHESIS,
                KeyEvent.KEYCODE_NUMPAD_RIGHT_PAREN
        );
        ASCIICodeToKeyEventConstantTranslator._MAP.put(
                ASCIICharacters.COMMA, KeyEvent.KEYCODE_COMMA
        );
        ASCIICodeToKeyEventConstantTranslator._MAP.put(
                ASCIICharacters.COMMERCIAL_AT, KeyEvent.KEYCODE_AT
        );
        ASCIICodeToKeyEventConstantTranslator._MAP.put(
                ASCIICharacters.EQUALS, KeyEvent.KEYCODE_EQUALS
        );
        ASCIICodeToKeyEventConstantTranslator._MAP.put(
                ASCIICharacters.GRAVE_ACCENT, KeyEvent.KEYCODE_GRAVE
        );
        ASCIICodeToKeyEventConstantTranslator._MAP.put(
                ASCIICharacters.HYPHEN, KeyEvent.KEYCODE_MINUS
        );
        ASCIICodeToKeyEventConstantTranslator._MAP.put(
                ASCIICharacters.NUMBER_SIGN, KeyEvent.KEYCODE_POUND
        );
        ASCIICodeToKeyEventConstantTranslator._MAP.put(
                ASCIICharacters.OPENING_BRACKET, KeyEvent.KEYCODE_LEFT_BRACKET
        );
        ASCIICodeToKeyEventConstantTranslator._MAP.put(
                ASCIICharacters.OPENING_PARENTHESIS,
                KeyEvent.KEYCODE_NUMPAD_LEFT_PAREN
        );
        ASCIICodeToKeyEventConstantTranslator._MAP.put(
                ASCIICharacters.PERIOD, KeyEvent.KEYCODE_PERIOD
        );
        ASCIICodeToKeyEventConstantTranslator._MAP.put(
                ASCIICharacters.PLUS, KeyEvent.KEYCODE_PLUS
        );
        ASCIICodeToKeyEventConstantTranslator._MAP.put(
                ASCIICharacters.REVERSE_SLANT, KeyEvent.KEYCODE_BACKSLASH
        );
        ASCIICodeToKeyEventConstantTranslator._MAP.put(
                ASCIICharacters.SEMICOLON, KeyEvent.KEYCODE_SEMICOLON
        );
        ASCIICodeToKeyEventConstantTranslator._MAP.put(
                ASCIICharacters.SLANT, KeyEvent.KEYCODE_SLASH
        );
        ASCIICodeToKeyEventConstantTranslator._MAP.put(
                ASCIICharacters.SPACE, KeyEvent.KEYCODE_SPACE
        );
    }

    /**
     * Puts a range of ASCII codes and the corresponding key code constants of
     * <tt>{@link KeyEvent}</tt> into the map.
     *
     * @param start  the character to start with.
     * @param end    the character to end with.
     * @param offset the offset between the ASCII code and the corresponding key
     *               code constant of <tt>{@link KeyEvent}</tt>
     */
    private static void _mapCharRange(
            final char start, final char end, final int offset) {
        int i;
        for (char c = start; c < end; ++c) {
            i = c;
            ASCIICodeToKeyEventConstantTranslator._MAP.put(i, i - offset);
        }
    }

    /**
     * <tt>ASCIICodeToKeyEventConstantTranslator</tt> instances should
     * <i>NOT</i> be constructed in standard programming.
     * <p>
     * Instead, the class should be used as:
     * <code>
     * CodeToKeyEventConstantTranslator.translate(ASCIICharacters.DEL);
     * </code>
     */
    private ASCIICodeToKeyEventConstantTranslator() {
    }

    /**
     * Returns the corresponding key code constant of <tt>{@link KeyEvent}</tt>
     * for the specified unicode code of the key that was pressed.
     *
     * @param primaryCode the unicode code of the key that was released.
     * @return the key code constant of <tt>{@link KeyEvent}</tt> to which the
     * specified code is mapped, or <tt>null</tt> the code is unknown.
     */
    @Nullable
    public static Integer translate(final int primaryCode) {
        return ASCIICodeToKeyEventConstantTranslator._MAP.get(primaryCode);
    }

}
