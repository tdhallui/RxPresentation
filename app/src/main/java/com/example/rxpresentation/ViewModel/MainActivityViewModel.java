package com.example.rxpresentation.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.rxpresentation.Model.UserModel;
import com.example.rxpresentation.Repository.FirstRepository;

import java.util.List;

public class MainActivityViewModel extends ViewModel {
    private final FirstRepository firstRepository;

    public MainActivityViewModel() {
        this.firstRepository = new FirstRepository();
    }

    public LiveData<List<UserModel>> getUserModel() {
        return firstRepository.getUserModelLiveData();
    }

    public LiveData<List<UserModel>> getModelByUser() {
        return firstRepository.getModelByUserLiveData();
    }
}
