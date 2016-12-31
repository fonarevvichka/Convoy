package com.fonarev.convoy;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class placeHolderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_holder);

        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#ff757575"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
    }
}
