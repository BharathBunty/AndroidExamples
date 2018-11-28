package com.example.bkanjula.cameraapp.LoginApp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bkanjula.cameraapp.R;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>  {

    Context context;
    ArrayList<EmployeeDetails> employeeDetailsList;
    int position;
    private OnItemClickListener onItemClickListener;
    public static Activity activity;

    public RecyclerViewAdapter(Context context, ArrayList<EmployeeDetails> employeeDetailsList,OnItemClickListener onItemClickListener1) {

        this.context = context;

        this.employeeDetailsList = employeeDetailsList;
        this.onItemClickListener = onItemClickListener1;

    }

    public RecyclerViewAdapter(Context context, ArrayList<EmployeeDetails> employeeDetailsList, OnItemClickListener onItemClickListener, Activity activity) {
        this.context = context;
        this.employeeDetailsList = employeeDetailsList;
        this.onItemClickListener = onItemClickListener;
        this.activity = activity;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_homelayout, viewGroup, false);
        ButterKnife.bind(this, view);
        return new RecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewAdapter.ViewHolder viewHolder, final int position) {

        final EmployeeDetails employeeDetails = employeeDetailsList.get(position);
        viewHolder.textView_name.setText(employeeDetails.getName());
        viewHolder.textView_id.setText(employeeDetails.getId());
        viewHolder.textView_email.setText(employeeDetails.getEmail());

        File imgFile = new  File(employeeDetails.getImage());

        if(imgFile.exists()){

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            viewHolder.imageView.setImageBitmap(myBitmap);

        }
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(v,position);
                activity.openContextMenu(v);

            }
        });
        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
               employeeDetails.setPosition(viewHolder.getAdapterPosition());
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return employeeDetailsList.size();
    }
   /* @Override
    public void onViewRecycled(ViewHolder holder) {
        holder.itemView.setOnLongClickListener(context);
        super.onViewRecycled(holder);
    }*/

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{

        public ImageView imageView_prof;
        @BindView(R.id.card_view) CardView cardView;
        @BindView(R.id.textViewName) TextView textView_name;
        @BindView(R.id.textViewEmpEmail) TextView textView_email;
        @BindView(R.id.textViewEmpId) TextView textView_id;
        @BindView(R.id.imageView_prof)ImageView imageView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
//            imageView_prof = itemView.findViewById(R.id.imageView_prof);
//            textView_email = itemView.findViewById(R.id.textViewEmpEmail);
//            textView_id = itemView.findViewById(R.id.textViewEmpId);
//            textView_name = itemView.findViewById(R.id.textViewName);
//            rows = itemView.findViewById(R.id.row_id);
//            cardView = itemView.findViewById(R.id.card_view);
            itemView.setOnCreateContextMenuListener(this);


        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

            // inflate menu
            menu.setHeaderTitle("Select The Action");
            MenuInflater inflater = activity.getMenuInflater();
            inflater.inflate(R.menu.context_layout, menu);

           /* menu.setHeaderTitle("Select The Action");
            menu.add(Menu.NONE, R.id.update,
                    Menu.NONE, R.string.update);
            menu.add(Menu.NONE, R.id.delete,
                    Menu.NONE, R.string.delete);*/
        }


    }
}
