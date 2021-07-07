package com.saidur.mynote.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.saidur.mynote.entity.Notes;
import com.saidur.mynote.repository.NoteRepo;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {
    NoteRepo noteRepo;

    LiveData<List<Notes>> getAllNotes;
    public NotesViewModel(@NonNull Application application) {
        super(application);
         noteRepo=new NoteRepo(application);
         getAllNotes=noteRepo.getAllnotes;
    }

    void insertNote(Notes notes)
    {
        noteRepo.insertNotes(notes);
    }
    void updateNote(Notes notes)
    {
        noteRepo.updateNotes(notes);
    }
    void delNote(int ids)
    {
        noteRepo.deleteNotes(ids);
    }
}
