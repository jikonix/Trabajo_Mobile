package com.example.myapplication.repository



import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.Interface.DAOUsuarios
import com.example.myapplication.Model.Usuario
@Database(entities=[Usuario::class],version=1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun usuarioDao(): DAOUsuarios

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}