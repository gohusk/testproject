package com.nordstrom.core.wordchecker.gameengine;

import com.nordstrom.gameengine.WordCheckerConsole;
import com.nordstrom.gameengine.WordCheckerGame;
import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import java.io.IOException;

/**
 * Created by plavelle on 9/20/2016.
 */
public class WordCheckerGameTest {

    @Rule
    public TextFromStandardInputStream systemInMock = TextFromStandardInputStream.emptyStandardInputStream();

    @Test
    public void shouldReadGameGrid() throws IOException {
        WordCheckerGame game = new WordCheckerGame();
        systemInMock.provideLines("./src/test/resources/gridtestdata.txt");
        game.loadGameGrid();
        Assert.assertTrue(game.getGridDimensions().equals(new Pair<Integer, Integer>(5, 5)));
    }

    @Test
    public void shouldReadDictionary() throws IOException {
        WordCheckerGame game = new WordCheckerGame();
        systemInMock.provideLines("./src/test/resources/library.txt");
        game.loadGameLibrary();
        Assert.assertTrue(game.getWordLibrary().size() == 3);
    }
}
