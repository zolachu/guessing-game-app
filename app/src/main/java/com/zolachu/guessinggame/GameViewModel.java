package com.zolachu.guessinggame;

import android.widget.Button;

import androidx.lifecycle.ViewModel;

import java.util.Random;

public class GameViewModel extends ViewModel {
    String[] words = {"Activity", "Android", "Fragment"};
    String secretWord = "";
    String secretWordDisplay = "";
    String correctGuesses = "";
    String incorrectGuesses = "";
    int livesLeft = 8;
    Button guessButton;

    public void init() {
        Random rand = new Random();
        int randInt = rand.nextInt(words.length);
        secretWord = words[randInt].toUpperCase();

        secretWordDisplay = deriveSecretWordDisplay();
    }

    public String deriveSecretWordDisplay() {
        String display = "";
        for (int i = 0; i < secretWord.length(); i++) {
            display += checkLetter(secretWord.charAt(i));
        }
        return display;
    }

    private char checkLetter(char c) {
        if (correctGuesses.contains(String.valueOf(c)) ) {
            return c;
        }
        return '_';
    }

    public boolean isWon() {
        return secretWord.equalsIgnoreCase(secretWordDisplay);
    }

    public boolean isLost() {
        return livesLeft == 0;
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
                correctGuesses += guess;
                secretWordDisplay = deriveSecretWordDisplay();
            } else {
                incorrectGuesses += guess + " ";
                livesLeft--;
            }

        }
    }

}
