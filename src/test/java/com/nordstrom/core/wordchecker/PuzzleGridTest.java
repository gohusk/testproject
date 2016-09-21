package com.nordstrom.core.wordchecker;

import com.nordstrom.core.wordchecker.dto.PuzzleGrid;
import com.nordstrom.core.wordchecker.dto.PuzzleWord;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

import static com.nordstrom.utils.PuzzleUtils.reverseCharArray;

/**
 * Created by plavelle on 9/18/2016.
 */
public class PuzzleGridTest {

    static PuzzleGrid testGrid = new PuzzleGrid();


    static char[] expectedRowReturn = {'F', 'G', 'H', 'I', 'J'};
    static char[] expectedColumnReturn = {'D', 'I', 'N', 'S', 'X'};

    PuzzleWord rowLeftToRight;
    PuzzleWord rowRightToLeft;
    PuzzleWord columnUpDown;
    PuzzleWord columnDownUp;

    @BeforeClass
    public static void init() throws IOException {
        Stream<String> stream = Files.lines(Paths.get("src/test/resources/gridtestdata.txt"));
        stream.forEach(s -> {
            testGrid.appendNewRow(s.toCharArray());
        });

    }

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

    @Test
    public void shouldGetHorizontalLeftToRight() {
        char[] row = testGrid.getLine(1, PuzzleGrid.Orientation.LR);
        Assert.assertTrue("Row functionality appears to be broken",
                Arrays.equals(row, expectedRowReturn));
    }

    @Test
    public void shouldGetHorizontalRightToLeft() {
        char[] row = testGrid.getLine(1, PuzzleGrid.Orientation.RL);
        char[] expectedResult = new StringBuilder(new String(expectedRowReturn)).reverse().toString().toCharArray();
        Assert.assertTrue("Get column functionality appears to be broken. Expected: [" + new String(expectedResult) + "], " +
                        "received [" + new String(row) + "]",
                Arrays.equals(row, expectedResult));
    }

    @Test
    public void shouldGetVerticalLineUpDown() {
        char[] column = testGrid.getLine(3, PuzzleGrid.Orientation.D);
        Assert.assertTrue("Get column functionality appears to be broken. Expected: [" + new String(expectedColumnReturn) + "], " +
                        "received [" + new String(column) + "]",
                Arrays.equals(column, expectedColumnReturn));
    }

    @Test
    public void shouldGetVerticalLineDownUp() {
        char[] column = testGrid.getLine(3, PuzzleGrid.Orientation.U);
        char[] expectedResult = new StringBuilder(new String(expectedColumnReturn)).reverse().toString().toCharArray();
        Assert.assertTrue("Get column functionality appears to be broken. Expected: [" + new String(expectedResult) + "], " +
                        "received [" + new String(column) + "]",
                Arrays.equals(column, expectedResult));
    }

    @Test
    public void shouldFindWordInRow() {
        testGrid.findWordInRow(rowLeftToRight, 1);
        Assert.assertTrue("Did not find test word " + rowLeftToRight.getValue()
                + "in the test grid", rowLeftToRight.isFound());
    }

    @Test
    public void shouldFindWordInRowReverse() {
        testGrid.findWordInRow(rowRightToLeft, 1);
        Assert.assertTrue("Did not find test word " + rowRightToLeft.getValue()
                + "in the test grid", rowRightToLeft.isFound());
    }


    @Test
    public void shouldFindWordInColumUpDown() {
        testGrid.findWordInColumn(columnUpDown, 3);
        Assert.assertTrue("Did not find test word " + columnUpDown.getValue() + " in the test grid",
                columnUpDown.isFound());
        Assert.assertTrue("Row/Column of first letter does not appear to be correct, expected (0, 3), received (" +
                        columnUpDown.getLocation().getKey() + ", " + columnUpDown.getLocation().getValue() + ")",
                columnUpDown.getLocation().getKey() == 0 && columnUpDown.getLocation().getValue() == 3);
    }

    @Test
    public void shouldFindWordInColumnDownUp() {
        testGrid.findWordInColumn(columnDownUp, 3);
        Assert.assertTrue("Did not find test word " + columnUpDown.getValue() + " in the test grid",
                columnDownUp.isFound());
    }
}
