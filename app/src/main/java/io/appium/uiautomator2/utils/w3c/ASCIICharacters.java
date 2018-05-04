/*
 * <tt>ASCIICharacters.java</tt>
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
. */

package io.appium.uiautomator2.utils.w3c;

/**
 * The class <tt>ASCIICharacters</tt> contains constants for all characters of
 * the American Standard Code for Information Interchange (ASCII).
 *
 * ASCII contains both control (32 codes) and graphic (95 codes) characters.
 *
 * @author  Tobias Groch <tgroch@stud.hs-bremen.de>
 * @author  Florian Wolters <flwolters@stud.hs-bremen.de>
 * @version Release: @package_version@
 * @see     <a href="https://tools.ietf.org/rfc/rfc20.txt">ASCII format for
 *          Network Interchange</a>
 * @since   Class available since Release 1.0.0
. */
public final class ASCIICharacters {

    // Control Characters

    /** Null. */
    public static final int NUL = 0;
    /** Start of Heading (Communication Control). */
    public static final int SOH = 1;
    /** Start of Text (Communication Control). */
    public static final int STX = 2;
    /** End of Text (Communication Control). */
    public static final int ETX = 3;
    /** End of Transmission (Communication Control). */
    public static final int EOT = 4;
    /** Enquiry (Communication Control). */
    public static final int ENW = 5;
    /** Acknowledge (Communication Control). */
    public static final int ACK = 6;
    /** Bell (audible or attention signal). */
    public static final int BEL = 7;
    /** Backspace (Format Effector). */
    public static final int BS = 8;
    /** Horizontal Tabulation (punched card skip) (Format Effector). */
    public static final int HT = 9;
    /** Line Feed (Format Effector). */
    public static final int LF = 10;
    /** Vertical Tabulation (Format Effector). */
    public static final int VT = 11;
    /** Form Feed (Format Effector). */
    public static final int FF = 12;
    /** Carriage Return (Format Effector). */
    public static final int CR = 13;
    /** Shift Out. */
    public static final int SO = 14;
    /** Shift In. */
    public static final int SI = 15;
    /** Data Link Escape (Communication Control). */
    public static final int DLE = 16;
    /** Device Control 1. */
    public static final int DC1 = 17;
    /** Device Control 2. */
    public static final int DC2 = 18;
    /** Device Control 3. */
    public static final int DC3 = 19;
    /** Device Control 4 (Stop). */
    public static final int DC4 = 20;
    /** Negative Acknowledge (Communication Control). */
    public static final int NAK = 21;
    /** Synchronous Idle (Communication Control). */
    public static final int SYN = 22;
    /** End of Transmission Block (Communication Control). */
    public static final int ETB = 23;
    /** Cancel. */
    public static final int CAN = 24;
    /** End of Medium. */
    public static final int EM = 25;
    /** Substitute. */
    public static final int SUB = 26;
    /** Escape. */
    public static final int ESC = 27;
    /** File Separator (Information Separator). */
    public static final int FS = 28;
    /** Group Separator (Information Separator). */
    public static final int GS = 29;
    /** Record Separator (Information Separator). */
    public static final int RS = 30;
    /** Unit Separator (Information Separator). */
    public static final int US = 31;

    // Graphic Characters

