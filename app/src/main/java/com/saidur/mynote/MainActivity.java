package com.saidur.mynote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.saidur.mynote.databinding.ActivityMainBinding;
import com.saidur.mynote.entity.Notes;
import com.saidur.mynote.store.MyPref;
import com.saidur.mynote.view.DeleteListener;
import com.saidur.mynote.view.adapters.AllnoteAdapter;
import com.saidur.mynote.viewmodel.NotesViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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

    DatePickerDialog datePickerDialog1, datePickerDialog2;
    MyPref myPref;
    HashMap<String, String> stylename=new HashMap<>();
    String styleName;

    public static String chk;
    int ck=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        amb = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(amb.getRoot());
        myPref=new MyPref(MainActivity.this);
        stylename =myPref.getStyle();
        styleName=stylename.get(MyPref.Style);

        //setContentView(R.layout.activity_main);
        notesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);

        initpopup();
        amb.addnotebtn.setOnClickListener(v -> {
            dialog.show();
        });
        setStyle();
        notesViewModel.getAllNotes.observe(MainActivity.this, new Observer<List<Notes>>() {
            @Override
            public void onChanged(List<Notes> notes) {
                if (notes != null) {
                    switch (styleName)
                    {
                        case "":
                            loadStyle(0);
                            break;
                        case "grid":
                            loadStyle(1);
                            break;
                        default:loadStyle(0);
                    }

                } else {
                    Toast.makeText(MainActivity.this, "No note found", Toast.LENGTH_SHORT).show();
                }
            }
        });

        filter();

        initDatePicker();
        // initDatePicker();
        amb.selectst.setOnClickListener(v -> {
            datePickerDialog1.show();
        });
        amb.selectend.setOnClickListener(v -> {
            datePickerDialog2.show();
        });

    }

    public void setStyle() {

        switch (styleName)
        {
            case "":
                loadStyle(0);
                break;
            case "grid":
                loadStyle(1);
                break;
            default:loadStyle(0);
        }

    }

    public void setAdapterList(List<Notes> notes) {
        amb.allnote.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        ana = new AllnoteAdapter(MainActivity.this, MainActivity.this);
        ana.setAllNotes(notes);
        amb.allnote.setAdapter(ana);
    }

    public void setAdapterGrid(List<Notes> notes) {
        amb.allnote.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
        ana = new AllnoteAdapter(MainActivity.this, MainActivity.this);
        ana.setAllNotes(notes);
        amb.allnote.setAdapter(ana);
    }

    private void filter() {
        amb.nofilter.setBackgroundResource(R.drawable.shape_itemfilter_selected);
        amb.nofilter.setOnClickListener(v -> {
            loaddata(0);
            amb.nofilter.setBackgroundResource(R.drawable.shape_itemfilter_selected);
            amb.high.setBackgroundResource(R.drawable.shape_itemfilterhigh);
            amb.high.setTextColor(Color.WHITE);
            amb.mid.setBackgroundResource(R.drawable.shape_itemfiltermid);
            amb.mid.setTextColor(Color.WHITE);
            amb.low.setBackgroundResource(R.drawable.shape_itemfilterlow);
            amb.low.setTextColor(Color.WHITE);

            amb.datewise.setBackgroundResource(R.drawable.shape_searchdatewise);
            amb.datewise.setTextColor(Color.WHITE);
            amb.filterdatelay.setVisibility(View.GONE);
        });
        amb.high.setOnClickListener(v -> {

            loaddata(1);
            amb.high.setBackgroundResource(R.drawable.shape_itemfilter_selected);
            amb.high.setTextColor(Color.BLACK);

            amb.mid.setBackgroundResource(R.drawable.shape_itemfiltermid);
            amb.mid.setTextColor(Color.WHITE);

            amb.low.setBackgroundResource(R.drawable.shape_itemfilterlow);
            amb.low.setTextColor(Color.WHITE);

            amb.nofilter.setBackgroundResource(R.drawable.shape_itemfilterno);

            amb.datewise.setBackgroundResource(R.drawable.shape_searchdatewise);
            amb.datewise.setTextColor(Color.WHITE);
            amb.filterdatelay.setVisibility(View.GONE);

            //callSearchPriwise("1");

        });
        amb.mid.setOnClickListener(v -> {
            loaddata(2);
            amb.mid.setBackgroundResource(R.drawable.shape_itemfilter_selected);
            amb.mid.setTextColor(Color.BLACK);

            amb.low.setBackgroundResource(R.drawable.shape_itemfilterlow);
            amb.low.setTextColor(Color.WHITE);

            amb.high.setBackgroundResource(R.drawable.shape_itemfilterhigh);
            amb.high.setTextColor(Color.WHITE);
            amb.nofilter.setBackgroundResource(R.drawable.shape_itemfilterno);

            amb.datewise.setBackgroundResource(R.drawable.shape_searchdatewise);
            amb.datewise.setTextColor(Color.WHITE);
            amb.filterdatelay.setVisibility(View.GONE);
            //callSearchPriwise("2");
        });
        amb.low.setOnClickListener(v -> {
            loaddata(3);
            amb.low.setBackgroundResource(R.drawable.shape_itemfilter_selected);
            amb.low.setTextColor(Color.BLACK);

            amb.mid.setBackgroundResource(R.drawable.shape_itemfiltermid);
            amb.mid.setTextColor(Color.WHITE);

            amb.high.setBackgroundResource(R.drawable.shape_itemfilterhigh);
            amb.high.setTextColor(Color.WHITE);
            amb.nofilter.setBackgroundResource(R.drawable.shape_itemfilterno);

            amb.datewise.setBackgroundResource(R.drawable.shape_searchdatewise);
            amb.datewise.setTextColor(Color.WHITE);
            amb.filterdatelay.setVisibility(View.GONE);

            //callSearchPriwise("3");
        });
        amb.datewise.setOnClickListener(v -> {
            amb.datewise.setBackgroundResource(R.drawable.shape_itemfilter_selected);
            amb.datewise.setTextColor(Color.BLACK);

            amb.mid.setBackgroundResource(R.drawable.shape_itemfiltermid);
            amb.mid.setTextColor(Color.WHITE);

            amb.high.setBackgroundResource(R.drawable.shape_itemfilterhigh);
            amb.high.setTextColor(Color.WHITE);

            amb.low.setBackgroundResource(R.drawable.shape_itemfilterlow);
            amb.low.setTextColor(Color.WHITE);

            amb.nofilter.setBackgroundResource(R.drawable.shape_itemfilterno);
            amb.filterdatelay.setVisibility(View.VISIBLE);
        });

        amb.stylelist.setOnClickListener(v -> {
            //TODO:List
            ck=0;
            amb.stylelist.setBackgroundResource(R.drawable.shape_selected);
            amb.stylelist.setTextColor(Color.BLACK);
            amb.stylegrid.setBackgroundResource(R.drawable.shape_searchdatewise);
            myPref.cleare();
            myPref.saveAction("list");

            loadStyle(0);
        });
        amb.stylegrid.setOnClickListener(v -> {
            //TODO:Grid itemview
            ck=1;
            myPref.cleare();
            myPref.saveAction("grid");
            amb.stylegrid.setBackgroundResource(R.drawable.shape_selected);
            amb.stylegrid.setTextColor(Color.BLACK);
            amb.stylelist.setBackgroundResource(R.drawable.shape_searchdatewise);
            loadStyle(1);
        });
   /*     amb.stylelist.setOnClickListener(v -> {
            //setAdapterList();
            style = "list";

            notesViewModel.getAllNotes.observe(MainActivity.this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    if (notes != null) {
                        setAdapterList(notes);
                    } else {
                        Toast.makeText(MainActivity.this, "No note found", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            amb.stylegrid.setBackgroundResource(R.drawable.shape_searchdatewise);
        });
        amb.stylegrid.setOnClickListener(v -> {
            style = "grid";
            amb.stylegrid.setBackgroundResource(R.drawable.shape_itemfilter_selected);
            amb.stylegrid.setTextColor(Color.BLACK);
            amb.stylelist.setBackgroundResource(R.drawable.shape_searchdatewise);
            Toast.makeText(this, "Grid select", Toast.LENGTH_SHORT).show();
            notesViewModel.getAllNotes.observe(MainActivity.this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    if (notes != null) {
             *//*     amb.allnote.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
                  ana =new AllnoteAdapter(MainActivity.this,MainActivity.this);
                  ana.setAllNotes(notes);
                  amb.allnote.setAdapter(ana);*//*
                        setAdapterGrid(notes);

                    } else {
                        Toast.makeText(MainActivity.this, "No note found", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        });*/
    }

    private void loadStyle(int s) {
        if (s == 0) {
            notesViewModel.getAllNotes.observe(MainActivity.this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    if (notes != null) {
                            setAdapterList(notes);
                        amb.stylelist.setBackgroundResource(R.drawable.shape_selected);
                        amb.stylelist.setTextColor(Color.BLACK);
                        amb.stylegrid.setBackgroundResource(R.drawable.shape_searchdatewise);
                    } else {
                        Toast.makeText(MainActivity.this, "No note found", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else{
            notesViewModel.getAllNotes.observe(MainActivity.this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    if (notes != null) {
                        setAdapterGrid(notes);
                        amb.stylegrid.setBackgroundResource(R.drawable.shape_selected);
                        amb.stylegrid.setTextColor(Color.BLACK);
                        amb.stylelist.setBackgroundResource(R.drawable.shape_searchdatewise);
                    } else {
                        Toast.makeText(MainActivity.this, "No note found", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void loaddata(int i) {
        if (i == 0) {
            notesViewModel.getAllNotes.observe(MainActivity.this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    if (notes != null) {
                        if (styleName.equals("list")||styleName.isEmpty()) {
                            setAdapterList(notes);
                        } else {
                            setAdapterGrid(notes);
                        }

                    } else {
                        Toast.makeText(MainActivity.this, "No note found", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else if (i == 1) {

            notesViewModel.gethinote.observe(MainActivity.this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    if (notes != null) {
                        //setAdapter(notes);
                        if (styleName.equals("list")||styleName.isEmpty()) {
                            setAdapterList(notes);
                        } else {
                            setAdapterGrid(notes);
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "No note found", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else if (i == 2) {
            notesViewModel.getmidnote.observe(MainActivity.this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    if (notes != null) {
                        // setAdapter(notes);
                        if (styleName.equals("list")||styleName.isEmpty()) {
                            setAdapterList(notes);
                        } else {
                            setAdapterGrid(notes);
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "No note found", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else if (i == 3) {
            notesViewModel.getlownote.observe(MainActivity.this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    if (notes != null) {
                        // setAdapter(notes);
                        if (styleName.equals("list")||styleName.isEmpty()) {
                            setAdapterList(notes);
                        } else {
                            setAdapterGrid(notes);
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "No note found", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
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

    private void initDatePicker() {

        DatePickerDialog.OnDateSetListener dateSetListener1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day, month, year);
                amb.startdate.setText(parseDateandTime(date, "dd-MM-yyyy"));
            }
        };
        DatePickerDialog.OnDateSetListener dateSetListener2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day, month, year);
                amb.enddate.setText(parseDateandTime(date, "dd-MM-yyyy"));
                callSearchDatewise();
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog1 = new DatePickerDialog(this, style, dateSetListener1, year, month, day);
        datePickerDialog2 = new DatePickerDialog(this, style, dateSetListener2, year, month, day);


    }

    private void callSearchDatewise() {
    }

    /*  private void callSearchPriwise(String code) {
          notesViewModel.srchprtywiseNote(code);
      }*/
    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month) {
        if (month == 1)
            return "JAN";
        if (month == 2)
            return "FEB";
        if (month == 3)
            return "MAR";
        if (month == 4)
            return "APR";
        if (month == 5)
            return "MAY";
        if (month == 6)
            return "JUN";
        if (month == 7)
            return "JUL";
        if (month == 8)
            return "AUG";
        if (month == 9)
            return "SEP";
        if (month == 10)
            return "OCT";
        if (month == 11)
            return "NOV";
        if (month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }

    public String parseDateandTime(String dates, String outputPattern) {
        // String inputPattern = "yyyy-MM-dd";
        String inputPattern = "MMM dd yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(dates);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    @Override
    public void DeleteItem(int pos, int id) {
        notesViewModel.delNote(id);
        ana.notifyItemRemoved(pos);
        ana.notifyDataSetChanged();
    }


}