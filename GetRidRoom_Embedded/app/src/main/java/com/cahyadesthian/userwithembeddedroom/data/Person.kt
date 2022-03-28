package com.cahyadesthian.userwithembeddedroom.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "person_table")
data class Person(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val firstName: String,
    val middleName: String,
    val lastName: String,
    val age: Int,
    @Embedded
    val adress:Address

)


data class Address(
    val streetName: String,
    val streenNumber: Int
)