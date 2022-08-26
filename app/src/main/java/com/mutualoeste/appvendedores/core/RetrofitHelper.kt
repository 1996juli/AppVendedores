package com.mutualoeste.appvendedores.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://b88f0bed3a9e.sn.mynetname.net:2929/api/")
            //.baseUrl("http://localhost:59014/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}