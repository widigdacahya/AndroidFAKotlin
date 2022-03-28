package com.cahyadesthian.userwithembeddedroom.data

import androidx.lifecycle.LiveData

class PersonRepository(private val personDao : PersonDao) {

    val readPerson: LiveData<List<Person>> = personDao.readPerson()

    suspend fun insertPerson(person: Person) {
        personDao.insertPerson(person)
    }

}