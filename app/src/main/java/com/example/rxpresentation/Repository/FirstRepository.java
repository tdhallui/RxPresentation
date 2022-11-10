package com.example.rxpresentation.Repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.rxpresentation.Common.Common;
import com.example.rxpresentation.Model.UserModel;
import com.example.rxpresentation.Network.APIService;

import java.util.List;
import java.util.Observable;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class FirstRepository {
    private static final String TAG = "FirstRepository";

    private APIService apiService;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public FirstRepository() {
        this.apiService = Common.getAPIService();
    }

    public MutableLiveData<List<UserModel>> getUserModelLiveData() {
        MutableLiveData<List<UserModel>> data = new MutableLiveData<>();

        compositeDisposable.add(apiService.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userModels -> {
                    if (userModels != null)
                        data.setValue(userModels);
                }, e -> Log.e("FirstRepo", "ErrorWith : " + e))
        );
        return data;
    }

    public MutableLiveData<List<UserModel>> getModelByUserLiveData() {
        MutableLiveData<List<UserModel>> data = new MutableLiveData<>();

        compositeDisposable.add(apiService.getUserOne()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userModels -> {
                    if (userModels != null)
                        data.setValue(userModels);
                }, e -> Log.e("FirstRepo", "ErrorWith : " + e))
        );
        return data;
    }
}
