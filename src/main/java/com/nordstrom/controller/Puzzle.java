package com.nordstrom.controller;

import com.nordstrom.core.wordchecker.model.PuzzleGrid;
import com.nordstrom.core.wordchecker.model.PuzzleWord;
import com.nordstrom.view.PuzzleUserConsole;
import javafx.util.Pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Main class for the word finding puzzle tool.
 *
 * */
public class Puzzle {

    private PuzzleGrid grid = new PuzzleGrid();
    private Set<PuzzleWord> wordLibrary = new HashSet<>();


    public static void main(String[] args) throws IOException {
        Puzzle game = new Puzzle();
        game.loadGameGrid();
        game.loadGameLibrary();
        game.searchForWords();
        PuzzleUserConsole.printResults(game.getWordLibrary());
    }

    public void searchForWords() {
        for (PuzzleWord word : wordLibrary) {
            for (int i = 0; i < grid.getGridDimensions().getKey(); i++) {
                grid.findWordInRow(word, i);
            }
        }

        for (PuzzleWord word : wordLibrary) {
            for (int i = 0; i < grid.getGridDimensions().getValue(); i++) {
                grid.findWordInColumn(word, i);
            }
        }
    }
    /**
     * Returns the grid dimensions. Primarily for testability.
     *
     * @return Pair<Integer, Integer>
     */
    public Pair<Integer, Integer> getGridDimensions() {
        return grid.getGridDimensions();
    }

    public Set<PuzzleWord> getWordLibrary() {
        return wordLibrary;
    }

    public void loadGameLibrary() throws IOException {
        readWordsToFind(PuzzleUserConsole.getDictionaryFileLocation());
    }

    private void readWordsToFind(String dictionaryFileLocation) throws IOException {
        Stream<String> stream = Files.lines(Paths.get(dictionaryFileLocation));
        stream.forEach(s -> {
            wordLibrary.add(new PuzzleWord(s.replaceAll("\\s+","")));
        });
    }

    public void loadGameGrid() throws IOException {
        readGridFromFile(PuzzleUserConsole.getGameGridLocation());
    }

    /**
     * Simple method that reads
     *
     * @throws IOException
     */
    private void readGridFromFile(String fileLocation) throws IOException {
        Stream<String> stream = Files.lines(Paths.get(fileLocation));
        stream.forEach(s -> {
            grid.appendNewRow(s.toCharArray());
        });
    }
}
