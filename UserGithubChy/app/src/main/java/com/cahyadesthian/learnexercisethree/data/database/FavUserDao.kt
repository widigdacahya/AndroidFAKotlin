package com.cahyadesthian.learnexercisethree.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface FavUserDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addToFav(favUser: FavUser)

    @Query("SELECT * FROM FAV_USER ORDER BY id ASC")
    fun readAllFavUser(): LiveData<List<FavUser>>

    @Query("SELECT COUNT(*) FROM FAV_USER")
    suspend fun isThereAnyFav(): Int

    @Query("SELECT COUNT(*) FROM FAV_USER WHERE FAV_USER.id = :id")
    suspend fun checkUser(id: Int): Int

    @Query("DELETE FROM FAV_USER WHERE FAV_USER.id=:id")
    suspend fun deleteFromFav(id: Int)

    @Query("DELETE FROM FAV_USER")
    suspend fun deleteAllUser()
}