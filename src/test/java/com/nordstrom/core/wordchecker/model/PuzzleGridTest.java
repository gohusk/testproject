package com.nordstrom.core.wordchecker.model;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

import static com.nordstrom.utils.PuzzleUtils.reverseCharArray;

/**
 * Created by plavelle on 9/18/2016.
 */
@RunWith(DataProviderRunner.class)
public class PuzzleGridTest {

    static PuzzleGrid testGrid = new PuzzleGrid();


    static char[] expectedRowReturn = {'F', 'G', 'H', 'I', 'J'};
    static char[] expectedColumnReturn = {'D', 'I', 'N', 'S', 'X'};

    PuzzleWord rowLeftToRight;
    PuzzleWord rowRightToLeft;
    PuzzleWord columnUpDown;
    PuzzleWord columnDownUp;

    @DataProvider
    public static Object[][] searchPresentWords() {
        return new Object[][]{
                {new PuzzleWord("AFK"), new Pair<Integer, Integer>(0, 0), PuzzleGrid.Orientation.D, true},
                {new PuzzleWord("YTO"), new Pair<Integer, Integer>(4, 4), PuzzleGrid.Orientation.U, true},
                {new PuzzleWord("FGH"), new Pair<Integer, Integer>(1, 0), PuzzleGrid.Orientation.LR, true},
                {new PuzzleWord("NML"), new Pair<Integer, Integer>(2, 3), PuzzleGrid.Orientation.RL, true},
                {new PuzzleWord("HM"), new Pair<Integer, Integer>(1, 2), PuzzleGrid.Orientation.D, true},
                {new PuzzleWord("NML"), new Pair<Integer, Integer>(2, 3), PuzzleGrid.Orientation.RL, true},
                {new PuzzleWord("A"), new Pair<Integer, Integer>(0, 0), PuzzleGrid.Orientation.LR, true},
                {new PuzzleWord("A"), new Pair<Integer, Integer>(0, 0), PuzzleGrid.Orientation.LR, true},
                {new PuzzleWord("M"), new Pair<Integer, Integer>(2, 2), PuzzleGrid.Orientation.LR, true},
                {new PuzzleWord("Y"), new Pair<Integer, Integer>(4, 4), PuzzleGrid.Orientation.LR, true},
                {new PuzzleWord("AC"), new Pair<Integer, Integer>(-1, -1), null, false},
                {new PuzzleWord("Z"), new Pair<Integer, Integer>(-1, -1), null, false}
        };
    }

    @DataProvider
    public static Object[][] searchMissingWords() {
        return new Object[][]{
                {new PuzzleWord("AC"), new Pair<Integer, Integer>(-1, -1), false},
                {new PuzzleWord("Z"), new Pair<Integer, Integer>(-1, -1), false},
                {new PuzzleWord("AC"), new Pair<Integer, Integer>(-1, -1), false},
        };
    }

    @BeforeClass
    public static void init() throws IOException {
        Stream<String> stream = Files.lines(Paths.get("src/test/resources/gridtestdata.txt"));
        stream.forEach(s -> {
            testGrid.appendNewRow(s.toCharArray());
        });

    }

    //initialize words for row tests
    @Before
    public void initTests() {
        rowLeftToRight = new PuzzleWord(new String(expectedRowReturn));
        rowRightToLeft = new PuzzleWord(new String(reverseCharArray(expectedRowReturn)));
        columnUpDown = new PuzzleWord(new String(expectedColumnReturn));
        columnDownUp = new PuzzleWord(new String(reverseCharArray(expectedColumnReturn)));
    }

    //not sure what's going on here, it's throwing the right  exception in the method
    //but junit's picking it up as runtimeexception
    @Test(expected = RuntimeException.class)
    public void shouldThrowIllegalRowAdditionExceptoionOnIllegalAdd() {
        char[] row1 = {'a', 'b'};
        char[] row2 = {'c'};
        PuzzleGrid badGrid = new PuzzleGrid();
        badGrid.appendNewRow(row1);
        badGrid.appendNewRow(row2);
    }

    //check left to right functionality
    @Test
    public void shouldGetHorizontalLeftToRight() {
        char[] row = testGrid.getLine(1, PuzzleGrid.Orientation.LR);
        Assert.assertTrue("Row functionality appears to be broken",
                Arrays.equals(row, expectedRowReturn));
    }

    //test right to left functionality
    @Test
    public void shouldGetHorizontalRightToLeft() {
        char[] row = testGrid.getLine(1, PuzzleGrid.Orientation.RL);
        char[] expectedResult = new StringBuilder(new String(expectedRowReturn)).reverse().toString().toCharArray();
        Assert.assertTrue("Get column functionality appears to be broken. Expected: [" + new String(expectedResult) + "], " +
                        "received [" + new String(row) + "]",
                Arrays.equals(row, expectedResult));
    }

    //teset up/down functionality
    @Test
    public void shouldGetVerticalLineUpDown() {
        char[] column = testGrid.getLine(3, PuzzleGrid.Orientation.D);
        Assert.assertTrue("Get column functionality appears to be broken. Expected: [" + new String(expectedColumnReturn) + "], " +
                        "received [" + new String(column) + "]",
                Arrays.equals(column, expectedColumnReturn));
    }

    //test down/up functionality
    @Test
    public void shouldGetVerticalLineDownUp() {
        char[] column = testGrid.getLine(3, PuzzleGrid.Orientation.U);
        char[] expectedResult = new StringBuilder(new String(expectedColumnReturn)).reverse().toString().toCharArray();
        Assert.assertTrue("Get column functionality appears to be broken. Expected: [" + new String(expectedResult) + "], " +
                        "received [" + new String(column) + "]",
                Arrays.equals(column, expectedResult));
    }


    //Positive test case for findWordInGrid method
    @Test
    @UseDataProvider("searchPresentWords")
    public void shouldPerformSearches(PuzzleWord word, Pair<Integer, Integer> location, PuzzleGrid.Orientation orientation, boolean isFound) {
        testGrid.findWordInGrid(word);
        Assert.assertTrue(word.getOrientation().equals(orientation));
        Assert.assertTrue(word.getLocation().equals(location));
        Assert.assertTrue(word.isFound() == isFound);
    }

    //negative test case for findWordInGrid method
    @Test
    @UseDataProvider("searchMissingWords")
    public void shouldNotFindMissingWords(PuzzleWord word, Pair<Integer, Integer> location, boolean isFound) {
        testGrid.findWordInGrid(word);
        Assert.assertTrue(word.getOrientation() == null);
        Assert.assertTrue(word.getLocation().equals(location));
        Assert.assertTrue(word.isFound() == isFound);
    }
}
