package com.example.bkanjula.cameraapp.LoginApp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.example.bkanjula.cameraapp.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity implements OnItemClickListener {

    private static final String TAG = "home";
    RecyclerViewAdapter recyclerViewAdapter;
    @BindView(R.id.recycler_view_emp) RecyclerView recyclerView;
    int row_position;

    ArrayList<EmployeeDetails> employeeDetailsList = new ArrayList<EmployeeDetails>();
    DataBaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        db = new DataBaseHelper(getApplicationContext());
//        recyclerView = findViewById(R.id.recycler_view_emp);
//        floatingActionButton = findViewById(R.id.fab_add);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HomeActivity.this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        displayData();
        registerForContextMenu(recyclerView);

/*        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, LoginDetailActivity.class);
                startActivity(intent);
            }
        });*/


    }

    @OnClick(R.id.fab_add)
    public void onButtonClick(){
        Intent intent = new Intent(HomeActivity.this, LoginDetailActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        displayData();
    }

    private void displayData() {
        employeeDetailsList = db.getData();
        recyclerViewAdapter = new RecyclerViewAdapter(HomeActivity.this, employeeDetailsList, this,HomeActivity.this);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
//        Intent intent = new Intent(HomeActivity.this, AddressActivity.class);
//        startActivity(intent);
//        finish();
    }



    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.update) {
            Intent intent = new Intent(HomeActivity.this, UpdateActivity.class);
            intent.putExtra("position", row_position);
            startActivity(intent);

        } else if (item.getItemId() == R.id.delete) {


            db.deleteEntry(employeeDetailsList.get(row_position));
            displayData();

        } else {
            return false;
        }


        return true;
    }


    @Override
    public void onItemClick(View view, int position) {
        row_position = position;
    }
}
