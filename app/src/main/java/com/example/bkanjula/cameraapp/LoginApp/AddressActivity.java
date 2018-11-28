package com.example.bkanjula.cameraapp.LoginApp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.example.bkanjula.cameraapp.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddressActivity extends AppCompatActivity {

    // Button button_prev, button_sub;
    EmployeeDetails employeeDetails;
    DataBaseHelper db;
    @BindView(R.id.area)TextInputEditText txt_street;
    @BindView(R.id.plotno)TextInputEditText txt_plot;
    @BindView(R.id.Street)TextInputEditText  txt_area;
    ArrayList<EmployeeDetails> employeeDetailsList = new ArrayList<EmployeeDetails>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        ButterKnife.bind(this);
        employeeDetails = new EmployeeDetails();
        db = new DataBaseHelper(AddressActivity.this);

     /*   button_prev = findViewById(R.id.button_prev);
        button_sub = findViewById(R.id.button_submit);

        txt_area = findViewById(R.id.area);
        txt_plot = findViewById(R.id.plotno);
        txt_street = findViewById(R.id.Street);*/

/*        button_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String area = txt_area.getText().toString().trim();
                String plot = txt_plot.getText().toString().trim();
                String street = txt_street.getText().toString().trim();
                if (!TextUtils.isEmpty(area) && !TextUtils.isEmpty(plot) && !TextUtils.isEmpty(street)) {

                    SharedPreferences sharedPreferences = getSharedPreferences("CameraApp", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("area", area);
                    editor.putString("plot", plot);
                    editor.putString("street", street);
                    editor.apply();
                }
                Intent intent = new Intent(AddressActivity.this, EmpDetailActivity.class);
                intent.putExtra("FromActivity", "AddressActivity");
                startActivity(intent);
                finish();
            }
        });

        button_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("CameraApp", Context.MODE_PRIVATE);
                String image_uri = sharedPreferences.getString("pic", null);
                String name = sharedPreferences.getString("name", null);
                String email = sharedPreferences.getString("email", null);
                String empId = sharedPreferences.getString("empId", null);
                if (name != null && email != null && empId != null && image_uri != null) {
                    employeeDetails = new EmployeeDetails();
                    employeeDetails.setName(name);
                    employeeDetails.setEmail(email);
                    employeeDetails.setId(empId);
                    employeeDetails.setImage(image_uri);
                    db.insertData(employeeDetails);


                    Intent intent = new Intent(AddressActivity.this, HomeActivity.class);
                    startActivity(intent);
                    sharedPreferences.edit().clear().apply();
                    finish();

                }
            }
        });*/
    }


    @OnClick(R.id.button_prev)
    public void prevPage(){
        String area = txt_area.getText().toString().trim();
        String plot = txt_plot.getText().toString().trim();
        String street = txt_street.getText().toString().trim();
        if (!TextUtils.isEmpty(area) && !TextUtils.isEmpty(plot) && !TextUtils.isEmpty(street)) {

            SharedPreferences sharedPreferences = getSharedPreferences("CameraApp", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("area", area);
            editor.putString("plot", plot);
            editor.putString("street", street);
            editor.apply();
        }
        Intent intent = new Intent(AddressActivity.this, EmpDetailActivity.class);
        intent.putExtra("FromActivity", "AddressActivity");
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.button_submit)
    public void submitDetails(){
        SharedPreferences sharedPreferences = getSharedPreferences("CameraApp", Context.MODE_PRIVATE);
        String image_uri = sharedPreferences.getString("pic", null);
        String name = sharedPreferences.getString("name", null);
        String email = sharedPreferences.getString("email", null);
        String empId = sharedPreferences.getString("empId", null);
        if (name != null && email != null && empId != null && image_uri != null) {
            employeeDetails = new EmployeeDetails();
            employeeDetails.setName(name);
            employeeDetails.setEmail(email);
            employeeDetails.setId(empId);
            employeeDetails.setImage(image_uri);
            db.insertData(employeeDetails);


            Intent intent = new Intent(AddressActivity.this, HomeActivity.class);
            startActivity(intent);
            sharedPreferences.edit().clear().apply();
            finish();

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        String from = getIntent().getStringExtra("FromActivity");
        if (from != null && from.equals("EmpDetailActivity")) {
            SharedPreferences sharedPreferences = getSharedPreferences("CameraApp", Context.MODE_PRIVATE);
            String area = sharedPreferences.getString("area", null);
            String plot = sharedPreferences.getString("plot", null);
            String street = sharedPreferences.getString("street", null);
            if (area != null && plot != null && street != null) {
                txt_area.setText(area);
                txt_plot.setText(plot);
                txt_street.setText(street);
            }

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(AddressActivity.this, EmpDetailActivity.class);
        intent.putExtra("FromActivity", "AddressActivity");
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        employeeDetailsList.clear();
    }
}
