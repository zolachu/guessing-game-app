package com.zolachu.guessinggame;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
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

    Button guessButton;
    FragmentGameBinding binding = null;

    GameViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentGameBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        viewModel = new ViewModelProvider(this).get(GameViewModel.class);
        viewModel.init();


        updateScreen();

        guessButton = binding.guessButton;
        guessButton.setOnClickListener(v -> {

            String guess = binding.guess.getText().toString().toUpperCase();
            viewModel.makeGuess(guess);
            binding.guess.setText("");
            updateScreen();

            if (viewModel.isLost() || viewModel.isWon()) {
                GameFragmentDirections.ActionGameFragmentToResultFragment action =
                        GameFragmentDirections.actionGameFragmentToResultFragment(viewModel.wonLostMessage());

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
        binding.word.setText(viewModel.secretWordDisplay);
        binding.lives.setText("You have " + viewModel.livesLeft + " lives left");
        binding.incorrectGuesses.setText("Incorrect Guesses:  " + viewModel.incorrectGuesses);
    }




}