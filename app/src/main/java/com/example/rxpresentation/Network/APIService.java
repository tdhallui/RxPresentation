package com.example.rxpresentation.Network;

import com.example.rxpresentation.Model.UserModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface APIService {

    @GET("/posts")
    Observable<List<UserModel>> getUsers();

    @GET("/posts?user=1")
    Observable<List<UserModel>> getUserOne();
}
