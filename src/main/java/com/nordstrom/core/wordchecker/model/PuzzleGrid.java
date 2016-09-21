package com.nordstrom.core.wordchecker.model;

import com.nordstrom.core.wordchecker.model.exceptions.IllegalRowAdditionException;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

import static com.nordstrom.utils.PuzzleUtils.reverseCharArray;
import static com.nordstrom.utils.PuzzleUtils.reverseString;

/**
 * Represents the puzzle grid for the word find.
 */
public class PuzzleGrid {

    //Orientations for the word (down,  up, left to right, right to left
    public enum Orientation {
        D, U, LR, RL;
    }

    private List<char[]> grid = new ArrayList<>();

    public PuzzleGrid() {
        super();
    }

    /**
     * Returns current dimensions of the grid.
     *
     * @return Pair<Integer, Integer>
     */
    public Pair<Integer, Integer> getGridDimensions() {
        if (grid.size() == 0) return new Pair<Integer, Integer>(0, 0);
        return new Pair<Integer, Integer>(grid.size(), grid.get(0).length);
    }

    /**
     * Adds a new row to the puzzle grid appending it to the bottom
     *
     * @param row char[]
     */
    public void appendNewRow(char[] row) {
        if (grid.size() > 0 && grid.get(0).length != row.length) {
            throw new IllegalRowAdditionException("Illegal row addition. Attempting to add row of length "
                    + row.length + " when length of current grid is " + grid.get(0).length);
        }
        grid.add(row);
    }

    /**
     * Returns the character array for the row or column with the given index. If a horizontal
     * orientation is supplied then the index represents the row number while the vertical orientation
     * represents the column number
     *
     * @param index
     * @param orientation
     * @return
     */
    public char[] getLine(int index, Orientation orientation) {
        switch (orientation) {
            case D:
                return getColumn(index, orientation);
            case U:
                return getColumn(index, orientation);
            case LR:
                return getRow(index, orientation);
            case RL:
                return getRow(index, orientation);
        }

        throw new RuntimeException("Undefined orientation provided: " + orientation);
    }

    /**
     * Returns a horizontal row of characters
     *
     * @param index
     * @param orientation
     * @return
     */
    private char[] getRow(int index, Orientation orientation) {
        char[] retval = grid.get(index);
        return orientation == Orientation.RL ? reverseCharArray(retval) : retval;
    }

    /**
     * Returns a column with the specified orientation (up dowwn or down up)
     *
     * @param index
     * @param orientation
     * @return
     */
    private char[] getColumn(int index, Orientation orientation) {
        char[] retval = new char[grid.size()];

        for (int i = 0; i < grid.size(); i++) {
            retval[i] = grid.get(i)[index];
        }
        return orientation == Orientation.U ? reverseCharArray(retval) : retval;
    }

    /**
     * Searches for an individual word over the entire grid
     * @param word
     */
    public void findWordInGrid(PuzzleWord word) {
        for (int i = 0; i < grid.size(); i++ ) {
            findWordInRow(word, i);
            if (word.isFound()) return;
        }

        for (int i = 0; i < grid.get(0).length; i++) {
            findWordInColumn(word, i);
            if (word.isFound()) return;
        }

    }
    /**
     * Finds a word in a row with the given index.
     *
     * @param word PuzzleWord to find
     * @param rowIndex int row index to check
     */
    public void findWordInRow(PuzzleWord word, int rowIndex) {
        String rowAsString = new String(getLine(rowIndex, Orientation.LR));
        findWordInString(word, rowAsString, rowIndex, Orientation.LR);
        String rowAsStringReversed = new String(reverseCharArray(rowAsString.toCharArray()));
        findWordInString(word, rowAsStringReversed, rowIndex, Orientation.RL);
    }

    /**
     * Finds a row in a column with the specified orientation (up/down or down/up)
     *
     * @param word PuzzleWord to find
     * @param columnIndex index of the column to search
     */
    public void findWordInColumn(PuzzleWord word, int columnIndex) {
        String columnString = new String(getLine(columnIndex, Orientation.D));
        findWordInString(word, columnString, columnIndex, Orientation.D);
        String columAsStringReversed = reverseString(columnString);
        findWordInString(word, columAsStringReversed, columnIndex, Orientation.U);
    }

    /**
     * Method will find a word in a string using string utilities
     *
     * @param word PuzzleWord to find
     * @param stringToSearch String to search for the word in
     * @param puzzleIndex int row or column index to search
     * @param orientation Orientation of the search (up/down, left/right, ...)
     */
    private void findWordInString(PuzzleWord word, String stringToSearch, int puzzleIndex, Orientation orientation) {
        int index = stringToSearch.indexOf(word.getValue());
        if (index >= 0) {
            switch (orientation) {
                case LR:
                    word.setLocation(new Pair<>(puzzleIndex, index));
                    word.setOrientation(Orientation.LR);
                    break;
                case RL:
                    word.setLocation(new Pair<>(puzzleIndex, stringToSearch.length() - index - 1));
                    word.setOrientation(Orientation.RL);
                    break;

                case D:
                    word.setLocation(new Pair<>(index, puzzleIndex));
                    word.setOrientation(Orientation.D);
                    break;
                case U:
                    word.setLocation(new Pair<>(stringToSearch.length() - index - 1, puzzleIndex));
                    word.setOrientation(Orientation.U);
                    break;
                default:
                    break;
            }
        }
    }
}