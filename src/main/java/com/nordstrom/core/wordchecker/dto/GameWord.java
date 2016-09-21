package com.nordstrom.core.wordchecker.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by plavelle on 9/19/2016.
 */
public class GameWord {

    private String value;
    private List<WordLocation> locations = new ArrayList<>();

    public GameWord(String value) {
        this.value = value.toUpperCase();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getFindCount() {
        return locations.size();
    }

    public void addLocation(WordLocation wordLocation) {
        locations.add(wordLocation);
    }

    public static class WordLocation {
        public int x;
        public int y;
        public LetterGrid.Orientation orientation;

        public WordLocation(int x, int y, LetterGrid.Orientation orientation) {
            this.x = x;
            this.y = y;
            this.orientation = orientation;
        }
    }
}
