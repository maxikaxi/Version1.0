package com.example.healthe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    public static FragmentManager fragmentManager;
    public static MyAppDatabase myAppDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottombar = findViewById(R.id.bottombar);
        bottombar.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new HomeFragment()).commit();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, new HomeFragment()).commit();
        myAppDatabase = Room.databaseBuilder(getApplicationContext(),MyAppDatabase.class,"userdb").allowMainThreadQueries().build();
        fragmentManager = getSupportFragmentManager();
        if(findViewById(R.id.fragment_container)!=null){
        if(savedInstanceState!=null)
        {
            return;
            }
        fragmentManager.beginTransaction().add(R.id.fragment_container, new HomeFragment()).commit();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.Home:
                            selectedFragment = new HomeFragment();
                            break;

                        case R.id.Schlaf:
                            selectedFragment = new SchlafFragment();
                            break;

                        case R.id.Puls:
                            selectedFragment = new PulsFragment();
                            break;

                        case R.id.Profil:
                            selectedFragment = new ProfilFragment();
                            break;

                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                    return true;
                }
            };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menueleiste, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.einstellungen:
                Toast.makeText(this, "Einstellungsfenster wird geöffnet", Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(this, Einstellungen.class));
                return true;

            case R.id.logout:
                Toast.makeText(this, "Man wird ausgeloggt", Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(this, Logout.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void openToastLebensenergie  (View view) {
        Toast.makeText(this, "Lebensenergie-Grafik", Toast.LENGTH_LONG).show();

    }

    public void openToastPuls  (View view) {
        Toast.makeText(this, "Pulsanzeige", Toast.LENGTH_LONG).show();

    }


    public void openToastSchritte  (View view) {
        Toast.makeText(this, "Tägliche Schrittanzahl", Toast.LENGTH_LONG).show();
    }

    public void openToastSchlaf  (View view) {
        Toast.makeText(this, "Schlafanalyse-Grafik", Toast.LENGTH_LONG).show();
    }

    public void openToastProfil  (View view) {
        Toast.makeText(this, "Details über Benutzer", Toast.LENGTH_LONG).show();
    }

}



