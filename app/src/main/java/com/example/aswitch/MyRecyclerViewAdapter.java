package com.example.aswitch;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {
    String namee;
    Context context;

    public MyRecyclerViewAdapter(Context context, List<Currency> datacrypto ) {
        this.context = context;
        Commons.datacurrency=(ArrayList<Currency>) datacrypto;

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recyclerview_layout, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //BIND DATA

        final Currency cr = Commons.datacurrency.get(position);
        Log.d("aaaaaaaaaa"+cr.getPrice(),"aaaaaaaaa"+cr.getPrice());

        String aaa= cr.getName();
        /*if(aaa=="bitcoin")
            holder.icon.setImageResource(R.drawable.bitcoin);
        else if ( aaa =="ethereum")
            holder.icon.setImageResource(R.drawable.ethereum);
        else if( aaa=="dogecoin")
            holder.icon.setImageResource(R.drawable.doge);*/


        holder.tvName.setText(cr.getName()+"\n");


        holder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String msg =
                        "\n  Currency Name:  "+cr.getName()
                        +"\n  Currency Amount:  "+cr.getPrice();
               // ((..Activity)context).displayDialog(msg);
            }
        });

    }

    @Override
    public int getItemCount() {
        return Commons.datacurrency.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        ImageButton btnDetail;
        TextView tvName;
        ImageView icon;

        MyViewHolder(View viewItem){
            super(viewItem);
            tvName = viewItem.findViewById(R.id.tvName);
            icon = viewItem.findViewById(R.id.imgIcon);
            btnDetail = viewItem.findViewById(R.id.btnDetail);
            namee = tvName.getText().toString();
        }
    }
}
