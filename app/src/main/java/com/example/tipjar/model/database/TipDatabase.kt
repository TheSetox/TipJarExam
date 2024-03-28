package com.example.tipjar.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tipjar.model.database.dao.TipJarDaos
import com.example.tipjar.model.entity.PaymentHistory

@Database(entities = [PaymentHistory::class], version = 1, exportSchema = false)
abstract class TipDatabase : RoomDatabase(), TipJarDaos {
    companion object {
        @Volatile
        private var tipDataBase: TipDatabase? = null

        fun getInstance(context: Context): TipDatabase {
            synchronized(this) {
                var instance = tipDataBase

                if (instance == null) {
                    instance =
                        Room.databaseBuilder(
                            context.applicationContext,
                            TipDatabase::class.java,
                            "tip_jar_database",
                        )
                            .fallbackToDestructiveMigration()
                            .build()
                    tipDataBase = instance
                }
                return instance
            }
        }
    }
}
