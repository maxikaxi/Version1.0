package com.example.healthe;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReadUserFragment extends Fragment {
private TextView TxtInfo;

    public ReadUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_read_user, container, false);
        TxtInfo = view.findViewById(R.id.txt_display_info);

        List<User> users = MainActivity.myAppDatabase.MyDao().getUsers();


        String info = "";

        for (User usr : users)
        {
            String username = usr.getUsername();
            String email = usr.getEmail();
            info = info+"\n\n"+"Username :"+username+"\n Email :"+email;

        }
TxtInfo.setText(info);
        return view;
    }

}
