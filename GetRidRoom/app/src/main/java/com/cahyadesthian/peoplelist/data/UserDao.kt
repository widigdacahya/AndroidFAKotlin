package com.cahyadesthian.peoplelist.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cahyadesthian.peoplelist.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM USER_TABLE ORDER BY ID ASC")
    fun readAllData(): LiveData<List<User>>

    @Update
    suspend fun updateUser(user:User)

    @Delete
    suspend fun deleteUser(user:User)

    @Query("DELETE FROM user_table")
    suspend fun deleteAllUser()


}