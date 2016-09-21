package com.nordstrom.gameengine;

import com.nordstrom.gameengine.constants.DisplayConstants;
import com.sun.glass.ui.SystemClipboard;

import java.util.Scanner;

/**
 * Created by plavelle on 9/20/2016.
 */
public class WordCheckerConsole {

   static Scanner scanner = new Scanner(System.in);

    public static void printWelcome() {
        System.out.println(DisplayConstants.splashMessage);
    }

    public static String getDictionaryFileLocation() {
        System.out.print(DisplayConstants.promptForDictionaryFileName);
        return scanner.next();
    }

    public static String getGameGridLocation() {
        System.out.println(DisplayConstants.promptForGameGridFileName);
        return scanner.next();
    }
}
