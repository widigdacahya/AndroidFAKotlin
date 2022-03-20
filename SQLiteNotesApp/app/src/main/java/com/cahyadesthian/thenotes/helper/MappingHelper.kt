package com.cahyadesthian.thenotes.helper

import android.database.Cursor
import com.cahyadesthian.thenotes.db.DatabaseContract
import com.cahyadesthian.thenotes.entity.Note

/*
* 22
* untuk berbuat dengan queryAll() yang ada di NoteHelper
* adapter nanti akan pake arraylist sedangkan
* objek yang dikembalikan berupa cursor
* maka harus ada yang bisa mengoversi dari cursor  ke arraylist
* Inilah kelas yang bisa melakukan hall itu
* UwU
*
* */

//22
object MappingHelper {

    fun mapCursorToArrayList(notesCursor: Cursor?) : ArrayList<Note> {
        val notesList = ArrayList<Note>()

        notesCursor?.apply {
            while(moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(DatabaseContract.NoteColumns._ID))
                val title = getString(getColumnIndexOrThrow(DatabaseContract.NoteColumns.TITLE))
                val description = getString(getColumnIndexOrThrow(DatabaseContract.NoteColumns.DESCRIPTION))
                val date = getString(getColumnIndexOrThrow(DatabaseContract.NoteColumns.DATE))
                notesList.add(Note(id,title,description,date))
            }
        }
        return notesList
    }

}
//22

/*
* 23
* Selanjutnya kita mulai untuk mengambil data dari database dengan menggunakan
* background thread dan memanfaatkan NoteHelper yang sudah kita buat.
* Sekarang di MainActivity mari kita inisialisasi terlebih dahulu dan
* panggil metode open() untuk memulai interaksi database dan close()
* saat Activity ditutup supaya tidak terjadi memory leak.
* */