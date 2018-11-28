package com.example.bkanjula.cameraapp.Retrofit;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bkanjula.cameraapp.R;
import com.example.bkanjula.cameraapp.Retrofit.JsonResponse.Post;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPost extends Fragment {

    private TextView mResponseTv;
    private ApiInterface mAPIService;

    public FragmentPost() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.layout_post, container, false);
        final EditText nameEt = (EditText) v.findViewById(R.id.et_title);
        final EditText jobEt = (EditText) v.findViewById(R.id.et_body);
        Button submitBtn = (Button) v.findViewById(R.id.btn_submit);
        mResponseTv = (TextView) v.findViewById(R.id.tv_response);

        mAPIService = RetrofitClientinstance.getRetrofitInstance().create(ApiInterface.class);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEt.getText().toString().trim();
                String job = jobEt.getText().toString().trim();
                if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(job)) {
                    sendPost(name,job);
                }
            }
        });

        return v;
    }

    private void sendPost(String name, String job) {

        mAPIService.savePost(name, job).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

                if(response.isSuccessful()) {
                    showResponse(response.body().toString());
                    Log.i("MainActivity", "post submitted to API." + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.e("MainActivity", "Unable to submit post to API.");
            }
        });
    }

    private void showResponse(String s) {

        if(mResponseTv.getVisibility() == View.GONE) {
            mResponseTv.setVisibility(View.VISIBLE);
        }
        mResponseTv.setText(s);
    }

}
