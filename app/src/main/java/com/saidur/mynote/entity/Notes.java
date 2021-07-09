package com.saidur.mynote.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Notes_table")
public class Notes {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "notes_title")
    public String notesTitle;
    @ColumnInfo(name = "notes_subtitle")
    public String notesSubtitle;
    @ColumnInfo(name = "notes_date")
    public String notesDate;
    @ColumnInfo(name = "notes_notes")
    public String notes;
    @ColumnInfo(name = "notes_priority")
    public String priority;

/*    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNotesTitle() {
        return notesTitle;
    }

    public void setNotesTitle(String notesTitle) {
        this.notesTitle = notesTitle;
    }

    public String getNotesSubtitle() {
        return notesSubtitle;
    }

    public void setNotesSubtitle(String notesSubtitle) {
        this.notesSubtitle = notesSubtitle;
    }

    public String getNotesDate() {
        return notesDate;
    }

    public void setNotesDate(String notesDate) {
        this.notesDate = notesDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }*/
}
