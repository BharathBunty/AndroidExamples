package com.example.bkanjula.cameraapp.CameraphotoApp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.bkanjula.cameraapp.R;

public class Main2Activity extends AppCompatActivity {

    ImageView img_click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        img_click= findViewById(R.id.image_click);

        Intent intent = getIntent();
        String pic_uri = intent.getStringExtra("uri");
        Uri img_uri = Uri.parse(pic_uri);
        img_click.setImageURI(img_uri);
        img_click.setRotation(90);



    }



}
