package com.saidur.mynote.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.saidur.mynote.dao.NotesDao;
import com.saidur.mynote.db.NoteDB;
import com.saidur.mynote.entity.Notes;

import java.util.List;

public class NoteRepo {
    public NotesDao notesDao;
    public LiveData<List<Notes>> getAllnotes;

    public NoteRepo(Application application) {
        NoteDB noteDB=NoteDB.getDBinstance(application);
        notesDao=noteDB.notesDao();
        getAllnotes=notesDao.getAllNotes();
    }

    public void insertNotes(Notes notes)
    {
        notesDao.InsertNote(notes);
    }

   public void updateNotes(Notes notes)
    {
        notesDao.UpdateNote(notes);
    }
    public void deleteNotes(int id)
    {
        notesDao.deleteNote(id);
    }

}
