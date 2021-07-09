package com.saidur.mynote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.saidur.mynote.databinding.ActivityMainBinding;
import com.saidur.mynote.entity.Notes;
import com.saidur.mynote.view.DeleteListener;
import com.saidur.mynote.view.adapters.AllnoteAdapter;
import com.saidur.mynote.viewmodel.NotesViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements DeleteListener {
    ActivityMainBinding amb;
    Dialog dialog;
    FloatingActionButton donebtn;
    Button clear_txt;
    ImageView pri_green, pri_yello, pri_red;
    TextInputEditText writenote, subttl, ttl;
    String subtitle, title, note, date;
    NotesViewModel notesViewModel;
    String priority = "1";

    AllnoteAdapter ana;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        amb = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(amb.getRoot());
        //setContentView(R.layout.activity_main);
        notesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);

        initpopup();
        amb.addnotebtn.setOnClickListener(v -> {
            dialog.show();
        });

        notesViewModel.getAllNotes.observe(MainActivity.this, new Observer<List<Notes>>() {
            @Override
            public void onChanged(List<Notes> notes) {
              if(notes!=null)
              {
                  amb.allnote.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
                  ana =new AllnoteAdapter(MainActivity.this,MainActivity.this);
                  ana.setAllNotes(notes);
                  amb.allnote.setAdapter(ana);


              }else {
                  Toast.makeText(MainActivity.this, "No note found", Toast.LENGTH_SHORT).show();
              }
            }
        });

    }

    private void initpopup() {
        dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.layout_insertnote);
        dialog.getWindow().setLayout(Toolbar.LayoutParams.MATCH_PARENT, Toolbar.LayoutParams.WRAP_CONTENT);

        writenote = dialog.findViewById(R.id.writenote);
        subttl = dialog.findViewById(R.id.subttl);
        ttl = dialog.findViewById(R.id.ttl);

        pri_green = dialog.findViewById(R.id.pgreen);
        pri_yello = dialog.findViewById(R.id.pyello);
        pri_red = dialog.findViewById(R.id.pred);
        donebtn = dialog.findViewById(R.id.donebtn);
        clear_txt = dialog.findViewById(R.id.clear_txt);
        date = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(new Date());

        donebtn.setOnClickListener(v -> {
            subtitle = subttl.getText().toString();
            title = ttl.getText().toString();
            note = writenote.getText().toString();

            Notes no = new Notes();
            no.notesTitle = title;
            no.notesSubtitle = subtitle;
            no.notesDate = date;
            no.notes = note;
            no.priority = priority;
            notesViewModel.insertNote(no);
            Toast.makeText(this, "Note Created!!", Toast.LENGTH_SHORT).show();

            dialog.dismiss();
        });
        clear_txt.setOnClickListener(v -> {
            subttl.setText("");
            ttl.setText("");
            writenote.setText("");
        });
        pri_green.setOnClickListener(v -> {
            priority = "1";
            pri_green.setImageResource(R.drawable.ic_done);
            pri_yello.setImageResource(0);
            pri_red.setImageResource(0);
        });
        pri_yello.setOnClickListener(v -> {
            priority = "2";
            pri_yello.setImageResource(R.drawable.ic_done);
            pri_green.setImageResource(0);
            pri_red.setImageResource(0);
        });
        pri_red.setOnClickListener(v -> {
            priority = "3";
            pri_red.setImageResource(R.drawable.ic_done);
            pri_green.setImageResource(0);
            pri_yello.setImageResource(0);
        });

    }

    @Override
    public void DeleteItem(int pos,int id) {
        notesViewModel.delNote(id);
        ana.notifyItemRemoved(pos);
        ana.notifyDataSetChanged();
    }
}