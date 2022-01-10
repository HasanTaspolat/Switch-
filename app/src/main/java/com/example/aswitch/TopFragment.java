package com.example.aswitch;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TopFragment extends Fragment {

    Spinner frgTopSpinnerCity;

    boolean isDefaultSelection=true;

    /*****************************************/
    //STEP 3
    //Event will be done on TopFragement then it will effect the BottomFragement
    //So, create a listener and a reference from listener type

    TopFragmentInterface topFragmentInterfaceListener =null;

    interface  TopFragmentInterface{
        public void changeImage(int position);
    }
    /*****************************************/

    /*****************************************/
    //STEP4
    //override onAttach() method  so that, connection with MainActivity is provided
    // and through MainActivity image on BottomFragment can be changed.

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof TopFragmentInterface )
            topFragmentInterfaceListener = (TopFragmentInterface) context;
        // here, context is the MainActivity
        // Assign context to TopFragmentInterfaceListener means that MainActivity implements that interface
        // and changeImage() method is definietly implemented in MainActivity
    }

    /*****************************************/

    public TopFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_top, container, false) ;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        frgTopSpinnerCity = view.findViewById(R.id.frgTopSpinnerCity);

        frgTopSpinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(isDefaultSelection)
                    isDefaultSelection = false;
                else{
                    String str = frgTopSpinnerCity.getSelectedItem().toString();
                    Toast.makeText(getActivity(), str+" is selected from Top Fragement",Toast.LENGTH_SHORT).show();

                    //STEP 8
                    //When a city selected image of BottomFragment will be changed. So changeImage method implemented in MAinActivity will be called
                    //Remember, topFragmentInterfaceListener points to the MainActivity
                    topFragmentInterfaceListener.changeImage(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Toast.makeText(getActivity(), "Top Fragment loaded",Toast.LENGTH_SHORT).show();
    }
}