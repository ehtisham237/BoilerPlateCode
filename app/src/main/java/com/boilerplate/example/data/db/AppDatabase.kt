package com.boilerplate.example.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.boilerplate.example.data.db.dao.UserDao
import com.boilerplate.example.data.db.entity.User
import com.boilerplate.example.utils.LogUtil


@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
    companion object {
        const val DATABASE_NAME = "MeterApp.db"

        var rdc: Callback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                LogUtil.i("Database created!")
            }

            override fun onOpen(db: SupportSQLiteDatabase) {
                // do something every time database is open
            }
        }
    }
}