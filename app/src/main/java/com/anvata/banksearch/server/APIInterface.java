package com.anvata.banksearch.server;

import com.anvata.banksearch.server.model.BankResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("/banks")
    Call<ArrayList<BankResponse>> getBankDetailByCity(@Query("city") String city );
}
