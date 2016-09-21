package com.nordstrom.core.wordchecker.performance;

import com.nordstrom.controller.Puzzle;
import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import java.io.IOException;

/**
 * This is real basic. Just want to get some basic numbers on it. Would use other tool if this were professional project
 * */
public class BasicPerformanceTest {

    @Rule
    public TextFromStandardInputStream systemInMock = TextFromStandardInputStream.emptyStandardInputStream();

    //Test that Puzzle can read game grid
    @Test
    public void runLoad() throws IOException {
        System.out.println("BASIC PERFORMANCE NUMBERS");
        for (int i = 0; i < 10; i++){
            long start = System.currentTimeMillis();
            Puzzle game = new Puzzle();
            systemInMock.provideLines("./src/test/resources/gridtestdata.txt");
            game.loadGameGrid();
            systemInMock.provideLines("./src/test/resources/library.txt");
            game.loadGameLibrary();
            game.searchForWords();
            System.out.println("\nRUN " + i + ": PERFORMED IN " + (System.currentTimeMillis() - start) + "ms\n");
        }

    }
}
