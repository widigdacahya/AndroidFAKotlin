package com.cahyadesthian.notewithroom.database

import androidx.lifecycle.LiveData
import androidx.room.*


/**
 * STEP 2
 * Kelas ini nantinya digunakan untuk melakukan eksekusi quiring
 *
 * */

@Dao
interface NoteDao {


    /**
     * Menggunakan @Insert untuk query insert pada database sesuai dengan input entitas yang
     * dimasukkan, contohnya jika pada perintah di atas adalah Note.
     * Sedangkan kode OnConflictStrategy.IGNORE digunakan jika data yang dimasukkan sama,
     * maka dibiarkan saja.
     * */
    @Insert(onConflict =  OnConflictStrategy.IGNORE)
    fun insert(note:Note)


    @Update
    fun update(note: Note)


    /**
     * Menggunakan @Delete untuk menghapus sebuah item tertentu, contohnya pada baris
     * tersebut akan menghapus sebuah item note dengan cara mencocokkan item mana yang sama.
     * */
    @Delete
    fun delete(note:Note)
    

    @Query("SELECT * from note ORDER BY id ASC")
    fun getAllNotes(): LiveData<List<Note>>


}

/**
 * Kode di atas digunakan untuk melakukan aksi CRUD(Create, Read, Update dan Delete).
 * Sebuah kelas interface yang diberi sebuah annotation @dao akan menjadi sebuah kelas
 * Dao secara otomatis.
 *
 * */