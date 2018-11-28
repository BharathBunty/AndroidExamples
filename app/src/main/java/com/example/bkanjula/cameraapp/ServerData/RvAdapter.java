package com.example.bkanjula.cameraapp.ServerData;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bkanjula.cameraapp.R;
import com.example.bkanjula.cameraapp.ServerData.data.RestaurantItems;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ViewHolder>{

    Context context;
    ArrayList<RestaurantItems> restaurantItems ;
    public RvAdapter(Context context, ArrayList<RestaurantItems> restaurantItems) {
        this.context = context;
        this.restaurantItems = restaurantItems;

    }

    @NonNull
    @Override
    public RvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_layout_item,viewGroup,false);
        return new RvAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RvAdapter.ViewHolder viewHolder, int i) {
        viewHolder.tv_name.setText(restaurantItems.get(i).getItemname());
        viewHolder.tv_cost.setText(restaurantItems.get(i).getCost() );
        String url = "http://87.106.210.241:10088/";
        Picasso.with(context).load(url+restaurantItems.get(i).getImagepath()).error(R.mipmap.ic_launcher).resize(150,150).into(viewHolder.iv_image);


    }

    @Override
    public int getItemCount() {
        return restaurantItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_name;
        private TextView tv_cost;
        private ImageView iv_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.textView_itemName);
            tv_cost = itemView.findViewById(R.id.textView_cost);
            iv_image = itemView.findViewById(R.id.imageView);
        }
    }
}
