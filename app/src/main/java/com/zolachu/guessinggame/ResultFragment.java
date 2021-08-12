package com.zolachu.guessinggame;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zolachu.guessinggame.databinding.FragmentResultBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ResultFragment} factory method to
 * create an instance of this fragment.
 */
public class ResultFragment extends Fragment {

    FragmentResultBinding binding;

    ResultViewModel resultViewModel;
    ResultViewModelFactory resultViewModelFactory;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentResultBinding.inflate(inflater, container, false);

        View view = binding.getRoot();
        String result = (ResultFragmentArgs.fromBundle(requireArguments()).getResult());

        resultViewModelFactory = new ResultViewModelFactory(result);
        resultViewModel = new ViewModelProvider(this, resultViewModelFactory).get(ResultViewModel.class);
        binding.wonLost.setText(resultViewModel.getResult());


        binding.start.setOnClickListener(v -> {

            Navigation.findNavController(view).navigate(R.id.action_resultFragment_to_gameFragment);
                });


        return view;
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }
}