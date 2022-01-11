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

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyRecyclerViewAdapterModern extends RecyclerView.Adapter<MyRecyclerViewAdapterModern.MyRecyclerViewItemHolder> {
    private Context context;
    private ArrayList<Currency> mArrayList;
    // Turkish lira symbol

    public MyRecyclerViewAdapterModern(Context context, ArrayList<Currency> mArrayList) {
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

        String imageNameAddress = curr.getImg();



        Picasso.with(context)
                .load(imageNameAddress)
                .into(holder.icon);


        Log.d("IMAGE REQUESTED", imageNameAddress);


        holder.tvName.setText(curr.getName());
        holder.value.setText(curr.getPrice());

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String msg =
                        "\n  Currency Name:  "+curr.getName()
                                +"\n  Currency Amount:  "+curr.getPrice();

                ((ModernActivity)context).displayDialog(msg);
            }
        });

        YoYo.with(Techniques.SlideInLeft)
                .duration(700)
                .repeat(0)
                .playOn(holder.btn);

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
            icon = (ImageView) viewItem.findViewById(R.id.moneyImage);
            value = viewItem.findViewById(R.id.moneyValue);
            btn = viewItem.findViewById(R.id.button);
        }

    }

}

































































