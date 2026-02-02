package com.example.myapplication.Interface

import com.example.myapplication.Model.Usuario
import com.example.myapplication.Model.Servicio
import com.example.myapplication.Model.Solicitud
import retrofit2.Response
import retrofit2.http.*;

interface ApiService {
    @GET("/users")
    suspend fun getUsers(): Response<List<Usuario>>;

    @GET("/users/{username}")
    suspend fun getUser(@Path("correo") correo: String): Response<Usuario>;

    @POST("/users")
    suspend fun createUser(@Body user: Usuario): Response<Usuario>

    @DELETE
    suspend fun deleteUser(@Query("correo") correo: String): Response<Usuario>

    @PUT
    suspend fun updateUser(@Query("correo") correo: String, @Body user: Usuario): Response<Usuario>
// servicios
    @GET("/users")
    suspend fun getServices(): Response<List<Servicio>>;

    @GET("/users/{username}")
    suspend fun getService(@Path("id") id: Int): Response<Servicio>;

    @POST("/users")
    suspend fun createServices(@Body user: Servicio): Response<Servicio>

    @DELETE
    suspend fun deleteServices(@Query("id") id: Int): Response<Servicio>

    @PUT
    suspend fun updateServices(@Query("id") id: Int, @Body user: Servicio): Response<Servicio>
    //solicitudes

    @GET("/users")
    suspend fun getSolis(): Response<List<Solicitud>>;

    @GET("/users/{username}")
    suspend fun getSoli(@Path("id") id: Int): Response<Solicitud>;

    @POST("/users")
    suspend fun createSoli(@Body user: Solicitud): Response<Solicitud>

    @DELETE
    suspend fun deleteSoli(@Query("id") id: Int): Response<Solicitud>

    @PUT
    suspend fun updateSoli(@Query("id") id: Int, @Body user: Solicitud): Response<Solicitud>



}