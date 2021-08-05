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

    public LiveData<List<Notes>> getPrtywiseList;
    public LiveData<List<Notes>> getDatewiseList;

    public LiveData<List<Notes>> getHiList;
    public LiveData<List<Notes>> getMidList;
    public LiveData<List<Notes>> getLowList;


    public NoteRepo(Application application) {
        NoteDB noteDB=NoteDB.getDBinstance(application);
        notesDao=noteDB.notesDao();
        getAllnotes=notesDao.getAllNotes();
        getPrtywiseList=notesDao.getSearchNotespriority("");
        getDatewiseList=notesDao.getSearchNotesdate("");

        getHiList=notesDao.getHinote(1);
        getMidList=notesDao.getMidnote(2);
        getLowList=notesDao.getLownote(3);
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

   /* public void searpriorityNotes(String codes)
    {
        notesDao.getSearchNotespriority(codes);
    }*/

}
