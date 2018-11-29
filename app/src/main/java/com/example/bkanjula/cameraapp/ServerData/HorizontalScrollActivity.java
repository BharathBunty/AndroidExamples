package com.example.bkanjula.cameraapp.ServerData;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.bkanjula.cameraapp.R;
import com.example.bkanjula.cameraapp.ServerData.data.Categories;
import com.example.bkanjula.cameraapp.ServerData.data.RestaurantItems;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HorizontalScrollActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView_veg)
    RecyclerView recyclerView_veg;
    @BindView(R.id.recyclerView_nonveg)
    RecyclerView recyclerView_Non;
    @BindView(R.id.textView_category)
    TextView textView_veg;
    @BindView(R.id.textView_cat)
    TextView textView_non;

    ArrayList<Categories> categories = new ArrayList<>();
    ArrayList<RestaurantItems> veg = new ArrayList<>();
    ArrayList<RestaurantItems> nonveg = new ArrayList<>();

    RvAdapter rvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_scroll);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        categories = (ArrayList<Categories>) intent.getSerializableExtra("cat");

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);// set a LinearLayoutManager
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL); // set Horizontal Orientation
        recyclerView_Non.setLayoutManager(linearLayoutManager); // set LayoutManager to RecyclerView
        recyclerView_Non.setHasFixedSize(true);

        final LinearLayoutManager linearLayoutManager_veg = new LinearLayoutManager(this);// set a LinearLayoutManager
        linearLayoutManager_veg.setOrientation(LinearLayoutManager.HORIZONTAL); // set Horizontal Orientation
        recyclerView_veg.setLayoutManager(linearLayoutManager_veg); // set LayoutManager to RecyclerView
        recyclerView_veg.setHasFixedSize(true);

        textView_veg.setText(categories.get(0).getName());
        textView_non.setText(categories.get(1).getName());

        for (int i = 0; i < categories.get(0).getSubcategories().size(); i++) {
            veg.addAll(categories.get(0).getSubcategories().get(i).getRestaurantItems());
        }

        rvAdapter = new RvAdapter(HorizontalScrollActivity.this, veg);
        recyclerView_veg.setAdapter(rvAdapter);

        for (int i = 0; i < categories.get(1).getSubcategories().size(); i++) {
            nonveg.addAll(categories.get(1).getSubcategories().get(i).getRestaurantItems());
        }

        rvAdapter = new RvAdapter(HorizontalScrollActivity.this, nonveg);
        recyclerView_Non.setAdapter(rvAdapter);


    }
}
