package com.nordstrom.gameengine;

import com.nordstrom.gameengine.constants.DisplayConstants;
import com.sun.glass.ui.SystemClipboard;

import java.util.Scanner;

/**
 * Created by plavelle on 9/20/2016.
 */
public class WordCheckerConsole {

    public static void printWelcome() {
        System.out.println(DisplayConstants.splashMessage);
    }

    public static String getDictionaryFileLocation() {
        System.out.print(DisplayConstants.promptForDictionaryFileName);
        return getScanner().next();
    }

    public static String getGameGridLocation() {
        System.out.println(DisplayConstants.promptForGameGridFileName);
        return getScanner().next();
    }

    private static Scanner getScanner() {
        return new Scanner(System.in);
    }
}
