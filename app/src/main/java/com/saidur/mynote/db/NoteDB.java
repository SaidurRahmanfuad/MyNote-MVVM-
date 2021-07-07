package com.saidur.mynote.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.saidur.mynote.dao.NotesDao;
import com.saidur.mynote.entity.Notes;

@Database(entities = {Notes.class},version = 1)
public abstract class NoteDB extends RoomDatabase{

    public abstract NotesDao notesDao();
    public static final String tablename="Notes_table";
    public static volatile NoteDB instance;
    public static NoteDB getDBinstance(final Context c)
    {

        if(instance==null)
        synchronized (NoteDB.class)
        {
              instance=Room.databaseBuilder(c.getApplicationContext(),
                      NoteDB.class,tablename)
                      .fallbackToDestructiveMigration()
                      .build();
        }
        return instance;

    }

}
