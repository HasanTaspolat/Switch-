package com.example.aswitch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {
    String namee;
    Context context;
    private ArrayList<Currency> recyclerItemValues;


    public MyRecyclerViewAdapter(Context context, ArrayList<Currency> datacrypto ) {
        this.context = context;
        this.recyclerItemValues = datacrypto;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View itemView;
        LayoutInflater inflator = LayoutInflater.from(parent.getContext());
        itemView = inflator.inflate(R.layout.recyclerview_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //BIND DATA

        final Currency cr = recyclerItemValues.get(position);

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
        return recyclerItemValues.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        ImageButton btnDetail;
        TextView tvName;
        ImageView icon;

        MyViewHolder(View viewItem){
            super(viewItem);
            tvName = viewItem.findViewById(R.id.moneyName);
            icon = viewItem.findViewById(R.id.moneyImage);
            namee = tvName.getText().toString();
        }
    }
}
