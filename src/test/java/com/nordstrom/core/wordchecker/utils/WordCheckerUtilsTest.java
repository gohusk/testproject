package com.nordstrom.core.wordchecker.utils;

import com.nordstrom.utils.WordCheckerUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by plavelle on 9/20/2016.
 */
public class WordCheckerUtilsTest {

    static String testString = "abcde";
    static String testStringReversed = "edcba";
    static char[] testCharArray = testStringReversed.toCharArray();

    @Test
    public void shouldReverseString() {
        Assert.assertTrue(WordCheckerUtils.reverseString(testString).equals(testStringReversed));
    }

    @Test
    public void shouldReverseCharArray() {
        Assert.assertTrue(new String(WordCheckerUtils.reverseCharArray(testCharArray)).equals(testString));
    }
}
