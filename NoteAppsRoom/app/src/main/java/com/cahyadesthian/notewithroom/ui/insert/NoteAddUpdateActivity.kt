package com.cahyadesthian.notewithroom.ui.insert

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cahyadesthian.notewithroom.R

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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_add_update)
    }
}