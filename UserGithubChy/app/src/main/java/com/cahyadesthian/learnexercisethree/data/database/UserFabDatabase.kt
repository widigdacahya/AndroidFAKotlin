package com.cahyadesthian.learnexercisethree.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FavUser::class], version = 1,exportSchema = false)
abstract class UserFabDatabase: RoomDatabase() {

    abstract fun favUserDao(): FavUserDao

    companion object{
        @Volatile
        var INSTANCE: UserFabDatabase?= null

        @JvmStatic
        fun getDatabase(context: Context): UserFabDatabase? {
            if(INSTANCE == null) {
                synchronized(UserFabDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,UserFabDatabase::class.java, "user_fav_database").build()
                }
            }
            return INSTANCE
        }
    }



}