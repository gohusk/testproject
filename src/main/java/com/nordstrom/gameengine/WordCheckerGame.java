package com.nordstrom.gameengine;

import com.nordstrom.core.wordchecker.dto.LetterGrid;
import com.nordstrom.core.wordchecker.dto.GameWord;
import com.nordstrom.utils.WordCheckerUtils;
import javafx.util.Pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Created by plavelle on 9/18/2016.
 */
public class WordCheckerGame {

    private LetterGrid grid = new LetterGrid();
    private Set<GameWord> wordLibrary = new HashSet<>();


    public static void main(String[] args) throws IOException {
        WordCheckerGame game = new WordCheckerGame();
        game.loadGameGrid();
        game.loadGameLibrary();
        game.play();
        System.out.println();
    }

    public void play() {
        for (GameWord word : wordLibrary) {
            for (int i = 0; i < grid.getGridDimensions().getKey(); i++) {
                grid.findWordInRow(word, i);
            }
        }

        for (GameWord word : wordLibrary) {
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

    public Set<GameWord> getWordLibrary() {
        return wordLibrary;
    }

    public void loadGameLibrary() throws IOException {
        readWordsToFind(WordCheckerConsole.getDictionaryFileLocation());
    }

    private void readWordsToFind(String dictionaryFileLocation) throws IOException {
        Stream<String> stream = Files.lines(Paths.get(dictionaryFileLocation));
        stream.forEach(s -> {
            wordLibrary.add(new GameWord(s.replaceAll("\\s+","")));
        });
    }

    public void loadGameGrid() throws IOException {
        readGridFromFile(WordCheckerConsole.getGameGridLocation());
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
