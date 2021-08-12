package com.zolachu.guessinggame;

import androidx.lifecycle.ViewModel;

public class ResultViewModel extends ViewModel {
    private String s;

    ResultViewModel(String s) {
        this.s = s;
    }

    public String getResult() {
        return s;
    }
}
