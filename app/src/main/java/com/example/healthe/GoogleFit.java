package com.example.healthe;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.fitness.FitnessOptions;
import com.google.android.gms.fitness.data.DataType;

public class GoogleFit {
    private static GoogleFit INSTANCE = null;
    private static Integer GOOGLE_FIT_PERMISSIONS_REQUEST_CODE = 1234567890;

    private FitnessOptions fitnessOptions = FitnessOptions.builder()
            .addDataType(DataType.TYPE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
            .addDataType(DataType.AGGREGATE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
            .build();

    // other instance variables can be here

    private GoogleFit(Context context, Activity activity) {
        if (!GoogleSignIn.hasPermissions(GoogleSignIn.getLastSignedInAccount(context), fitnessOptions)) {
            GoogleSignIn.requestPermissions(
                    activity, // your activity
                    GOOGLE_FIT_PERMISSIONS_REQUEST_CODE,
                    GoogleSignIn.getLastSignedInAccount(context),
                    fitnessOptions);
        } else {
            Log.d("googlefit", "googlefit initialized");
        }
    };

    public static synchronized GoogleFit getInstance(Context context, Activity activity) { // synchronized ==> avoid multithreading side effects
        if (INSTANCE == null) {
            INSTANCE = new GoogleFit(context, activity);
        }
        return(INSTANCE);
    }
}
