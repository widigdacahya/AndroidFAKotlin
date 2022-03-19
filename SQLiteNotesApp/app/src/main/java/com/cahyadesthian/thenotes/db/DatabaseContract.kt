package com.cahyadesthian.thenotes.db

import android.provider.BaseColumns

/**
 * STEP 2
 * Kelas contract ini akan digunakan untuk
 * mempermudah akses nama
 * tabel dan nama kolom di dalam database kita.
 * */
internal class DatabaseContract {

    internal class NoteColumns: BaseColumns {


        companion object {
            const val  TABLE_NAME = "note"
            const val _ID = "_id"
            const val TITLE = "title"
            const val DESCRIPTION = "description"
            const val DATE = "date"
        }
    }

}