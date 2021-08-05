package com.saidur.mynote.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.saidur.mynote.R;
import com.saidur.mynote.databinding.ActivityNoteviewBinding;

public class NoteviewActivity extends AppCompatActivity {
ActivityNoteviewBinding anvb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        anvb= ActivityNoteviewBinding.inflate(getLayoutInflater());
        setContentView(anvb.getRoot());
        //setContentView(R.layout.activity_noteview);


        Intent catchdata=getIntent();
        anvb.ttl.setText(catchdata.getStringExtra("Title"));
        anvb.subttl.setText(catchdata.getStringExtra("Subtitle"));
        anvb.nte.setText(catchdata.getStringExtra("Notes"));
        anvb.date.setText(catchdata.getStringExtra("Date"));
        final String pt = catchdata.getStringExtra("Priority");
        //check priority
        switch (pt) {
            case "1":
                anvb.prioty.setBackgroundResource(R.drawable.circle_green);
                break;
            case "2":
                anvb.prioty.setBackgroundResource(R.drawable.circle_yellow);
                break;
            case "3":
                anvb.prioty.setBackgroundResource(R.drawable.circle_red);
                break;
        }

    }
}