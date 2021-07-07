package com.saidur.mynote;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;

import com.saidur.mynote.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding amb;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        amb = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(amb.getRoot());
        //setContentView(R.layout.activity_main);


        initpopup();
        amb.addnotebtn.setOnClickListener(v -> {

        });
    }

    private void initpopup() {
        dialog.setContentView(R.layout.layout_insertnote);

    }
}