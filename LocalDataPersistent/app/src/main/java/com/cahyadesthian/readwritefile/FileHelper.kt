package com.cahyadesthian.readwritefile

import android.content.Context


//untuk tempat medtode menyimpan dan membaca data

internal object FileHelper {

    /*
    metode openFileOutput berasal dari FileOutputStream
    *
    * */
        fun writeToFile(fileModel: FileModel, context: Context) {
            context.openFileOutput(fileModel.filename, Context.MODE_PRIVATE).use {
                it.write(fileModel.data?.toByteArray())
            }
        }

    /*
    * metode openFileInput ini ada dari FileInputStream
    * */
        fun readFromFile(context: Context, fileName: String) : FileModel {
            val fileModel = FileModel()
            fileModel.filename = fileName
            fileModel.data = context.openFileInput(fileName).bufferedReader().useLines { lines ->
                lines.fold("") {some,text ->
                    "$some\n$text"
                }
            }
            return fileModel
        }

}

