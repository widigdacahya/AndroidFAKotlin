package com.cahyadesthian.learnexercisethree.data

import androidx.lifecycle.LiveData
import com.cahyadesthian.learnexercisethree.data.database.FavUser
import com.cahyadesthian.learnexercisethree.data.database.FavUserDao

class FavUserRepository(private val favUserDao: FavUserDao) {

    val readAllFavUser : LiveData<List<FavUser>> = favUserDao.readAllFavUser()

    suspend fun addToFav(favUser: FavUser) {
        favUserDao.addToFav(favUser)
    }

    suspend fun isThereAnyFav(): Int = favUserDao.isThereAnyFav()

    suspend fun checkUser(id: Int) : Int = favUserDao.checkUser(id)

    suspend fun deleteFromFav(id:Int) {
        favUserDao.deleteFromFav(id)
    }

    suspend fun deleteAllUser() {
        favUserDao.deleteAllUser()
    }

}