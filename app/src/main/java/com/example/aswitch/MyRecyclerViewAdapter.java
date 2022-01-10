package com.example.aswitch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyRecyclerViewItemHolder> {
    private Context context;
    private ArrayList<Currency> mArrayList;
    // Turkish lira symbol

    public MyRecyclerViewAdapter(Context context, ArrayList<Currency> mArrayList) {
        this.context = context;
        this.mArrayList = mArrayList;
    }

    // Each object of the ViewHolder will be created here
    @NonNull
    @Override
    public MyRecyclerViewItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater mLayoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = (View) mLayoutInflater.inflate(R.layout.recyclerview_layout, parent, false);
        MyRecyclerViewItemHolder mViewHolder = new MyRecyclerViewItemHolder(itemView);

        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewItemHolder holder, int position) {
        Currency curr = mArrayList.get(position);


        holder.tvName.setText(curr.getName());
        holder.icon.setImageResource(R.drawable.logo);
        holder.value.setText(curr.getPrice());


    }

    // How many items exist in the list
    @Override
    public int getItemCount() {
        return mArrayList.size();
    }


    class MyRecyclerViewItemHolder extends RecyclerView.ViewHolder {


        TextView tvName;
        ImageView icon;
        TextView value;

        MyRecyclerViewItemHolder(View viewItem) {
            super(viewItem);
            tvName = viewItem.findViewById(R.id.moneyName);
            icon = viewItem.findViewById(R.id.moneyImage);
            value = viewItem.findViewById(R.id.moneyValue);
        }
    }

}

































































