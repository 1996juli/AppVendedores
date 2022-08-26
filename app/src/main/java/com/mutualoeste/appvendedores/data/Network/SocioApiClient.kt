package com.mutualoeste.appvendedores.data.Network

import com.mutualoeste.appvendedores.data.model.SocioModel
import retrofit2.Response
import retrofit2.http.*


interface SocioApiClient {
    @GET("Vendedores/{pass}")
    suspend fun Login(@Path ("pass") pass : String) : Response<Int>

    @GET("Vendedores/")
    suspend fun GetSocio(@Query("dni") dni: String) : Response<SocioModel>

    @GET("Vendedores/correo")
    suspend fun SendEmail(@Query("SocioNombre") SocioNombre: String,
                          @Query("idVendedorEmisor") idVendedorEmisor: String ) : Response<Boolean>
}


