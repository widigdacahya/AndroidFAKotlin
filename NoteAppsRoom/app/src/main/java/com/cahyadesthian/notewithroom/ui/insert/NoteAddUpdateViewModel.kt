package com.cahyadesthian.notewithroom.ui.insert

import android.app.Application
import androidx.lifecycle.ViewModel
import com.cahyadesthian.notewithroom.database.Note
import com.cahyadesthian.notewithroom.repository.NoteRepository


/**
 * STEP 6
 * sebagai penghubung antara Activity dengan Repository
 * */
class NoteAddUpdateViewModel(application: Application): ViewModel() {

    private val mNoteRepository: NoteRepository = NoteRepository(application)

    fun insert(note: Note) {
        mNoteRepository.insert(note)
    }

    fun update(note:Note) {
        mNoteRepository.update(note)
    }

    fun delete(note:Note) {
        mNoteRepository.delete(note)
    }


}