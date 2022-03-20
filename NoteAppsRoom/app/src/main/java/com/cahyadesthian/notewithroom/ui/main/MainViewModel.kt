package com.cahyadesthian.notewithroom.ui.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cahyadesthian.notewithroom.database.Note
import com.cahyadesthian.notewithroom.repository.NoteRepository


/**
 * STEP 7
 *
 * */
class MainViewModel(application: Application): ViewModel() {

    private val mNoteRepository: NoteRepository = NoteRepository(application)

    fun getAllNotes(): LiveData<List<Note>> = mNoteRepository.getAllNotes()
    //step 8 memnghias activity_main.xml, membuat item layout, dan xml update form
}