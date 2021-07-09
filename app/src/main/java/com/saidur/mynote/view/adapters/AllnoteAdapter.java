package com.saidur.mynote.view.adapters;

import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.saidur.mynote.R;
import com.saidur.mynote.entity.Notes;
import com.saidur.mynote.viewmodel.NotesViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;

public class AllnoteAdapter extends RecyclerView.Adapter<AllnoteAdapter.Anvh> {
    List<Notes> noteList;
    Context c;
    Application application;

    public AllnoteAdapter(Context c) {
        this.c = c;
    }

    public void setAllNotes(List<Notes> noteList) {
        this.noteList = noteList;
    }

    @NonNull
    @Override
    public AllnoteAdapter.Anvh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.recy_addnote, parent, false);
        Anvh anvh = new Anvh(v);
        return anvh;
    }

    @Override
    public void onBindViewHolder(@NonNull AllnoteAdapter.Anvh holder, int position) {
        Notes notes = noteList.get(position);
        NotesViewModel nvm = new NotesViewModel(application);
        if (noteList != null) {
            holder.ntitle.setText(notes.notesTitle);
            holder.nsubtitle.setText(notes.notesSubtitle);
            holder.nnote.setText(notes.notes);
            holder.date.setText(notes.notesDate);
            final String pt = notes.priority;
            switch (pt) {
                case "1":
                    holder.priority.setBackgroundResource(R.drawable.circle_green);
                    break;
                case "2":
                    holder.priority.setBackgroundResource(R.drawable.circle_yellow);
                    break;
                case "3":
                    holder.priority.setBackgroundResource(R.drawable.circle_red);
                    break;
            }

            holder.edit.setOnClickListener(v -> {
                //for showing popup
                Dialog d = new Dialog(c);
                d.setContentView(R.layout.layout_updatenote);
                d.getWindow().setLayout(Toolbar.LayoutParams.MATCH_PARENT, Toolbar.LayoutParams.WRAP_CONTENT);
                //declare all aspects of popup
                FloatingActionButton updonebtn;
                Button upclear_txt;
                ImageView uppri_green, uppri_yello, uppri_red;
                TextInputEditText upwritenote, upsubttl, upttl;
                final String update;


                //find all aspects
                upwritenote = d.findViewById(R.id.upwritenote);
                upsubttl = d.findViewById(R.id.upsubttl);
                upttl = d.findViewById(R.id.upttl);

                uppri_green = d.findViewById(R.id.uppgreen);
                uppri_yello = d.findViewById(R.id.uppyello);
                uppri_red = d.findViewById(R.id.uppred);
                updonebtn = d.findViewById(R.id.updonebtn);
                upclear_txt = d.findViewById(R.id.upclear_txt);
                update = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(new Date());

                //set data from prev stored
                upwritenote.setText(notes.notes);
                upttl.setText(notes.notesTitle);
                upsubttl.setText(notes.notesSubtitle);

                //check which priority
                switch (pt) {
                    case "1":
                        uppri_green.setImageResource(R.drawable.ic_done);
                        uppri_yello.setImageResource(0);
                        uppri_red.setImageResource(0);
                        break;
                    case "2":
                        uppri_yello.setImageResource(R.drawable.ic_done);
                        uppri_green.setImageResource(0);
                        uppri_red.setImageResource(0);
                        break;
                    case "3":
                        uppri_red.setImageResource(R.drawable.ic_done);
                        uppri_green.setImageResource(0);
                        uppri_yello.setImageResource(0);
                        break;
                }
                //clear input
                upclear_txt.setOnClickListener(v1 -> {
                    upsubttl.setText("");
                    upttl.setText("");
                    upwritenote.setText("");
                });
                holder.updpriority=pt;
                uppri_green.setOnClickListener(v1 -> {
                    holder.updpriority = "1";
                    uppri_green.setImageResource(R.drawable.ic_done);
                    uppri_yello.setImageResource(0);
                    uppri_red.setImageResource(0);
                });
                uppri_yello.setOnClickListener(v1 -> {
                    holder.updpriority = "2";
                    uppri_yello.setImageResource(R.drawable.ic_done);
                    uppri_green.setImageResource(0);
                    uppri_red.setImageResource(0);
                });
                uppri_red.setOnClickListener(v1 -> {
                    holder.updpriority = "3";
                    uppri_red.setImageResource(R.drawable.ic_done);
                    uppri_green.setImageResource(0);
                    uppri_yello.setImageResource(0);
                });
                updonebtn.setOnClickListener(v1 -> {
                    Notes no = new Notes();
                    no.id = notes.id;
                    no.notesTitle = upttl.getText().toString();
                    no.notesSubtitle = upsubttl.getText().toString();
                    no.notesDate = update;
                    no.notes = upwritenote.getText().toString();
                    no.priority = holder.updpriority;
                    nvm.updateNote(no);
                    Toast.makeText(c, "Updated!!", Toast.LENGTH_SHORT).show();
                    d.dismiss();
                });

                d.show();

            });
        }

    }

    @Override
    public int getItemCount() {
        if (noteList != null) {
            return noteList.size();
        } else {
            return 0;
        }

    }

    public class Anvh extends RecyclerView.ViewHolder {
        TextView ntitle, nsubtitle, nnote, date, edit;
        View priority;
        String updpriority;
        public Anvh(@NonNull View v) {
            super(v);
            ntitle = v.findViewById(R.id.ttl);
            nsubtitle = v.findViewById(R.id.subttl);
            nnote = v.findViewById(R.id.nte);
            date = v.findViewById(R.id.date);
            edit = v.findViewById(R.id.edit);
            priority = v.findViewById(R.id.prioty);
        }
    }
}
