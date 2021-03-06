package com.zolachu.guessinggame;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
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

    ResultViewModel resultViewModel;
    ResultViewModelFactory resultViewModelFactory;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentResultBinding.inflate(inflater, container, false);

        View view = binding.getRoot();
        String result = (ResultFragmentArgs.fromBundle(requireArguments()).getResult());

        resultViewModelFactory = new ResultViewModelFactory(result);
        resultViewModel = new ViewModelProvider(this, resultViewModelFactory).get(ResultViewModel.class);

        binding.setResultViewModel(resultViewModel);

        binding.start.setOnClickListener(v ->
            Navigation.findNavController(view).navigate(R.id.action_resultFragment_to_gameFragment));


        return view;
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }
}