package com.nordstrom.utils;

/**
 *Simple utils for the puzzle suite
 */
public class PuzzleUtils {

    public static char[] reverseCharArray(char[] arrayToReverse) {
        return new StringBuilder(new String(arrayToReverse)).reverse().toString().toCharArray();
    }

    public static String reverseString(String stringToReverse) {
        return new StringBuilder(stringToReverse).reverse().toString();
    }
}
