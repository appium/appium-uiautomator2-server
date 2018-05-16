/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.appium.uiautomator2.utils.w3c;

import android.support.annotation.Nullable;
import android.view.KeyEvent;

/**
 * Representations of pressable keys that aren't text.  These are stored in the Unicode PUA (Private
 * Use Area) code points, 0xE000-0xF8FF.
 *
 * @see <a href="http://www.google.com.au/search?&amp;q=unicode+pua&amp;btnK=Search">http://www.google.com/search?&amp;q=unicode+pua&amp;btnK=Search</a>
 */
public enum W3CKeyCode {
    NULL(0xE000, KeyEvent.KEYCODE_UNKNOWN),
    CANCEL(0xE001, KeyEvent.KEYCODE_BREAK), // ^break
    HELP(0xE002, KeyEvent.KEYCODE_HELP),
    BACK_SPACE(0xE003, KeyEvent.KEYCODE_DEL),
    TAB(0xE004, KeyEvent.KEYCODE_TAB),
    CLEAR(0xE005, KeyEvent.KEYCODE_CLEAR),
    RETURN(0xE006, KeyEvent.KEYCODE_ENTER),
    ENTER(0xE007, KeyEvent.KEYCODE_ENTER),
    SHIFT(0xE008, KeyEvent.KEYCODE_SHIFT_LEFT),
    LEFT_SHIFT(SHIFT.getW3CCodePoint(), KeyEvent.KEYCODE_SHIFT_LEFT),
    CONTROL(0xE009, KeyEvent.KEYCODE_CTRL_LEFT),
    LEFT_CONTROL(CONTROL.getW3CCodePoint(), KeyEvent.KEYCODE_CTRL_LEFT),
    ALT(0xE00A, KeyEvent.KEYCODE_ALT_LEFT),
    LEFT_ALT(ALT.getW3CCodePoint(), KeyEvent.KEYCODE_ALT_LEFT),
    PAUSE(0xE00B, KeyEvent.KEYCODE_MEDIA_PAUSE),
    ESCAPE(0xE00C, KeyEvent.KEYCODE_ESCAPE),
    SPACE(0xE00D, KeyEvent.KEYCODE_SPACE),
    PAGE_UP(0xE00E, KeyEvent.KEYCODE_PAGE_UP),
    PAGE_DOWN(0xE00F, KeyEvent.KEYCODE_PAGE_DOWN),
    END(0xE010, KeyEvent.KEYCODE_MOVE_END),
    HOME(0xE011, KeyEvent.KEYCODE_HOME),
    LEFT(0xE012, KeyEvent.KEYCODE_DPAD_LEFT),
    ARROW_LEFT(LEFT.getW3CCodePoint(), KeyEvent.KEYCODE_DPAD_LEFT),
    UP(0xE013, KeyEvent.KEYCODE_DPAD_UP),
    ARROW_UP(UP.getW3CCodePoint(), KeyEvent.KEYCODE_DPAD_UP),
    RIGHT(0xE014, KeyEvent.KEYCODE_DPAD_RIGHT),
    ARROW_RIGHT(RIGHT.getW3CCodePoint(), KeyEvent.KEYCODE_DPAD_RIGHT),
    DOWN(0xE015, KeyEvent.KEYCODE_DPAD_DOWN),
    ARROW_DOWN(DOWN.getW3CCodePoint(), KeyEvent.KEYCODE_DPAD_DOWN),
    INSERT(0xE016, KeyEvent.KEYCODE_INSERT),
    DELETE(0xE017, KeyEvent.KEYCODE_FORWARD_DEL),
    SEMICOLON(0xE018, KeyEvent.KEYCODE_SEMICOLON),
    EQUALS(0xE019, KeyEvent.KEYCODE_EQUALS),

    // Number pad keys
    NUMPAD0(0xE01A, KeyEvent.KEYCODE_NUMPAD_0),
    NUMPAD1(0xE01B, KeyEvent.KEYCODE_NUMPAD_1),
    NUMPAD2(0xE01C, KeyEvent.KEYCODE_NUMPAD_2),
    NUMPAD3(0xE01D, KeyEvent.KEYCODE_NUMPAD_3),
    NUMPAD4(0xE01E, KeyEvent.KEYCODE_NUMPAD_4),
    NUMPAD5(0xE01F, KeyEvent.KEYCODE_NUMPAD_5),
    NUMPAD6(0xE020, KeyEvent.KEYCODE_NUMPAD_6),
    NUMPAD7(0xE021, KeyEvent.KEYCODE_NUMPAD_7),
    NUMPAD8(0xE022, KeyEvent.KEYCODE_NUMPAD_8),
    NUMPAD9(0xE023, KeyEvent.KEYCODE_NUMPAD_9),
    MULTIPLY(0xE024, KeyEvent.KEYCODE_NUMPAD_MULTIPLY),
    ADD(0xE025, KeyEvent.KEYCODE_NUMPAD_ADD),
    SEPARATOR(0xE026, KeyEvent.KEYCODE_NUMPAD_COMMA),
    SUBTRACT(0xE027, KeyEvent.KEYCODE_NUMPAD_SUBTRACT),
    DECIMAL(0xE028, KeyEvent.KEYCODE_NUMPAD_DOT),
    DIVIDE(0xE029, KeyEvent.KEYCODE_NUMPAD_DIVIDE),

    // Function keys
    F1(0xE031, KeyEvent.KEYCODE_F1),
    F2(0xE032, KeyEvent.KEYCODE_F2),
    F3(0xE033, KeyEvent.KEYCODE_F3),
    F4(0xE034, KeyEvent.KEYCODE_F4),
    F5(0xE035, KeyEvent.KEYCODE_F5),
    F6(0xE036, KeyEvent.KEYCODE_F6),
    F7(0xE037, KeyEvent.KEYCODE_F7),
    F8(0xE038, KeyEvent.KEYCODE_F8),
    F9(0xE039, KeyEvent.KEYCODE_F9),
    F10(0xE03A, KeyEvent.KEYCODE_F10),
    F11(0xE03B, KeyEvent.KEYCODE_F11),
    F12(0xE03C, KeyEvent.KEYCODE_F12),

    META(0xE03D, KeyEvent.KEYCODE_META_LEFT),
    COMMAND(META.getW3CCodePoint(), KeyEvent.KEYCODE_META_LEFT),

    ZENKAKU_HANKAKU(0xE040, KeyEvent.KEYCODE_ZENKAKU_HANKAKU);

    private final int w3cCodePoint;
    private final int androidCodePoint;

    W3CKeyCode(int w3cCodePoint, int androidCodePoint) {
        this.w3cCodePoint = w3cCodePoint;
        this.androidCodePoint = androidCodePoint;
    }

    public int getW3CCodePoint() {
        return w3cCodePoint;
    }

    public int getAndroidCodePoint() {
        return androidCodePoint;
    }

    @Nullable
    public Integer toAndroidMetaKeyCode() {
        switch (this) {
            case SHIFT:
            case LEFT_SHIFT:
                return KeyEvent.META_SHIFT_ON;
            case ALT:
            case LEFT_ALT:
                return KeyEvent.META_ALT_ON;
            case CONTROL:
            case LEFT_CONTROL:
                return KeyEvent.META_CTRL_ON;
            case META:
            case COMMAND:
                return KeyEvent.META_META_ON;
            default:
                return null;
        }
    }

    @Nullable
    public static W3CKeyCode fromCodePoint(int codePoint) {
        for (W3CKeyCode value : values()) {
            if (value.getW3CCodePoint() == codePoint) {
                return value;
            }
        }
        return null;
    }
}
