package com.zolachu.guessinggame;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.zolachu.guessinggame.databinding.FragmentGameBinding;


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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentGameBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        viewModel = new ViewModelProvider(this).get(GameViewModel.class);
        viewModel.init();

        binding.setGameViewModel(viewModel);
        binding.setLifecycleOwner(getViewLifecycleOwner());

        viewModel.getGameOver().observe(getViewLifecycleOwner(), gameOver -> {
            if (gameOver) {
                GameFragmentDirections.ActionGameFragmentToResultFragment action =
                        GameFragmentDirections.actionGameFragmentToResultFragment(viewModel.wonLostMessage());

                Navigation.findNavController(view).navigate(action);
            }
        });

        guessButton = binding.guessButton;
        guessButton.setOnClickListener(v -> {

            String guess = binding.guess.getText().toString().toUpperCase();
            viewModel.makeGuess(guess);
            binding.guess.setText("");
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }


}