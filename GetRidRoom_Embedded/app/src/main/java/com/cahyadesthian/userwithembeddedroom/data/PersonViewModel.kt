package com.cahyadesthian.userwithembeddedroom.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PersonViewModel(application: Application): AndroidViewModel(application){

    private val theDao = PersonDatabase.getDatabase(application).personDao()

    private val theRepository = PersonRepository(theDao)

    val readPerson: LiveData<List<Person>> = theRepository.readPerson

    fun insertPerson(person: Person) {
        viewModelScope.launch(Dispatchers.IO) {
            theRepository.insertPerson(person)
        }
    }

}