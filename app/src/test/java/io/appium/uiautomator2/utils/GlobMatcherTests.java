package io.appium.uiautomator2.utils;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GlobMatcherTests {
    @Test
    public void emptyPattern() {
        assertTrue(GlobMatcher.matches("", ""));
        assertFalse(GlobMatcher.matches("", "mytest"));
    }

    @Test
    public void noPlaceholders() {
        assertTrue(GlobMatcher.matches("mytest", "mytest"));
        assertFalse(GlobMatcher.matches("mytest", "other"));
    }

    @Test
    public void prefixPlaceHolders() {
        assertTrue(GlobMatcher.matches("*mytest", "mytest"));
        assertTrue(GlobMatcher.matches("*mytest", "someprefixmytest"));
    }

    @Test
    public void suffixPlaceHolders() {
        assertTrue(GlobMatcher.matches("mytest*", "mytest"));
        assertTrue(GlobMatcher.matches("mytest*", "mytestsuffix"));
    }

    @Test
    public void midPlaceHolders() {
        assertTrue(GlobMatcher.matches("my*est", "my test"));
    }

    @Test
    public void placeHolders() {
        assertTrue(GlobMatcher.matches("*my*est*", "some of my tests"));
    }

    @Test
    public void prefixSinglePlaceHolders() {
        assertTrue(GlobMatcher.matches("?ytest", "mytest"));
    }

    @Test
    public void suffixSinglePlaceHolders() {
        assertTrue(GlobMatcher.matches("mytes?", "mytest"));
        assertFalse(GlobMatcher.matches("mytes?", "mytes"));
    }

    @Test
    public void midSinglePlaceHolders() {
        assertTrue(GlobMatcher.matches("my?est", "mytest"));
        assertFalse(GlobMatcher.matches("my?est", "myest"));
    }

    @Test
    public void singlePlaceHolders() {
        assertTrue(GlobMatcher.matches("???e??", "mytest"));
        assertFalse(GlobMatcher.matches("???e??", "mytes"));
    }

    @Test
    public void escapes() {
        assertTrue(GlobMatcher.matches("?\\?", "a?"));
        assertTrue(GlobMatcher.matches("*\\*", "abc*"));
    }

    @Test
    public void specialChars() {
        assertTrue(GlobMatcher.matches("${mytest]*[+}^|.)@(%", "${mytest]5[+}^|.)@(%"));
    }

}
