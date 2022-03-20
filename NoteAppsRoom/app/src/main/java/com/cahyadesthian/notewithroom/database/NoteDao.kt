package com.cahyadesthian.notewithroom.database

import androidx.lifecycle.LiveData
import androidx.room.*


/**
 * STEP 2
 * Kelas ini nantinya digunakan untuk melakukan eksekusi quiring
 *
 * */
interface NoteDao {

    @Insert(onConflict =  OnConflictStrategy.IGNORE)
    fun insert(note:Note)

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note:Note)

    @Query("SELECT * from note ORDER BY id ASC")
    fun getAllNotes(): LiveData<List<Note>>


}