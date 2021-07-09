package com.saidur.mynote.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.saidur.mynote.entity.Notes;

import java.util.List;

@Dao
public interface NotesDao {
    @Query("Select * from Notes_table")
    LiveData<List<Notes>> getAllNotes();

    @Insert
    void InsertNote(Notes notes);

    @Query("Delete from Notes_table where id=:id")
    void deleteNote(int id);

    @Update
    void UpdateNote(Notes notes);

}
