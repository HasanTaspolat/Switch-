package com.example.aswitch;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {
    String namee;
    Context context;
    private ArrayList<Currency> recyclerItemValues;


    public MyRecyclerViewAdapter(@NonNull Context context, @NonNull ArrayList<Currency> datacrypto ) {
        this.context = context;
        this.recyclerItemValues = datacrypto;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType) {


        View itemView;
        LayoutInflater inflator = LayoutInflater.from(parent.getContext());
        itemView = inflator.inflate(R.layout.recyclerview_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //BIND DATA

        final Currency cr = recyclerItemValues.get(position);

        Log.d("cr.g ++++++++++++et",cr.getName());

        MyViewHolder itemView = (MyViewHolder) holder;

        /*if(aaa=="bitcoin")
            holder.icon.setImageResource(R.drawable.bitcoin);
        else if ( aaa =="ethereum")
            holder.icon.setImageResource(R.drawable.ethereum);
        else if( aaa=="dogecoin")
            holder.icon.setImageResource(R.drawable.doge);*/

//Integer.parseInt(cr.getImg()+"\n")
        itemView.tvName.setText(cr.getName()+"\n");
        itemView.icon.setImageResource(R.drawable.logo);
        itemView.value.setText(cr.getPrice()+"\n");

    }

    @Override
    public int getItemCount() {
        return  this.recyclerItemValues.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{


        TextView tvName;
        ImageView icon;
        TextView value;

        MyViewHolder(View viewItem){
            super(viewItem);
            tvName = viewItem.findViewById(R.id.moneyName);
            icon = viewItem.findViewById(R.id.moneyImage);
            value = viewItem.findViewById(R.id.moneyValue);
        }
    }
}
