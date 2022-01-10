package com.example.aswitch;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

public class BottomFragment extends Fragment {
    Context context;
    ImageView frgBottomImg;
    ImageView a, b;

    int[] imgIds = new int[]{ R.drawable.trad_curr, R.drawable.modern_curr};

    public BottomFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bottom, container, false);
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        frgBottomImg = view.findViewById(R.id.frgBottomImg);

        int pos = getArguments().getInt("position");

        frgBottomImg.setImageResource(imgIds[pos]);



    }

    //STEP 7
    //Implenet changeCityImage(position) method,
    // so that mainActivity can call it to chane the image according to postin value
    //position value is sent from TopActivity to MainActivity
    //then sent from MainActivity to BottomFragment
    void changeCityImage(int position){
        frgBottomImg.setImageResource(imgIds[position]);

        if (position == 0){frgBottomImg.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent(view.getContext(), TraditionalActivity.class);
                view.getContext().startActivity(intent);

                return false;
            }
        });}
        else if ( position ==1){
            frgBottomImg.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    Intent intent = new Intent(view.getContext(), ModernActivity.class);
                    view.getContext().startActivity(intent);
                    return false;
                }
            });
    }}
}