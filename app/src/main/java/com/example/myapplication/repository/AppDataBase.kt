package com.example.myapplication.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.myapplication.Interface.DAOServicios
import com.example.myapplication.Interface.DAOSolicitudes
import com.example.myapplication.Interface.DAOUsuarios
import com.example.myapplication.Model.Servicio
import com.example.myapplication.Model.Solicitud
import com.example.myapplication.Model.Usuario
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Usuario::class, Servicio::class, Solicitud::class], version = 4) // Versión actualizada a 4
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
                    //.fallbackToDestructiveMigration() // Ojo: esto borra los datos en cada cambio de versión.
                    .addCallback(AppDatabaseCallback(context))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class AppDatabaseCallback(private val context: Context) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let {
                database ->
                CoroutineScope(Dispatchers.IO).launch {
                    val servicioDao = database.servicioDao()
                    // Limpiar servicios viejos por si acaso
                    // servicioDao.deleteAll() // Descomentar si es necesario limpiar
                    servicioDao.insertServicio(Servicio(nombre = "Cambio de Aceite", precio = 50000.0))
                    servicioDao.insertServicio(Servicio(nombre = "Limpieza Completa", precio = 30000.0))
                    servicioDao.insertServicio(Servicio(nombre = "Cambio de Repuestos", precio = 75000.0))
                    servicioDao.insertServicio(Servicio(nombre = "Mantención General", precio = 100000.0))
                    servicioDao.insertServicio(Servicio(nombre = "Revisión de Frenos", precio = 40000.0))
                    servicioDao.insertServicio(Servicio(nombre = "Cambio de Vidrios", precio = 120000.0))

                    val usuarioDao = database.usuarioDao()
                    val adminUser = Usuario(
                        correo = "admin@admin.cl",
                        password = "admin",
                        run = "1-1",
                        nombre = "Administrador",
                        numero = "987654321",
                        rol = true
                    )
                    usuarioDao.insert(adminUser)
                }
            }
        }
    }
}
