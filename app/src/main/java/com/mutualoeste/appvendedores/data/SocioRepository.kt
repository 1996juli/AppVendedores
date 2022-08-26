package com.mutualoeste.appvendedores.data

import com.mutualoeste.appvendedores.data.model.SocioModel
import com.mutualoeste.appvendedores.data.Network.SocioService
import javax.inject.Inject

class SocioRepository @Inject constructor(
    private val api: SocioService,
) {

    suspend fun GetSocio(dni: String): SocioModel {
        return api.GetSocio(dni)
    }

    suspend fun Login(clave: String): Int {
        val resultado = api.Login(clave)
        return resultado;
    }

    suspend fun SendEmail(SocioNombre : String, idVendedorEmisor : String ): Boolean {
        val resultado = api.SendEmail(SocioNombre, idVendedorEmisor )
          return resultado;
    }
}