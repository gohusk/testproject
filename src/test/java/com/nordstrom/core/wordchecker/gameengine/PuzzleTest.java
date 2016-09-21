package com.nordstrom.core.wordchecker.gameengine;

import com.nordstrom.gameengine.Puzzle;
import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import java.io.IOException;

/**
 * Test suite for the word checker game class
 */
public class PuzzleTest {

    @Rule
    public TextFromStandardInputStream systemInMock = TextFromStandardInputStream.emptyStandardInputStream();

    @Test
    public void shouldReadGameGrid() throws IOException {
        Puzzle game = new Puzzle();
        systemInMock.provideLines("./src/test/resources/gridtestdata.txt");
        game.loadGameGrid();
        Assert.assertTrue(game.getGridDimensions().equals(new Pair<Integer, Integer>(5, 5)));
    }

    @Test
    public void shouldReadDictionary() throws IOException {
        Puzzle game = new Puzzle();
        systemInMock.provideLines("./src/test/resources/library.txt");
        game.loadGameLibrary();
        Assert.assertTrue(game.getWordLibrary().size() == 3);
    }
}
