package com.cahyadesthian.thenotes.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import com.cahyadesthian.thenotes.db.DatabaseContract.NoteColumns.Companion.TABLE_NAME
import com.cahyadesthian.thenotes.db.DatabaseContract.NoteColumns.Companion._ID

/**
 * STEP 4
 * buat DML paleng
 * */
class NoteHelper(context: Context) {

    //1
    private var databaseHelper: DatabaseHelper = DatabaseHelper(context)
    private lateinit var database : SQLiteDatabase
    //1


    //3
    /*
    * Membuka dan menutup database
    * */
    @Throws(SQLiteException::class)
    fun open() {
        database=databaseHelper.writableDatabase
    }

    fun close() {
        databaseHelper.close()

        if(database.isOpen) database.close()
    }
    //3



    //4
    /*
    *
    * metode untuk CRUD
    * */
    //untuk mengmbil data
    fun queryAll(): Cursor {
        return database.query(
            DATABASE_TABLE,
            null,
            null,
            null,
            null,
            null,
            "$_ID ASC"
        )
    }

    //untuk mengambil dengan id tertentu
    fun queryById(id: String): Cursor {
        return database.query(
            DATABASE_TABLE,
            null,
            "$_ID = ?",
            arrayOf(id),
            null,
            null,
            null,
            null
        )
    }


    //untuk menyimpan data
    //insert
    fun insert(values: ContentValues?): Long {
        return database.insert(DATABASE_TABLE, null,values)
    }

    //untuk memperbarui data
    fun update(id: String, values: ContentValues?) : Int {
        return database.update(DATABASE_TABLE,values, "$_ID = ?", arrayOf(id))
    }


    //untuk menghapus data
    fun deleteById(id:String) : Int {
        return database.delete(DATABASE_TABLE, "$_ID = $id", null)
    }
    //4


    companion object {
        //1
        private const val DATABASE_TABLE = TABLE_NAME
        //1

        //2
        //metode unutk inisiasi database
        private var INSTANCE: NoteHelper? = null
        fun getInstace(context: Context): NoteHelper =
            INSTANCE ?: synchronized(this) {
                INSTANCE?: NoteHelper(context)
            }
        //2

    }

}