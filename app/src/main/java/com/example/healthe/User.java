package com.example.healthe;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "parameters")

public class User {

   @PrimaryKey
   @NonNull
   @ColumnInfo(name = "user_username")
    private String username;
    @ColumnInfo(name = "user_email")
    private String email;

    private int puls;

    private int schlaf;

    private int gewicht;

    private int alter;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPuls() {
        return puls;
    }

    public void setPuls(int puls) {
        this.puls = puls;
    }

    public int getSchlaf() {
        return schlaf;
    }

    public void setSchlaf(int schlaf) {
        this.schlaf = schlaf;
    }

    public int getGewicht() {
        return gewicht;
    }

    public void setGewicht(int gewicht) {
        this.gewicht = gewicht;
    }

    public int getAlter() {
        return alter;
    }

    public void setAlter(int alter) {
        this.alter = alter;
    }
}
