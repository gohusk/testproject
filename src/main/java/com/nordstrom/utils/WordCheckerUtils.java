package com.nordstrom.utils;

import com.nordstrom.core.wordchecker.dto.LetterGrid;

/**
 * Created by plavelle on 9/20/2016.
 */
public class WordCheckerUtils {

    public static char[] reverseCharArray(char[] arrayToReverse) {
        return new StringBuilder(new String(arrayToReverse)).reverse().toString().toCharArray();
    }

    public static String reverseString(String stringToReverse) {
        return new StringBuilder(stringToReverse).reverse().toString();
    }


    public static boolean areGridsTheSame(LetterGrid grid_1, LetterGrid grid_2) {
        if (grid_1.getGrid().size() != grid_2.getGrid().size())     return false;

        for (int i = 0; i < grid_1.getGrid().size(); i++) {
            if (!grid_1.getGrid().get(i).equals(grid_2.getGrid().get(i)))   {
                return false;
            }
        }
        return true;
    }
}
