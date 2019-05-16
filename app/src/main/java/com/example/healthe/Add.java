package com.example.healthe;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Add extends Fragment {
    private EditText Username,Email;
    private Button Save;



    public Add() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        Username = view.findViewById(R.id.user_username);
        Email = view.findViewById(R.id.user_email);
        Save = view.findViewById(R.id.button_save);

                Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            String username =Username.getText().toString();
            String  email = Email.getText().toString();

            User user = new User();
            user.setUsername(username);
            user.setEmail(email);

            MainActivity.myAppDatabase.MyDao().addUser(user);
                Toast.makeText(getActivity(),"User added, Well Done Bratan",Toast.LENGTH_SHORT).show();

                Username.setText("");
                Email.setText("");




            }

        });

        return view ;

    }

}
