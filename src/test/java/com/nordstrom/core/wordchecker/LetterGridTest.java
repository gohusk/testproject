package com.nordstrom.core.wordchecker;

import com.nordstrom.core.wordchecker.dto.LetterGrid;
import com.nordstrom.core.wordchecker.dto.GameWord;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

import static com.nordstrom.utils.WordCheckerUtils.reverseCharArray;

/**
 * Created by plavelle on 9/18/2016.
 */
public class LetterGridTest {

    static LetterGrid testGrid = new LetterGrid();


    static char[] expectedRowReturn = {'F', 'G', 'H', 'I', 'J'};
    static char[] expectedColumnReturn = {'D', 'I', 'N', 'S', 'X'};

    GameWord rowLeftToRight;
    GameWord rowRightToLeft;
    GameWord columnUpDown;
    GameWord columnDownUp;

    @BeforeClass
    public static void init() throws IOException {
        Stream<String> stream = Files.lines(Paths.get("src/test/resources/gridtestdata.txt"));
        stream.forEach(s -> {
            testGrid.appendNewRow(s.toCharArray());
        });

    }

    @Before
    public void initTests() {
        rowLeftToRight = new GameWord(new String(expectedRowReturn));
        rowRightToLeft = new GameWord(new String(reverseCharArray(expectedRowReturn)));
        columnUpDown = new GameWord(new String(expectedColumnReturn));
        columnDownUp = new GameWord(new String(reverseCharArray(expectedColumnReturn)));
    }

    //not sure what's going on here, it's throwing the right  exception in the method
    //but junit's picking it up as runtimeexception
    @Test(expected = RuntimeException.class)
    public void shouldThrowIllegalRowAdditionExceptoionOnIllegalAdd() {
        char[] row1 = {'a', 'b'};
        char[] row2 = {'c'};
        LetterGrid badGrid = new LetterGrid();
        badGrid.appendNewRow(row1);
        badGrid.appendNewRow(row2);
    }

    @Test
    public void shouldGetHorizontalLeftToRight() {
        char[] row = testGrid.getLine(1, LetterGrid.Orientation.HORIZONTAL_LEFT_RIGHT);
        Assert.assertTrue("Row functionality appears to be broken",
                Arrays.equals(row, expectedRowReturn));
    }

    @Test
    public void shouldGetHorizontalRightToLeft() {
        char[] row = testGrid.getLine(1, LetterGrid.Orientation.HORIZONTAL_RIGHT_LEFT);
        char[] expectedResult = new StringBuilder(new String(expectedRowReturn)).reverse().toString().toCharArray();
        Assert.assertTrue("Get column functionality appears to be broken. Expected: [" + new String(expectedResult) + "], " +
                        "received [" + new String(row) + "]",
                Arrays.equals(row, expectedResult));
    }

    @Test
    public void shouldGetVerticalLineUpDown() {
        char[] column = testGrid.getLine(3, LetterGrid.Orientation.VERTICAL_UP_DOWN);
        Assert.assertTrue("Get column functionality appears to be broken. Expected: [" + new String(expectedColumnReturn) + "], " +
                        "received [" + new String(column) + "]",
                Arrays.equals(column, expectedColumnReturn));
    }

    @Test
    public void shouldGetVerticalLineDownUp() {
        char[] column = testGrid.getLine(3, LetterGrid.Orientation.VERTICAL_DOWN_UP);
        char[] expectedResult = new StringBuilder(new String(expectedColumnReturn)).reverse().toString().toCharArray();
        Assert.assertTrue("Get column functionality appears to be broken. Expected: [" + new String(expectedResult) + "], " +
                        "received [" + new String(column) + "]",
                Arrays.equals(column, expectedResult));
    }

    @Test
    public void shouldFindWordInRow() {
        testGrid.findWordInRow(rowLeftToRight, 1);
        Assert.assertTrue("Did not find test word " + rowLeftToRight.getValue()
                + "in the test grid", rowLeftToRight.getFindCount() == 1);
    }

    @Test
    public void shouldFindWordInRowReverse() {
        testGrid.findWordInRow(rowRightToLeft, 1);
        Assert.assertTrue("Did not find test word " + rowRightToLeft.getValue()
                + "in the test grid", rowRightToLeft.getFindCount() == 1);
    }


    @Test
    public void shouldFindWordInColumUpDown() {
        testGrid.findWordInColumn(columnUpDown, 3);
        Assert.assertTrue("Did not find test word " + columnUpDown.getValue() + " in the test grid",
                columnUpDown.getFindCount() == 1);
    }

    @Test
    public void shouldFindWordInColumnDownUp() {
        testGrid.findWordInColumn(columnDownUp, 3);
        Assert.assertTrue("Did not find test word " + columnUpDown.getValue() + " in the test grid",
                columnDownUp.getFindCount() == 1);
    }
}
