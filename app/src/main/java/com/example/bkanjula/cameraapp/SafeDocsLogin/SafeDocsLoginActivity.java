package com.example.bkanjula.cameraapp.SafeDocsLogin;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.example.bkanjula.cameraapp.R;

public class SafeDocsLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_docs_login);

//        final TextInputEditText emailEt = findViewById(R.id.email);
//        final TextInputEditText passwordEt = findViewById(R.id.password);
//        Button submitBtn = (Button) findViewById(R.id.button);
//        submitBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String name = emailEt.getText().toString().trim();
//                String job = passwordEt.getText().toString().trim();
//                if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(job)) {
//                    Intent intent = new Intent(SafeDocsLoginActivity.this,Main2Activity.class);
//                    startActivity(intent);
//                }
//            }
//        });
    }
}
