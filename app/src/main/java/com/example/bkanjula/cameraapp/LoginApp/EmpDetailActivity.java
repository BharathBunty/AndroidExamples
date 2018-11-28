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

public class EmpDetailActivity extends AppCompatActivity {


//    Button button_nxt_emp, button_previous_emp;
    @BindView(R.id.email)TextInputEditText txt_emailId;
    @BindView(R.id.employee_id)TextInputEditText txt_empId;
    @BindView(R.id.employee_name)TextInputEditText txt_name;
    String bytArray, path;
    DataBaseHelper db;
    EmployeeDetails employeeDetails;
    ArrayList<EmployeeDetails> employeeDetailsList = new ArrayList<EmployeeDetails>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_detail);
        ButterKnife.bind(this);
        db = new DataBaseHelper(EmpDetailActivity.this);
        employeeDetails = new EmployeeDetails();

//        button_previous_emp = findViewById(R.id.button_prev_emp);
//        button_nxt_emp = findViewById(R.id.button_next_emp);

//        txt_emailId = findViewById(R.id.email);
//        txt_empId = findViewById(R.id.employee_id);
//        txt_name = findViewById(R.id.employee_name);


      /*  button_nxt_emp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = txt_name.getText().toString().trim();
                String email = txt_emailId.getText().toString().trim();
                String empId = txt_empId.getText().toString().trim();
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(empId)) {


                    SharedPreferences sharedPreferences = getSharedPreferences("CameraApp", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("name", name);
                    editor.putString("email", email);
                    editor.putString("empId", empId);
                    editor.putString("bytAry", bytArray);
                    editor.apply();

                    Intent intent = new Intent(EmpDetailActivity.this, AddressActivity.class);
                    intent.putExtra("FromActivity", "EmpDetailActivity");
                    startActivity(intent);
                    finish();


                }

            }
        });*/

/*        button_previous_emp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmpDetailActivity.this, LoginDetailActivity.class);
                intent.putExtra("FromActivity", "EmpDetailActivity");
                startActivity(intent);
                finish();

            }
        });*/

    }

    @OnClick(R.id.button_next_emp)
    public void nextpage() {
        String name = txt_name.getText().toString().trim();
        String email = txt_emailId.getText().toString().trim();
        String empId = txt_empId.getText().toString().trim();
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(empId)) {

            SharedPreferences sharedPreferences = getSharedPreferences("CameraApp", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("name", name);
            editor.putString("email", email);
            editor.putString("empId", empId);
            editor.putString("bytAry", bytArray);
            editor.apply();

            Intent intent = new Intent(EmpDetailActivity.this, AddressActivity.class);
            intent.putExtra("FromActivity", "EmpDetailActivity");
            startActivity(intent);
            finish();
        }
    }

    @OnClick(R.id.button_prev_emp)
    public void previouspage() {
        Intent intent = new Intent(EmpDetailActivity.this, LoginDetailActivity.class);
        intent.putExtra("FromActivity", "EmpDetailActivity");
        startActivity(intent);
        finish();
    }


    @Override
    protected void onResume() {
        super.onResume();

        String from = getIntent().getStringExtra("FromActivity");
        assert from != null;
        if (from.equals("AddressActivity") || from.equals("LoginDetailActivity")) {
            SharedPreferences sharedPreferences = getSharedPreferences("CameraApp", Context.MODE_PRIVATE);
            String name = sharedPreferences.getString("name", null);
            String email = sharedPreferences.getString("email", null);
            String empId = sharedPreferences.getString("empId", null);
            if (name != null && email != null && empId != null) {
                txt_name.setText(name);
                txt_empId.setText(empId);
                txt_emailId.setText(email);

            }
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(EmpDetailActivity.this, LoginDetailActivity.class);
        intent.putExtra("FromActivity", "EmpDetailActivity");
        intent.putExtra("ImagePath", path);
        startActivity(intent);
        finish();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        employeeDetailsList.clear();
    }
}
