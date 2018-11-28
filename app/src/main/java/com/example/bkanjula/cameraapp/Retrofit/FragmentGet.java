package com.example.bkanjula.cameraapp.Retrofit;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bkanjula.cameraapp.R;
import com.example.bkanjula.cameraapp.Retrofit.JsonResponse.Datum;
import com.example.bkanjula.cameraapp.Retrofit.JsonResponse.Example;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentGet extends Fragment {


    ApiInterface apiInterface;
    RecyclerView recyclerView;
    List<Datum> datumList = new ArrayList<Datum>();
    DataAdapter adapter;
    Example example;

    public FragmentGet() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.layout_get, container, false);
        apiInterface  = RetrofitClientinstance.getRetrofitInstance().create(ApiInterface.class);
        recyclerView = v.findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        getJsonData();

        return  v;

    }

    private void getJsonData() {

        Call<Example> call = apiInterface.getJSON("2");
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {

                example = response.body();
                datumList = example.getData();

                adapter = new DataAdapter(getActivity(),datumList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });

    }

}
