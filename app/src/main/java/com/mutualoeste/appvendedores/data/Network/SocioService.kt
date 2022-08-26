package com.mutualoeste.appvendedores.data.Network
import androidx.lifecycle.ViewModel
import com.mutualoeste.appvendedores.data.model.SocioModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class SocioService @Inject constructor(val api : SocioApiClient) :ViewModel()  {

    suspend fun GetSocio( dni: String): SocioModel {
        return withContext(Dispatchers.IO) {
            val response = api.GetSocio(dni)
            val s = SocioModel(NombreYapellido = null, Documento = null, SocioID = null, Titular = null,
                Activo = null, FechaBaja = null, Plan = null, Cuota = null, Vendedor = null , MotivoBaja = null,
                VendedorID = "", NombreZona = "", Telefono = "", Zona = "")
            response.body() ?: s
        }
    }

    suspend fun Login( clave: String): Int {
        return withContext(Dispatchers.IO) {
            val response = api.Login(clave)
            (response.body() ?: 0)
        }
    }

    suspend fun SendEmail( SocioNombre : String, idVendedorEmisor : String ): Boolean {
        return withContext(Dispatchers.IO) {
            val response = api.SendEmail(SocioNombre, idVendedorEmisor)
            response.body() ?: false
        }
    }
}


