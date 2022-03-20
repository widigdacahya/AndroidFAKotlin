package com.cahyadesthian.thenotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.cahyadesthian.thenotes.adapter.NoteAdapter
import com.cahyadesthian.thenotes.databinding.ActivityMainBinding
import com.cahyadesthian.thenotes.db.NoteHelper
import com.cahyadesthian.thenotes.entity.Note
import com.cahyadesthian.thenotes.helper.MappingHelper
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

//20
//sebelumnya ini adalah pembuatan berkas xmlnya
//kemudian atur binding dan layout manger rv dan fixed size


//21
/*
* Kemudian karena kita sudah membuat halaman NoteAddUpdateActivity untuk menambah dan
* meng-edit data dengan Intent Result. Di MainActivity kita akan memanggil
* activity tersebut dan mendapatkan nilai result darinya.
*
* */


class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    //21
    private lateinit var adapter: NoteAdapter

    //21
    val resultLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->

        if(result.data != null) {
            //dipanggil saat request code berupa ADD
            when(result.resultCode) {
                NoteAddUpdateActivity.RESULT_ADD -> {
                    val note = result.data?.getParcelableExtra<Note>(NoteAddUpdateActivity.EXTRA_NOTE) as Note
                    note.let { adapter.addItem(it) }
                    mainBinding.rvNotesMainUI.smoothScrollToPosition(adapter.itemCount-1)
                    showSnackbarMessage("One item added succesfully..Yay!")
                }
                NoteAddUpdateActivity.RESULT_UPDATE -> {
                    val note = result.data?.getParcelableExtra<Note>(NoteAddUpdateActivity.EXTRA_NOTE) as Note
                    val position = result?.data?.getIntExtra(NoteAddUpdateActivity.EXTRA_POSITION,0) as Int
                    adapter.updateItem(position,note)
                    mainBinding.rvNotesMainUI.smoothScrollToPosition(position)
                    showSnackbarMessage("One item changed SUcessfully...aayayayaya!")
                }
                NoteAddUpdateActivity.RESULT_DELETE -> {
                    val position = result?.data?.getIntExtra(NoteAddUpdateActivity.EXTRA_POSITION,0) as Int
                    adapter.removeItem(position)
                    showSnackbarMessage("One item deleted :p")
                }
            }
        }

    }


    private fun showSnackbarMessage(message: String) {
        Snackbar.make(mainBinding.rvNotesMainUI, message, Snackbar.LENGTH_SHORT).show()
    }
    //21

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)


        supportActionBar?.title = "Notes"

        mainBinding.rvNotesMainUI.layoutManager = LinearLayoutManager(this)
        mainBinding.rvNotesMainUI.setHasFixedSize(true)



        //21
        adapter = NoteAdapter(object : NoteAdapter.OnItemClickCallback {

            override fun onItemClicked(selectedNote: Note?, position: Int?) {
                val intent = Intent(this@MainActivity, NoteAddUpdateActivity::class.java)
                intent.putExtra(NoteAddUpdateActivity.EXTRA_NOTE,selectedNote)
                intent.putExtra(NoteAddUpdateActivity.EXTRA_POSITION, position)
                resultLauncher.launch(intent)
            }

        })

        mainBinding.rvNotesMainUI.adapter = adapter

        mainBinding.fabAddMainUI.setOnClickListener {
            val intent = Intent(this@MainActivity, NoteAddUpdateActivity::class.java)
            resultLauncher.launch(intent)
        }

        //21
        /*
        * Setelah ini kita akan mengambil data dari method queryAll() yang ada di NoteHelper.
        * Namun, karena nanti di adapter kita akan menggunakan arraylist,
        * sedangkan di sini objek yang di kembalikan berupa Cursor, maka dari itu kita harus
        * mengonversi dari Cursor ke Arraylist. Kita akan membuat kelas pembantu untuk
        * menangani hal ini. Buat package baru bernama helper dan klik kanan pada package tersebut.
        * Pilih new Kotlin class/Java class dan beri nama sebagai MappingHelper
        * */


        //23
        //proses ambil data
        //loadNotesAsync()
        //commented caused of used in step 24_2



        //24_2
        if(savedInstanceState == null) {

            //proses ambil mengambild data
            loadNotesAsync()
        } else {
            val list = savedInstanceState.getParcelableArrayList<Note>(EXTRA_STATE)
            if(list!=null) {
                adapter.listNotes = list
            }
        }
        //24_2
    }

    //24_3

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(EXTRA_STATE, adapter.listNotes)
    }

    //24_3


    //23 ambild data dari database dengan bgthread
    /*
    * pemanggilan metode open() dan memulai interaksi databse
    * dan close() saat activity ditutup agar tidak terjadi memory leak
    * */
    private fun loadNotesAsync() {
        lifecycleScope.launch {
            mainBinding.pbMainUI.visibility = View.VISIBLE
            val noteHelper = NoteHelper.getInstace(applicationContext)
            noteHelper.open()
            val defferedNotes = async(Dispatchers.IO) {
                val cursor = noteHelper.queryAll()
                MappingHelper.mapCursorToArrayList(cursor)
            }
            mainBinding.pbMainUI.visibility = View.INVISIBLE
            val notes = defferedNotes.await()
            if(notes.size>0) {
                adapter.listNotes = ArrayList()
                showSnackbarMessage("Saat ini, belum ada data")
            }
            noteHelper.close()
        }
    }
    //23

    /*
    * STEP 24
    * Sampai sini kita sudah bisa menjalankan aplikasi yang sudah kita buat,
    * namun data tidak akan terjaga ketika layar dirotasi (configuration change).
    * Maka kita akan  menerapkan metode onSaveInstanceState. Pada metode ini Anda akan
    * menyimpan ArrayList, jadi pada saat rotasi, layar berubah dan aplikasi tidak
    * memanggil ulang proses mengambil data dari database.
    * Kemudian pada metode onCreate kita ambil data dari saveInstanceState jika ada.
    * */


    //24_1
    companion object {
        private const val EXTRA_STATE = "EXTRA_STATE"
    }
    //24_1



}