package com.cahyadesthian.learnexercisethree.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "FAV_USER")
data class FavUser(
    @PrimaryKey
    val ID: Int,
    val LOGIN: String,
    val AVATAR_URL : String
): Serializable
