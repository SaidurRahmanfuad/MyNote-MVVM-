package com.saidur.mynote.viewmodel;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.saidur.mynote.entity.Notes;
import com.saidur.mynote.repository.NoteRepo;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {
    public NoteRepo noteRepo;
    public LiveData<List<Notes>> getAllNotes;
    public LiveData<List<Notes>> prtnote;
    public LiveData<List<Notes>> gethinote;
    public LiveData<List<Notes>> getmidnote;
    public LiveData<List<Notes>> getlownote;
    public LiveData<List<Notes>> datenote;

    public NotesViewModel(@NonNull Application application) {
        super(application);
         noteRepo=new NoteRepo(application);
         getAllNotes=noteRepo.getAllnotes;
         prtnote=noteRepo.getPrtywiseList;
         datenote=noteRepo.getDatewiseList;

        gethinote=noteRepo.getHiList;
        getmidnote=noteRepo.getMidList;
        getlownote=noteRepo.getLowList;
    }

    public void insertNote(Notes notes)
    {
        noteRepo.insertNotes(notes);
    }
   public void updateNote(Notes notes)
    {
        noteRepo.updateNotes(notes);
    }
   public void delNote(int ids)
    {
        noteRepo.deleteNotes(ids);
    }

   /* public void srchprtywiseNote(String code)
    {
        noteRepo.searpriorityNotes(code);
    }*/
}
