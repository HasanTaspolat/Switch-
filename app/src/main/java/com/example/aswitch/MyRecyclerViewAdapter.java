package com.example.aswitch;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

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


        holder.value.setText(curr.getPrice());

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String msg =
                        "\n  Currency Name:  "+curr.getName()
                                +"\n  Currency Amount:  "+curr.getPrice();

                ((TraditionalActivity)context).displayDialog(msg);
                //else if(context == ModernActivity.context) ((ModernActivity)context).displayDialog(msg);
            }
        });

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
        Button btn;

        MyRecyclerViewItemHolder(View viewItem) {
            super(viewItem);
            tvName = viewItem.findViewById(R.id.moneyName);
            icon = viewItem.findViewById(R.id.moneyImage);
            value = viewItem.findViewById(R.id.moneyValue);
            btn = viewItem.findViewById(R.id.button);
        }
    }

}

































































