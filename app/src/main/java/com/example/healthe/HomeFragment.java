package com.example.healthe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static android.widget.Toast.LENGTH_LONG;

public class HomeFragment extends Fragment implements View.OnClickListener {
private Button BnAddUser;



    @Nullable

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home, container, false);
BnAddUser= view.findViewById(R.id.hinzu_button);
        BnAddUser.setOnClickListener(this);

        return view;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
public void onClick(View view){
        switch(view.getId()){
            case R.id.hinzu_button:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,new Add())
                        .addToBackStack(null).commit();
                break;



        }
}




}
