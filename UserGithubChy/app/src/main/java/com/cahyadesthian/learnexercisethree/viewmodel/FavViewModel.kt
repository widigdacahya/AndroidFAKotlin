package com.cahyadesthian.learnexercisethree.viewmodel
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.cahyadesthian.learnexercisethree.data.FavUserRepository
import com.cahyadesthian.learnexercisethree.data.database.FavUser
import com.cahyadesthian.learnexercisethree.data.database.UserFabDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/**
 * Dealing data with Favourite screen
 * it will deal also with the
 * db by repository (kind of better practice to separate concern of app)
 */

class FavViewModel(application: Application): AndroidViewModel(application) {

    private val favUserRepository : FavUserRepository
    init {
        val favUserDao = UserFabDatabase.getDatabase(application)?.favUserDao()
        favUserRepository = favUserDao?.let { FavUserRepository(it) }!!
    }

    fun deleteAllUser() {
        viewModelScope.launch(Dispatchers.IO) {
            favUserRepository.deleteAllUser()
        }
    }

    fun getFavUser(): LiveData<List<FavUser>>? {
        return favUserRepository.readAllFavUser
    }

    suspend fun isThereAny(): Int? = favUserRepository.isThereAnyFav()

}