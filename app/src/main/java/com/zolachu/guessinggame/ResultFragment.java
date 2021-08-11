package com.zolachu.guessinggame;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentResultBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        binding.wonLost.setText(ResultFragmentArgs.fromBundle(requireArguments()).getResult());

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