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

    //filter
    @Query("Select * from Notes_table order by notes_priority desc")
    //start 3=red,2=yellow,1=green
    LiveData<List<Notes>> getLowtoHighNotes();

    @Query("Select * from Notes_table order by notes_priority asc")
        //start 1=green,2=yellow,3=red,
    LiveData<List<Notes>> getHightoLowNotes();

    //priority wise
   /* @Query("Select * from Notes_table where notes_priority =:code") //start 1=green,2=yellow,3=red,
    LiveData<List<Notes>> getSearchNotespriority(String code);*/

    @Query("Select * from Notes_table where notes_priority = notes_priority")
        //start 1=green,2=yellow,3=red,
    LiveData<List<Notes>> getSearchNotespriority();

    @Query("Select * from Notes_table where notes_priority like :prt")
        //start 1=green,2=yellow,3=red,
    LiveData<List<Notes>> getSearchNotespriority(String prt);

    //date wise
    @Query("Select * from Notes_table where notes_date like :dt")
    //start 1=green,2=yellow,3=red,
    LiveData<List<Notes>> getSearchNotesdate(String dt);


    //hi wise
    @Query("Select * from Notes_table where notes_priority like :hi")
    //start 1=green,2=yellow,3=red,
    LiveData<List<Notes>> getHinote(int hi);
    //mid wise
    @Query("Select * from Notes_table where notes_priority like :mid")
    //start 1=green,2=yellow,3=red,
    LiveData<List<Notes>> getMidnote(int mid);
    //low wise
    @Query("Select * from Notes_table where notes_priority like :low")
    //start 1=green,2=yellow,3=red,
    LiveData<List<Notes>> getLownote(int low);
}
