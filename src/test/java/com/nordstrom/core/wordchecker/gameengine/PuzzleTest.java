package com.nordstrom.core.wordchecker.gameengine;

import com.nordstrom.controller.Puzzle;
import javafx.util.Pair;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Test suite for the word checker game class
 */
public class PuzzleTest {

    @Rule
    public TextFromStandardInputStream systemInMock = TextFromStandardInputStream.emptyStandardInputStream();

    //Test that Puzzle can read game grid
    @Test
    public void shouldReadGameGrid() throws IOException {
        Puzzle game = new Puzzle();
        systemInMock.provideLines("./src/test/resources/gridtestdata.txt");
        game.loadGameGrid();
        Assert.assertTrue(game.getGridDimensions().equals(new Pair<Integer, Integer>(5, 5)));
    }

    //Test that puzzle can read dictionary
    @Test
    public void shouldReadDictionary() throws IOException {
        Puzzle game = new Puzzle();
        systemInMock.provideLines("./src/test/resources/library.txt");
        game.loadGameLibrary();
        Assert.assertTrue(game.getWordLibrary().size() == 3);
    }
}
