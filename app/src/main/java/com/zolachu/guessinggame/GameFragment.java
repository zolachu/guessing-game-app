package com.zolachu.guessinggame;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


import com.google.android.material.navigation.NavigationView;
import com.zolachu.guessinggame.databinding.FragmentGameBinding;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GameFragment} factory method to
 * create an instance of this fragment.
 */
public class GameFragment extends Fragment {

    String[] words = {"Activity", "Android", "Fragment"};
    String secretWord = "";
    String secretWordDisplay = "";
    String correctGuesses = "";
    String incorrectGuesses = "";
    int livesLeft = 8;
    Button guessButton;
    FragmentGameBinding binding = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentGameBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        Random rand = new Random();
        int randInt = rand.nextInt(words.length);
        secretWord = words[randInt].toUpperCase();

        secretWordDisplay = deriveSecretWordDisplay();
        updateScreen();

        guessButton = binding.guessButton;
        guessButton.setOnClickListener(v -> {

            String guess = binding.guess.getText().toString().toUpperCase();
            makeGuess(guess);
            binding.guess.setText("");
            updateScreen();

            if (isLost() || isWon()) {
                GameFragmentDirections.ActionGameFragmentToResultFragment action =
                        GameFragmentDirections.actionGameFragmentToResultFragment(wonLostMessage());

                Navigation.findNavController(view).navigate(action);
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }

    private void updateScreen() {
        binding.word.setText(secretWordDisplay);
        binding.lives.setText("You have " + livesLeft + " lives left");
        binding.incorrectGuesses.setText("Incorrect Guesses:  " + incorrectGuesses);
    }

    private String deriveSecretWordDisplay() {
        String display = "";
        for (int i = 0; i < secretWord.length(); i++) {
            display += checkLetter(secretWord.charAt(i));
        }
        return display;
    }

    private void makeGuess(String guess) {
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

    private char checkLetter(char c) {
        if (correctGuesses.contains(String.valueOf(c)) ) {
            return c;
        }
        return '_';
    }

    private boolean isWon() {
        return secretWord.equalsIgnoreCase(secretWordDisplay);
    }

    private boolean isLost() {
        return livesLeft == 0;
    }

    private String wonLostMessage() {
        String message = "";
        if (isWon()) {
            message += "You won! ";
        } else if (isLost()) {
            message += "You lost! ";
        }
        message +="The word secret was " + secretWord;
        return message;
    }


}