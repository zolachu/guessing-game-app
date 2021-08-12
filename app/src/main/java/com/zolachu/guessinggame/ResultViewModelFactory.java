package com.zolachu.guessinggame;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ResultViewModelFactory implements ViewModelProvider.Factory {
    private String finalResult;

    ResultViewModelFactory(String finalResult) {
        this.finalResult = finalResult;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ResultViewModel.class)) {
            ResultViewModel resultViewModel = new ResultViewModel(finalResult);
            return (T)resultViewModel;
        }
        throw new IllegalArgumentException("Unknown ViewModel!");
    }
}
