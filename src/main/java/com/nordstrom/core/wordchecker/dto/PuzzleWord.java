package com.nordstrom.core.wordchecker.dto;

import javafx.util.Pair;

/**
 * Class represents a word for the puzzle. Word has a string value, a int/int pair that represents
 * the location of the first character of the word on the grid, and an orientation
 */
public class PuzzleWord {

    private String value;
    private Pair<Integer, Integer> location = new Pair<>(-1, -1);
    private PuzzleGrid.Orientation orientation;

    /**
     * Constructor taking in string value
     * @param value
     */
    public PuzzleWord(String value) {
        this.value = value.toUpperCase();
    }

    /**
     * Set the location on the grid for the puzzle word
     *
     * @param location Pair<Integer, Integer>
     */
    public void setLocation(Pair<Integer, Integer> location) {
        this.location = location;
    }

    /**
     * Returns string value of the word
     *
     * @return string
     */
    public String getValue() {
        return value;
    }

    /**
     * Set orientation of the puzzle word on the grid
     * @param Orientation
     */
    public void setOrientation(PuzzleGrid.Orientation orientation) {
        this.orientation = orientation;
    }

    /**
     * Returns orientation on the grid of the given word.
     *
     * @return Orientation
     */
    public PuzzleGrid.Orientation getOrientation() {
        return orientation;
    }

    /**
     * Location of first character of word on the grid
     * @return Pair
     */
    public Pair<Integer, Integer> getLocation() {
        return location;
    }

    /**
     * Has the puzzle word been found in the grid?
     * @return boolean
     */
    public boolean isFound() {
        return location.getKey() > -1;
    }
}
