package com.example.bkanjula.cameraapp.ServerData;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.azoft.carousellayoutmanager.CarouselLayoutManager;
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener;
import com.azoft.carousellayoutmanager.CenterScrollListener;
import com.example.bkanjula.cameraapp.R;
import com.example.bkanjula.cameraapp.ServerData.data.Categories;
import com.example.bkanjula.cameraapp.ServerData.data.RestaurantItems;
import com.example.bkanjula.cameraapp.ServerData.data.SubCategories;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExpandableListActivity extends AppCompatActivity {

    @BindView(R.id.spinner)Spinner spinner;
    @BindView(R.id.spinner_sub)Spinner spinner_sub;
    @BindView(R.id.recycler_view_food)RecyclerView recyclerView;
    @BindView(R.id.shimmer_view_container)ShimmerFrameLayout shimmerFrameLayout;
    @BindView(R.id.linear_layout_spinner)LinearLayout linearLayout;


    ArrayList<Categories> categories = new ArrayList<>();
    ArrayList<SubCategories> subCategories = new ArrayList<>();
    ArrayList<RestaurantItems> restaurantItems = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    ArrayAdapter<String> arrayAdapter_sub;
    ArrayList<String> cate = new ArrayList<>();
    ArrayList<String> sub_cate = new ArrayList<>();
    RvAdapter rvAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_list);
        ButterKnife.bind(this);
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.background_spinner);
        Bitmap blurredBitmap = BlurBuilder.blur( this, originalBitmap );
        linearLayout.setBackground(new BitmapDrawable(getResources(), blurredBitmap));

        Intent intent = getIntent();
        categories = (ArrayList<Categories>) intent.getSerializableExtra("cat");
        Log.d("ExpandableListAdapter", categories.toString());

        final CarouselLayoutManager layoutManager = new CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL);
        layoutManager.setPostLayoutListener(new CarouselZoomPostLayoutListener());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new CenterScrollListener());
        recyclerView.setHasFixedSize(true);

        cate.add("Select Category");
        for (int i = 0; i < categories.size(); i++) {
            cate.add(categories.get(i).getName());
        }

        arrayAdapter = new ArrayAdapter<String>(ExpandableListActivity.this, android.R.layout.simple_list_item_1, cate);
        spinner.setAdapter(arrayAdapter);
        shimmerFrameLayout.stopShimmerAnimation();
//        if(categories != null) {
//            expandableListAdapter = new CustomExpandableListAdapter(this, categories);
//            list_item.setAdapter(expandableListAdapter);
//        }
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    shimmerFrameLayout.stopShimmerAnimation();
                    shimmerFrameLayout.setVisibility(View.GONE);
                }
                if (position != 0) {
                    shimmerFrameLayout.setVisibility(View.VISIBLE);
                    shimmerFrameLayout.startShimmerAnimation();
                    shimmerFrameLayout.setDuration(500);
                    subCategories = (categories.get(position - 1).getSubcategories());
                    sub_cate.clear();
                    for (int j = 0; j < subCategories.size(); j++) {
                        sub_cate.add(subCategories.get(j).getName());
                    }
                    arrayAdapter_sub = new ArrayAdapter<>(ExpandableListActivity.this, android.R.layout.simple_list_item_1, sub_cate);
                    spinner_sub.setAdapter(arrayAdapter_sub);

                    spinner_sub.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            shimmerFrameLayout.setVisibility(View.VISIBLE);
                            shimmerFrameLayout.startShimmerAnimation();
                            shimmerFrameLayout.setDuration(500);

                            restaurantItems = (subCategories.get(position).getRestaurantItems());
                            rvAdapter = new RvAdapter(ExpandableListActivity.this, restaurantItems);
                            recyclerView.setAdapter(rvAdapter);

                            shimmerFrameLayout.stopShimmerAnimation();
                            shimmerFrameLayout.setVisibility(View.GONE);
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
    }



    @Override
    public void onResume() {
        super.onResume();
        shimmerFrameLayout.startShimmerAnimation();
        shimmerFrameLayout.setDuration(500);
    }

    @Override
    protected void onPause() {
        shimmerFrameLayout.stopShimmerAnimation();
        super.onPause();
    }
}
