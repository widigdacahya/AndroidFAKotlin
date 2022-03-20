package com.cahyadesthian.notewithroom.ui.insert

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.cahyadesthian.notewithroom.R
import com.cahyadesthian.notewithroom.database.Note
import com.cahyadesthian.notewithroom.databinding.ActivityNoteAddUpdateBinding
import com.cahyadesthian.notewithroom.helper.DateHelper
import com.cahyadesthian.notewithroom.helper.ViewModelFactory

/**
 * STEP 5 membuat sturktur UI. insert dan main didalamnya
 * lalu membuat empty activity ini
 *
 * Setelah membuat Activity, kita akan buat terlebih dahulu kelas
 * ViewModel sebagai penghubung antara Activity dengan Repository.
 * Buatlah kelas di dalam package insert dengan nama
 * NoteAddUpdateViewModel
 * */
class NoteAddUpdateActivity : AppCompatActivity() {


    /*
    * STEP 15
    * penghubungan NoteAddUpdateViewModel dengan NoteAddUpdateActivity.
    * */
    //step 15_1
    private lateinit var noteAddUpdateViewModel: NoteAddUpdateViewModel

    private var _activityNoteAddUpdateBinding: ActivityNoteAddUpdateBinding? = null
    private val binding get() = _activityNoteAddUpdateBinding
    //step 15_1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //15_2
        _activityNoteAddUpdateBinding = ActivityNoteAddUpdateBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        //15_2

        //15_3
        noteAddUpdateViewModel = obtainViewModel(this@NoteAddUpdateActivity)
        //15_3


        /**
         * STEP 16
         * untuk memfasiltiasi penambaham, pembaharuan, dan penghapusan item
         * */
        note = intent.getParcelableExtra(EXTRA_NOTE)
        if(note!= null) isEdit = true else note=Note()

        val actionBarTitle: String
        val btnTitle: String

        if(isEdit) {
            actionBarTitle = getString(R.string.change)
            btnTitle = getString(R.string.update)
            if(note != null) {
                note?.let { note ->
                    binding?.edtTitleAddUI?.setText(note.title)
                    binding?.edtDescAddUI?.setText(note.description)
                }
            }
        } else {
            actionBarTitle = getString(R.string.add)
            btnTitle = getString(R.string.save)
        }

        supportActionBar?.title = actionBarTitle
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding?.btnSubmitAddUI?.text = btnTitle
        //STEP 16, step selanjutnya , step 17 untuk memfasilitasi saat vtn di klik



        //step 17
        binding?.btnSubmitAddUI?.setOnClickListener {
            val title = binding?.edtTitleAddUI?.text.toString().trim()
            val description = binding?.edtDescAddUI?.text.toString().trim()
            when {
                title.isEmpty() -> {
                    binding?.edtTitleAddUI?.error = getString(R.string.empty)
                }
                description.isEmpty() -> {
                    binding?.edtDescAddUI?.error = getString(R.string.empty)
                }
                else -> {
                    note.let { note ->
                        note?.title = title
                        note?.description = description
                    }
                    if(isEdit) {
                        noteAddUpdateViewModel.update(note as Note)
                        showToast(getString(R.string.changed))
                    } else {
                        note.let { note ->
                            note?.date = DateHelper.getCurrentDate()
                        }
                        noteAddUpdateViewModel.insert(note as Note)
                        showToast(getString(R.string.added))
                    }
                    finish()
                }
            }

        }
        //step 17, langkah selanjutnya untuk penghapusan item dari database

    }


    /**
     * STEP 18
     * things needed to delete item
     * form database
     * */
    //18_1
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        if(isEdit) {
            menuInflater.inflate(R.menu.menu_form, menu)
        }

        return super.onCreateOptionsMenu(menu)
    }
    //18_1



    //18_2
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.action_delete -> showAlertDialog(ALERT_DIALOG_DELETE)
            android.R.id.home -> showAlertDialog(ALERT_DIALOG_CLOSE)
        }

        return super.onOptionsItemSelected(item)
    }
    //18_2




    //18_3
    private fun showAlertDialog(type: Int) {

        val isDialogClose = type == ALERT_DIALOG_CLOSE
        val dialogTitle: String
        val dialogMessage: String

        if(isDialogClose) {
            dialogTitle = getString(R.string.cancel)
            dialogMessage = getString(R.string.message_cancel)
        } else {
            dialogMessage = getString(R.string.message_delete)
            dialogTitle = getString(R.string.delete)
        }

        val alertDialogBuilder = AlertDialog.Builder(this)
        with(alertDialogBuilder) {
            setTitle(dialogTitle)
            setMessage(dialogMessage)
            setCancelable(false)
            setPositiveButton(getString(R.string.yes)) { _, _ ->
                if(!isDialogClose) {
                    noteAddUpdateViewModel.delete(note as Note)
                    showToast(getString(R.string.deleted))
                }

                finish()
            }
            setNegativeButton(getString(R.string.no)) {dialog, _ -> dialog.cancel()}
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()

    }
    //18_3




    //18_4

    override fun onBackPressed() {
        showAlertDialog(ALERT_DIALOG_CLOSE)
    }

    //18_4
    //after this make a note adapter



    //15_4
    private fun obtainViewModel(activity: AppCompatActivity): NoteAddUpdateViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(NoteAddUpdateViewModel::class.java)
    }
    //15_4

    //15_5

    override fun onDestroy() {
        super.onDestroy()
        _activityNoteAddUpdateBinding = null
    }

    //15_5



    /**
     * STEP 17
     * untuk memfasiltisai saat btn submit di klik
     * didalamnya kny ada showTOast, jadi mari kita buat
     * showToastnya terlebih dahulu
     * */
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    /*
    * STEP 14
    * Tambahkan kode berikut untuk menginisialisasi
    * view yang ada di layout-nya.
    *
    * */
    companion object {
        const val EXTRA_NOTE = "extra_note"
        const val ALERT_DIALOG_CLOSE = 10
        const val ALERT_DIALOG_DELETE = 20
    }

    private var isEdit = false
    private var note: Note? = null
    //14




}