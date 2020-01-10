package com.anvata.banksearch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.anvata.banksearch.adapter.CustomAdapter;
import com.anvata.banksearch.server.ServerManager;
import com.anvata.banksearch.server.model.BankResponse;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    ProgressDialog progressDialog;
    ArrayList<BankResponse> names;
    RecyclerView recyclerView;
    EditText editTextSearch;
    CustomAdapter adapter;

    String[] city = {"Select City", "BANGALORE", "MUMBAI", "CHENNAI"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       Spinner spin = (Spinner) findViewById(R.id.city);
       ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, city);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setSelection(0);
        spin.setAdapter(adapter);





       spin.setOnItemSelectedListener(this);




        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_back_detail);
        editTextSearch = (EditText) findViewById(R.id.editTextSearch);


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));




        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //after the change calling the method and passing the search input
                if(names!=null)
               filter(editable.toString());
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
       // Toast.makeText(getApplicationContext(), "Selected city: "+city[i] ,Toast.LENGTH_SHORT).show();
        if(i!=0) {
            progressDialog = new ProgressDialog(MainActivity.this);
            //  progressDialog.setMessage("Signing Up...");
            progressDialog.getWindow().setLayout(400, 400);
            progressDialog.setCancelable(false);
            progressDialog.show();
            getData(city[i]);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    private void filter(String text) {
        //new array list that will hold the filtered data
        ArrayList<BankResponse> filterdNames = new ArrayList<BankResponse>();

        //looping through existing elements
        for (BankResponse s : names) {

            //if the existing elements contains the search input
            if (s.getIfsc().toLowerCase().contains(text.toLowerCase())) {
                //adding the element to filtered list
                filterdNames.add(s);
            }
        }

        //calling a method of the adapter class and passing the filtered list
        adapter.filterList(filterdNames);
    }

    public void getData(final String city)
    {
        ServerManager serverManager = new ServerManager(this);
        serverManager.getCity(city, recyclerView, progressDialog, new ServerManager.ICallback() {
            @Override
            public void callback(ArrayList<BankResponse> flag) {
                if(flag==null)
                    getData(city);
                else
                {
                    names=flag;
                    adapter = new CustomAdapter(names);
                    recyclerView.setAdapter(adapter);
                }
            }
        });
    }

}
