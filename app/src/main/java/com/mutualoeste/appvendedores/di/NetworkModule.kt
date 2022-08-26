package com.mutualoeste.appvendedores.di
import com.mutualoeste.appvendedores.data.Network.SocioApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("http://b88f0bed3a9e.sn.mynetname.net:2929/api/")
            //.baseUrl("http://localhost:59014/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideQuoteApiClient(retrofit: Retrofit):SocioApiClient{
        return retrofit.create(SocioApiClient::class.java)
    }
}