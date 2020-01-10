package com.anvata.banksearch.server;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.anvata.banksearch.adapter.CustomAdapter;
import com.anvata.banksearch.server.model.BankResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ServerManager {


    private Context context;
    public   ServerManager(Context context)
    {
        this.context = context;
    }

    public void getCity(String cityName, final RecyclerView recyclerView, final ProgressDialog progressDialog , final ICallback iCallback)
    {


        Retrofit retrofit = APIClient.getClient();
        APIInterface apiInterface = retrofit.create(APIInterface.class);
        Call<ArrayList<BankResponse>> call= apiInterface.getBankDetailByCity(cityName);
        call.enqueue(new Callback<ArrayList<BankResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<BankResponse>> call, Response<ArrayList<BankResponse>> response) {

                if (response.body() != null) {

                    progressDialog.dismiss();
                    ArrayList<BankResponse> bankResponse = response.body();


                    iCallback.callback(bankResponse);
                    Log.d("MYDATA",bankResponse.toString());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<BankResponse>> call, Throwable t) {
                iCallback.callback(null);
                Log.d("MYDATA",t.getMessage());

            }
        });
    }

    public interface ICallback{
        public void callback(ArrayList<BankResponse> flag);

    }
}