    /** Space (Normally Non-Printing). */
    public static final int SPACE = 32;
    /** Exclamation Point. */
    public static final int EXCLAMATION_POINT = 33;
    /** Quotation Marks. */
    public static final int QUOTATION_MARKS = 34;
    /** Number Sign. */
    public static final int NUMBER_SIGN = 35;
    /** Dollar Sign. */
    public static final int DOLLAR_SIGN = 36;
    /** Percent. */
    public static final int PERCENT = 37;
    /** Ampersand. */
    public static final int AMPERSAND = 38;
    /** Apostrophe. */
    public static final int APOSTROPHE = 39;
    /** Opening Parenthesis. */
    public static final int OPENING_PARENTHESIS = 40;
    /** Closing Parenthesis. */
    public static final int CLOSING_PARENTHESIS = 41;
    /** Asterisk. */
    public static final int ASTERISK = 42;
    /** Plus. */
    public static final int PLUS = 43;
    /** Comma. */
    public static final int COMMA = 44;
    /** Hyphen (Minus). */
    public static final int HYPHEN = 45;
    /** Period (Decimal Point). */
    public static final int PERIOD = 46;
    /** Slant. */
    public static final int SLANT = 47;
    /** '0'. */
    public static final int DIGIT_0 = 48;
    /** '1'. */
    public static final int DIGIT_1 = 49;
    /** '2'. */
    public static final int DIGIT_2 = 50;
    /** '3'. */
    public static final int DIGIT_3 = 51;
    /** '4'. */
    public static final int DIGIT_4 = 52;
    /** '5'. */
    public static final int DIGIT_5 = 53;
    /** '6'. */
    public static final int DIGIT_6 = 54;
    /** '7'. */
    public static final int DIGIT_7 = 55;
    /** '8'. */
    public static final int DIGIT_8 = 56;
    /** '9'. */
    public static final int DIGIT_9 = 57;
    /** Colon. */
    public static final int COLON = 58;
    /** Semicolon. */
    public static final int SEMICOLON = 59;
    /** Less Than. */
    public static final int LESS_THAN = 60;
    /** Equals. */
    public static final int EQUALS = 61;
    /** Greater Than. */
    public static final int GREATER_THAN = 62;
    /** Question Mark. */
    public static final int QUESTION_MARK = 63;
    /** Commercial At. */
    public static final int COMMERCIAL_AT = 64;
    /** 'A'. */
    public static final int UPPERCASE_A = 65;
    /** 'B'. */
    public static final int UPPERCASE_B = 66;
    /** 'C'. */
    public static final int UPPERCASE_C = 67;
    /** 'D'. */
    public static final int UPPERCASE_D = 68;
    /** 'E'. */
    public static final int UPPERCASE_E = 69;
    /** 'F'. */
    public static final int UPPERCASE_F = 70;
    /** 'G'. */
    public static final int UPPERCASE_G = 71;
    /** 'H'. */
    public static final int UPPERCASE_H = 72;
    /** 'I'. */
    public static final int UPPERCASE_I = 73;
    /** 'J'. */
    public static final int UPPERCASE_J = 74;
    /** 'K'. */
    public static final int UPPERCASE_K = 75;
    /** 'L'. */
    public static final int UPPERCASE_L = 76;
    /** 'M'. */
    public static final int UPPERCASE_M = 77;
    /** 'N'. */
    public static final int UPPERCASE_N = 78;
    /** 'O'. */
    public static final int UPPERCASE_O = 79;
    /** 'P'. */
    public static final int UPPERCASE_P = 80;
    /** 'Q'. */
    public static final int UPPERCASE_Q = 81;
    /** 'R'. */
    public static final int UPPERCASE_R = 82;
    /** 'S'. */
    public static final int UPPERCASE_S = 83;
    /** 'T'. */
    public static final int UPPERCASE_T = 84;
    /** 'U'. */
    public static final int UPPERCASE_U = 85;
    /** 'V'. */
    public static final int UPPERCASE_V = 86;
    /** 'W'. */
    public static final int UPPERCASE_W = 87;
    /** 'X'. */
    public static final int UPPERCASE_X = 88;
    /** 'Y'. */
    public static final int UPPERCASE_Y = 89;
    /** 'Z'. */
    public static final int UPPERCASE_Z = 90;
    /** Opening Bracket. */
    public static final int OPENING_BRACKET = 91;
    /** Reverse Slant. */
    public static final int REVERSE_SLANT = 92;
    /** Closing Bracket. */
    public static final int CLOSING_BRACKET = 93;
    /** Circumflex. */
    public static final int CIRCUMFLEX = 94;
    /** Underline. */
    public static final int UNDERLINE = 95;
    /** Grave Accent (Opening Single Quotation Mark). */
    public static final int GRAVE_ACCENT = 96;
    /** 'a'. */
    public static final int LOWERCASE_A = 97;
    /** 'b'. */
    public static final int LOWERCASE_B = 98;
    /** 'c'. */
    public static final int LOWERCASE_C = 99;
    /** 'd'. */
    public static final int LOWERCASE_D = 100;
    /** 'e'. */
    public static final int LOWERCASE_E = 101;
    /** 'f'. */
    public static final int LOWERCASE_F = 102;
    /** 'g'. */
    public static final int LOWERCASE_G = 103;
    /** 'h'. */
    public static final int LOWERCASE_H = 104;
    /** 'i'. */
    public static final int LOWERCASE_I = 105;
    /** 'j'. */
    public static final int LOWERCASE_J = 106;
    /** 'k'. */
    public static final int LOWERCASE_K = 107;
    /** 'l'. */
    public static final int LOWERCASE_L = 108;
    /** 'm'. */
    public static final int LOWERCASE_M = 109;
    /** 'n'. */
    public static final int LOWERCASE_N = 110;
    /** 'o'. */
    public static final int LOWERCASE_O = 111;
    /** 'p'. */
    public static final int LOWERCASE_P = 112;
    /** 'q'. */
    public static final int LOWERCASE_Q = 113;
    /** 'r'. */
    public static final int LOWERCASE_R = 114;
    /** 's'. */
    public static final int LOWERCASE_S = 115;
    /** 't'. */
    public static final int LOWERCASE_T = 116;
    /** 'u'. */
    public static final int LOWERCASE_U = 117;
    /** 'v'. */
    public static final int LOWERCASE_V = 118;
    /** 'w'. */
    public static final int LOWERCASE_W = 119;
    /** 'x'. */
    public static final int LOWERCASE_X = 120;
    /** 'y'. */
    public static final int LOWERCASE_Y = 121;
    /** 'z'. */
    public static final int LOWERCASE_Z = 122;
    /** Opening Brace. */
    public static final int OPENING_BRACE = 123;
    /** Vertical Line. */
    public static final int VERTICAL_LINE = 124;
    /** Closing Brace. */
    public static final int CLOSING_BRACE = 125;
    /** Tilde. */
    public static final int TILDE = 126;
    /** Delete. */
    public static final int DEL = 127;

    /**
     * <tt>ASCIICharacter</tt> instances should <i>NOT</i> be constructed in
     * standard programming.
     *
     * Instead, the class should be used as:
     * <code>
     * int asciiForDel = ASCIICharacter.DEL;
     * </code>
     */
    private ASCIICharacters() { }

}
