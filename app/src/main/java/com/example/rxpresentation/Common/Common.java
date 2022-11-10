package com.example.rxpresentation.Common;

import com.example.rxpresentation.Network.APIService;
import com.example.rxpresentation.Network.RetrofitClient;

public class Common {
    public static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    public static APIService getAPIService() {
        return RetrofitClient.getRetrofitClient(BASE_URL)
                .create(APIService.class);
    }
}
