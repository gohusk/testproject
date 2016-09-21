package com.nordstrom.view;

import com.nordstrom.core.wordchecker.model.PuzzleWord;
import com.nordstrom.view.constants.DisplayConstants;

import java.util.Scanner;
import java.util.Set;

/**
 * Class controlling console output
 */
public class PuzzleUserConsole {

    public static void printWelcome() {
        System.out.println(DisplayConstants.splashMessage);
    }

    /**
     * Gets the locataion of desired dictionary file to use
     * @return
     */
    public static String getDictionaryFileLocation() {
        System.out.print(DisplayConstants.promptForDictionaryFileName);
        return getScanner().next();
    }

    /**
     * Gets the location for the desired puzzle grid to use
     * @return
     */
    public static String getGameGridLocation() {
        System.out.print(DisplayConstants.promptForGameGridFileName);
        return getScanner().next();
    }

    /**
     * Prints the results for a given set of puzzle words
     *
     * @param words
     */
    public static void printResults(Set<PuzzleWord> words) {
        System.out.println("SCAN RESULTS:");
        for (PuzzleWord word : words) {
            System.out.println(word);
        }
    }
    private static Scanner getScanner() {
        return new Scanner(System.in);
    }

    /**
     * Override toString for better output
     * @return
     */
    private String getPuzzleWordOutput(PuzzleWord word) {
        if (!word.isFound()) return "WORD: " + word.getValue() + " N - Not Found";

        return "WORD: " + word.getValue() + ", LOCATION (ROW, COLUMN): (" + word.getLocation().getKey() + ", "
                + word.getLocation().getValue() + ")" + " ORIENTATION: " + word.getOrientation();
    }
}
