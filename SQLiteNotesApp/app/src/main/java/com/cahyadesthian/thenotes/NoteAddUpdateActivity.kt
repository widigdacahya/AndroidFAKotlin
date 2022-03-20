package com.cahyadesthian.thenotes

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.cahyadesthian.thenotes.databinding.ActivityNoteAddUpdateBinding
import com.cahyadesthian.thenotes.db.DatabaseContract
import com.cahyadesthian.thenotes.db.DatabaseContract.NoteColumns.Companion.DATE
import com.cahyadesthian.thenotes.db.NoteHelper
import com.cahyadesthian.thenotes.entity.Note
import java.text.SimpleDateFormat
import java.util.*

/*
* [STEP 9]
* sebelumnya membuat tampilan xml
* dan membuat menu untuk hapus
* */

class NoteAddUpdateActivity : AppCompatActivity(), View.OnClickListener {

    //9
    private var isEdit = false
    private var note: Note? = null
    private var position: Int = 0
    private lateinit var noteHelper: NoteHelper

    private lateinit var addNoteActBinding: ActivityNoteAddUpdateBinding
    //9

    //10
    companion object {
        const val EXTRA_NOTE = "extra_note"
        const val EXTRA_POSITION = "extra_position"
        const val RESULT_ADD = 101
        const val RESULT_UPDATE = 201
        const val RESULT_DELETE = 301
        const val ALERT_DIALOG_CLOSE = 10
        const val ALERT_DIALOG_DELETE = 20
    }
    //10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addNoteActBinding = ActivityNoteAddUpdateBinding.inflate(layoutInflater)
        setContentView(addNoteActBinding.root)

        //11
        noteHelper = NoteHelper.getInstace(applicationContext)
        noteHelper.open()
        //11

        //12//
        note = intent.getParcelableExtra(EXTRA_NOTE)
        if(note!= null) {
            position = intent.getIntExtra(EXTRA_POSITION,0)
            isEdit = true
        } else {
            note = Note()
        }

        val actionBarTitle : String
        val btnTitle: String


        if(isEdit) {
            actionBarTitle = "Ubah"
            btnTitle = "Update"

            note?.let {
                addNoteActBinding.edtTitleAddUI.setText(it.title)
                addNoteActBinding.edtDescAddUI.setText(it.description)
            }
        } else {
            actionBarTitle = "Tambah"
            btnTitle = "Simpan"
        }

        supportActionBar?.title = actionBarTitle
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        addNoteActBinding.btnSubmitAddUI.text = btnTitle
        //12//

        //13
        addNoteActBinding.btnSubmitAddUI.setOnClickListener(this)
        //13
    }

    override fun onClick(v: View) {

        //14
        if(v.id == R.id.btn_submit_addUI) {
            val title = addNoteActBinding.edtTitleAddUI.text.toString().trim()
            val description = addNoteActBinding.edtDescAddUI.text.toString().trim()

            if(title.isEmpty()) {
                addNoteActBinding.edtTitleAddUI.error = "Field can not be blank"
                return
            }

            note?.title = title
            note?.description = description

            val intent = Intent()
            intent.putExtra(EXTRA_NOTE,note)
            intent.putExtra(EXTRA_POSITION, position)

            val values = ContentValues()
            values.put(DatabaseContract.NoteColumns.TITLE, title)
            values.put(DatabaseContract.NoteColumns.DESCRIPTION,description)

            if(isEdit) {
                val result = noteHelper.update(note?.id.toString(), values).toLong()
                if(result>0) {
                    setResult(RESULT_UPDATE, intent)
                    finish()
                } else {
                    Toast.makeText(this@NoteAddUpdateActivity, "Gagal Update Data", Toast.LENGTH_SHORT).show()
                }
            } else {
                note?.date = getCurrentDate()
                values.put(DATE, getCurrentDate())
                val result = noteHelper.insert(values)

                if(result>0) {
                    note?.id = result.toInt()
                    setResult(RESULT_ADD, intent)
                    finish()
                } else {
                    Toast.makeText(this@NoteAddUpdateActivity, "Gagal menamba data", Toast.LENGTH_SHORT).show()
                }

            }

        }
        //14


    }


    //15
    private fun getCurrentDate() : String {
        val dateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault())
        val date = Date()

        return dateFormat.format(date)
    }
    //15

    //16
    //override metode onCreateOptionsMenu untuk memanggil menu_form.xml.
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if(isEdit) menuInflater.inflate(R.menu.menu_form,menu)
        return super.onCreateOptionsMenu(menu)
    }
    //16

    //17
    //override metode onOptionsItemSelecteduntuk memberikan fungsi ketika menu diklik
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_delete -> showAlertDialog(ALERT_DIALOG_DELETE)
            android.R.id.home -> showAlertDialog(ALERT_DIALOG_CLOSE)
        }
        return super.onOptionsItemSelected(item)
    }
    //17

    /*
    * 18
    * Pada saat menekan tombol back (kembali), kita ingin memunculkan AlertDialog.
    * Panggil metode onBackPressed.
    *
    * */

    override fun onBackPressed() {
        showAlertDialog(ALERT_DIALOG_CLOSE)
    }
    //18


    //19
    /*
    *  buat metode showAlertDialog untuk memunculkan dialognya dan mengembalikan nilai
    * result untuk diterima halaman MainActivity nantinya.
    * */
    private fun showAlertDialog(type: Int) {
        val isDialogClose = type == ALERT_DIALOG_CLOSE
        val dialogTitle: String
        val dialogMessage : String

        if(isDialogClose) {
            dialogTitle = "Batal"
            dialogMessage = "Apakah kamu yaqin ingin membatalkan perubahan pada form?"
        } else {
            dialogTitle = "Hapus Note"
            dialogMessage = "Are you sure want to delete this item???"
        }


        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle(dialogTitle)
        alertDialogBuilder
            .setMessage(dialogMessage)
            .setCancelable(false)
            .setPositiveButton("Iya") {_, _ ->
                if(isDialogClose) {
                    finish()
                } else {
                    val result = noteHelper.deleteById(note?.id.toString()).toLong()
                    if(result>0) {
                        val intent = Intent()
                        intent.putExtra(EXTRA_POSITION,position)
                        setResult(RESULT_DELETE,intent)
                        finish()
                    } else {
                        Toast.makeText(this@NoteAddUpdateActivity, "Gagal menghapus data", Toast.LENGTH_SHORT).show()

                    }
                }

            }
            .setNegativeButton("Tidak") {dialog, _ -> dialog.cancel()}

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()


    }
    //19


}

/**
 *
 * Tanggung jawab utama NoteAddUpdateActivity adalah sebagai berikut:
 * > Menyediakan form untuk melakukan proses input data.
 * > Menyediakan form untuk melakukan proses pembaruan data.
 * > Jika pengguna berada pada proses pembaruan data maka setiap kolom
 *    pada form sudah terisi otomatis dan ikon untuk hapus yang berada
 *    pada sudut kanan atas ActionBar ditampilkan dan berfungsi untuk menghapus data.
 * > Sebelum proses penghapusan data, dialog konfirmasi akan tampil. Pengguna akan ditanya
 *   terkait penghapusan yang akan dilakukan.
 * > Jika pengguna menekan tombol back (kembali) baik pada ActionBar maupun peranti,
 *   maka akan tampil dialog konfirmasi sebelum menutup halaman.
 * > Masih ingat materi di mana sebuah Activity menjalankan Activity lain dan
 *   menerima nilai balik pada metode registerForActivityResult()? Tepatnya di
 *   Activity yang dijalankan dan ditutup dengan menggunakan parameter RESULTCODE.
 *
 * */