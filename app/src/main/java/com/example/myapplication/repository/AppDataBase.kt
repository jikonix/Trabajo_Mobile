package com.example.myapplication.repository



import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.Interface.DAOUsuarios
import com.example.myapplication.Model.Usuario
import com.example.myapplication.Interface.DAOServicios
import com.example.myapplication.Model.Servicio
import com.example.myapplication.Interface.DAOSolicitudes
import com.example.myapplication.Model.Solicitud

@Database(entities = [Usuario::class, Servicio::class, Solicitud::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun usuarioDao(): DAOUsuarios
    abstract fun servicioDao(): DAOServicios
    abstract fun solicitudDao(): DAOSolicitudes
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