package com.example.bkanjula.cameraapp.Retrofit;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bkanjula.cameraapp.R;
import com.example.bkanjula.cameraapp.Retrofit.JsonResponse.Datum;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    List<Datum> datumList ;
    private Context context;
    LayoutInflater inflater;

    public DataAdapter(Context context , List<Datum> data) {
        this.context = context;
        this.datumList = data;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =inflater.inflate(R.layout.card_layout,parent,false);
        return new DataAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_firstname.setText(datumList.get(position).getFirstName());
        holder.tv_lastname.setText(datumList.get(position).getLastName());
        Picasso.with(context).load(datumList.get(position).getAvatar()).error(R.mipmap.ic_launcher).resize(150,150).into(holder.iv_image);

    }

    @Override
    public int getItemCount() {
        return datumList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_firstname;
        private TextView tv_lastname;
        public ImageView iv_image;

        public ViewHolder(View itemView) {
            super(itemView);

            tv_firstname = itemView.findViewById(R.id.textViewName);
            tv_lastname = itemView.findViewById(R.id.textViewVersion);
            iv_image = itemView.findViewById(R.id.imageView);

        }
    }
}