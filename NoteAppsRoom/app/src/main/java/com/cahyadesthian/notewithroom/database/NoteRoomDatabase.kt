package com.cahyadesthian.notewithroom.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


/**
 *
 * STEP 3
 * Kelas ini akan digunakan untuk menginisialisasi database dalam aplikasi.
 * */

//step 3_1
@Database(entities = [Note::class], version = 1)
abstract class NoteRoomDatabase : RoomDatabase() {

    //step 3_2
    abstract fun noteDao(): NoteDao
    //step 3_2


    //step 3_3
    companion object {
        @Volatile
        private var INSTANCE: NoteRoomDatabase? = null

        @JvmStatic
        fun getDatabase(context:Context): NoteRoomDatabase {
            if(INSTANCE == null) {
                synchronized(NoteRoomDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                    NoteRoomDatabase::class.java, "note_database")
                        .build()
                    /**
                     * Kode di atas digunakan untuk membuat atau membangun database pada
                     * aplikasi dengan nama note_database. Dengan begitu, Anda bisa memanfaatkannya
                     * untuk digunakan di kelas lain,
                     * pada project ini Anda memakainya di kelas NoteRepository.
                     * */
                }
            }
            return INSTANCE as NoteRoomDatabase
        }

    }
    //step 3_3
    /*
    * Anda sudah membuat beberapa kelas yang berfungsi sebagai komponen dari Room
    * yaitu entitas, dao, dan database.
    * */

}

/**
 * Dengan memberikan annotation @Database dan memberikan turunan kelas dari RoomDatabase
 * maka sebuah kelas abstract tersebut sudah dikatakan sebagai RoomDatabase.
 *
 * */