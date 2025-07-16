package io.appium.uiautomator2.utils;

import java.util.regex.Pattern;

/**
 * Turns a glob expression into a regular expression
 * '*' and '?' are supported as placeholders
 */
public class GlobMatcher {

    // Converts glob pattern to regex pattern
    public static String globToRegex(String glob) {
        StringBuilder regex = new StringBuilder();
        regex.append("^"); // Match beginning of string

        for (int i = 0; i < glob.length(); i++) {
            char c = glob.charAt(i);
            switch (c) {
                case '*':
                    regex.append(".*");
                    break;
                case '?':
                    regex.append(".");
                    break;
                case '.':
                case '(':
                case ')':
                case '+':
                case '|':
                case '^':
                case '$':
                case '@':
                case '%':
                case '{':
                case '}':
                case '[':
                case ']':
                    // Escape special regex characters
                    regex.append("\\").append(c);
                    break;
                case '\\':
                    if (i + 1 < glob.length())
                    {
                        char nextChar = glob.charAt(i + 1);
                        // Escape the glob patterns
                        if (nextChar == '?' || nextChar == '*') {
                            c = nextChar;
                            ++i;
                        }
                    }
                    regex.append("\\").append(c);
                    break;
                default:
                    regex.append(c);
                    break;
            }
        }

        regex.append("$"); // Match end of string
        return regex.toString();
    }

    /**
     * Returns true if string matches the glob pattern
     */
    public static boolean matches(String glob, String input) {
        String regex = globToRegex(glob);
        return Pattern.matches(regex, input);
    }
}