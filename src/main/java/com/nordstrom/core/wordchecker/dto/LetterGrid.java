package com.nordstrom.core.wordchecker.dto;

import com.nordstrom.core.wordchecker.dto.exceptions.IllegalRowAdditionException;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

import static com.nordstrom.utils.WordCheckerUtils.reverseCharArray;
import static com.nordstrom.utils.WordCheckerUtils.reverseString;

/**
 * Created by plavelle on 9/18/2016.
 */
public class LetterGrid {

    public enum Orientation {
        VERTICAL_UP_DOWN("D"),
        VERTICAL_DOWN_UP("U"),
        HORIZONTAL_LEFT_RIGHT("LR"),
        HORIZONTAL_RIGHT_LEFT("RL");

        private String displayText;

        Orientation(String displayText) {
            this.displayText = displayText;
        }

        public String getDisplayText() {
            return displayText;
        }
    }

    private List<char[]> grid = new ArrayList<>();
    private List<GameWord> wordsToFind;

    public LetterGrid() {
    }

    public Pair<Integer, Integer> getGridDimensions() {
        if (grid.size() == 0)   return new Pair<Integer, Integer>(0, 0);
        return new Pair<Integer, Integer>(grid.size(), grid.get(0).length);
    }

    public void appendNewRow(char[] row) {
        if (grid.size() > 0 && grid.get(0).length != row.length) {
            throw new IllegalRowAdditionException("Illegal row addition. Attempting to add row of length "
            + row.length + " when length of current grid is " + grid.get(0).length);
        }
        grid.add(row);
    }

    public void appendNewRow(String s) {
        grid.add(s.toCharArray());
    }

    public char[] getLine(int index, Orientation orientation) {
        switch(orientation)  {
            case VERTICAL_UP_DOWN:
                return getVerticalLine(index, orientation);
            case VERTICAL_DOWN_UP:
                return getVerticalLine(index, orientation);
            case HORIZONTAL_LEFT_RIGHT:
                return getHorizontalLine(index, orientation);
            case HORIZONTAL_RIGHT_LEFT:
                return getHorizontalLine(index, orientation);
        }

        throw new RuntimeException("Undefined orientation provided: " + orientation);
    }

    private char[] getHorizontalLine(int index, Orientation orientation) {
        char[] retval = grid.get(index);
        return orientation == Orientation.HORIZONTAL_RIGHT_LEFT?  reverseCharArray(retval): retval;
    }

    private char[] getVerticalLine(int index, Orientation orientation) {
        char[] retval = new char[grid.size()];

        for (int i = 0; i < grid.size(); i++) {
            retval[i] = grid.get(i)[index];
        }
        return orientation == Orientation.VERTICAL_DOWN_UP ?  reverseCharArray(retval): retval;
    }


    public List<char[]> getGrid() {
        return grid;
    }
    public void findWordInstances(String word) {

    }

    public void findWordInRow(GameWord word, int rowIndex) {
        String rowAsString = new String(getLine(rowIndex, Orientation.HORIZONTAL_LEFT_RIGHT));
        findWordInString(word, rowAsString, rowIndex, Orientation.HORIZONTAL_LEFT_RIGHT);
        String rowAsStringReversed = new String(reverseCharArray(rowAsString.toCharArray()));
        findWordInString(word, rowAsStringReversed, rowIndex, Orientation.HORIZONTAL_RIGHT_LEFT);
    }

    public void findWordInColumn(GameWord word, int columnIndex) {
        String columnString = new String(getLine(columnIndex, Orientation.VERTICAL_UP_DOWN));
        findWordInString(word, columnString, columnIndex, Orientation.VERTICAL_UP_DOWN);
        String columAsStringReversed = reverseString(columnString);
        findWordInString(word, columAsStringReversed, columnIndex, Orientation.VERTICAL_DOWN_UP);
    }

    private void findWordInString(GameWord word, String stringToSearch, int rowIndex, Orientation orientation) {
        int index;
        while ((index = stringToSearch.indexOf(word.getValue())) >= 0) {
            word.addLocation(new GameWord.WordLocation(rowIndex, index, orientation));
            stringToSearch = stringToSearch.substring(index + word.getValue().length());
        }
    }
}
