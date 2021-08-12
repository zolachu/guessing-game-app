package com.zolachu.guessinggame;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Random;

public class GameViewModel extends ViewModel {
    String[] words = {"Activity", "Android", "Fragment"};
    private String secretWord = "";
    private MutableLiveData<String> _secretWordDisplay = new MutableLiveData<>();
    private String _correctGuesses = "";
    private MutableLiveData<String> _incorrectGuesses = new MutableLiveData<>("");
    private MutableLiveData<Integer> _livesLeft = new MutableLiveData<>(8);
    private MutableLiveData<Boolean> _gameOver = new MutableLiveData<>(false);

    public MutableLiveData<String> getSecretWordDisplay() {
        return _secretWordDisplay;
    }

    public MutableLiveData<String> getIncorrectGuesses() {
        return _incorrectGuesses;
    }

    public MutableLiveData<Integer> getLivesLeft() {
        return _livesLeft;
    }

    public void init() {
        Random rand = new Random();
        int randInt = rand.nextInt(words.length);
        secretWord = (words[randInt].toUpperCase());
        _secretWordDisplay.setValue(deriveSecretWordDisplay());
        _livesLeft.setValue(8);
    }

    private String deriveSecretWordDisplay() {
        StringBuilder display = new StringBuilder();
        for (int i = 0; i < secretWord.length(); i++) {
            display.append(checkLetter(secretWord.charAt(i)));
        }
        return display.toString();
    }

    private char checkLetter(char c) {
        if (_correctGuesses.contains(String.valueOf(c)) ) {
            return c;
        }
        return '_';
    }


    private boolean isWon() {
        return secretWord.equalsIgnoreCase(_secretWordDisplay.getValue());
    }

    private boolean isLost() {
        return (_livesLeft.getValue() == null) || (_livesLeft.getValue() == 0);
    }

    public MutableLiveData<Boolean> getGameOver() {
        return _gameOver;
    }
    public String wonLostMessage() {
        String message = "";
        if (isWon()) {
            message += "You won! ";
        } else if (isLost()) {
            message += "You lost! ";
        }
        message +="The word secret was " + secretWord;
        return message;
    }

    public void makeGuess(String guess) {
        if (guess.length() == 1) {
            if (secretWord.contains(guess)) {
                _correctGuesses += guess;
                _secretWordDisplay.setValue(deriveSecretWordDisplay());
            } else {
                _incorrectGuesses.setValue(_incorrectGuesses.getValue() + guess + " ");
                if (_livesLeft.getValue() != null) {
                    _livesLeft.setValue(_livesLeft.getValue() - 1);
                }
            }

            if (isLost() || isWon()) {
                _gameOver.setValue(true);
            }

        }
    }

}
