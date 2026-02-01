package com.example.myapplication.Interface

import com.example.myapplication.Model.Usuario
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

}