package com.example.bkanjula.cameraapp.ServerData;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.bkanjula.cameraapp.R;
import com.example.bkanjula.cameraapp.ServerData.data.Categories;
import com.example.bkanjula.cameraapp.ServerData.data.GetData;
import com.example.bkanjula.cameraapp.ServerData.data.SubCategories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private ApiInterface apiInterface;
    ArrayList<String> RestuarantList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    String rest_name;

    @BindView(R.id.listView)ListView listView;
    @BindView(R.id.search_view)SearchView mSearchView;
    @BindView(R.id.linear_layout)LinearLayout linearLayout;

    ArrayList<Categories> categories = new ArrayList<>();
    ArrayList<SubCategories> subCategories = new ArrayList<>();
    HashMap<Categories, List<SubCategories>> expandablelist = new HashMap<>();
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bakground);
        Bitmap blurredBitmap = BlurBuilder.blur( this, originalBitmap );
        linearLayout.setBackground(new BitmapDrawable(getResources(), blurredBitmap));
        apiInterface = RetrofitClientinstance.getRetrofitInstance().create(ApiInterface.class);
        getJsonData();

    }

    private void getJsonData() {

        Call<List<GetData>> call = apiInterface.getJSON("1");
        call.enqueue(new Callback<List<GetData>>() {
            @Override
            public void onResponse(Call<List<GetData>> call, Response<List<GetData>> response) {

                List<GetData> get_list = response.body();
                try {
                    if (get_list != null) {
                        int size = get_list.size();
                        for (int i = 0; i < size; i++) {
                            rest_name = (get_list.get(i).getName());
                            RestuarantList.add(rest_name);
                            categories = (get_list.get(0).getCategories());
                            for (int j = 0; j < categories.size(); j++) {
                                subCategories=(get_list.get(0).getCategories().get(j).getSubcategories());
                                expandablelist.put(categories.get(j), subCategories);
                            }
                        }

                        arrayAdapter = new ArrayAdapter<>(ListActivity.this,R.layout.simple_view,RestuarantList);
                        listView.setAdapter(arrayAdapter);

                        listView.setTextFilterEnabled(true);
                        setupSearchView();

                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                if(position == 0){
                                    Intent intent = new Intent(ListActivity.this,ExpandableListActivity.class);
                                    intent.putExtra("cat",categories);
                                    startActivity(intent);
                                } else if(position == 1){

                                    Intent intent = new Intent(ListActivity.this,HorizontalScrollActivity.class);
                                    intent.putExtra("cat",categories);
                                    startActivity(intent);

                                }
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<GetData>> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }

    private void setupSearchView() {
        mSearchView.setIconifiedByDefault(false);
        mSearchView.setOnQueryTextListener(ListActivity.this);
        mSearchView.setSubmitButtonEnabled(true);
        mSearchView.setQueryHint("Search Here");
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        if (TextUtils.isEmpty(newText)) {
            listView.clearTextFilter();
        } else {
            listView.setFilterText(newText);
        }
        return true;
    }
}
