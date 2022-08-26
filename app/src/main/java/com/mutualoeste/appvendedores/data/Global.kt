package com.mutualoeste.appvendedores.data

import android.app.Application
import java.time.LocalDate

class Global : Application() {
    companion object {
        var vendedorIdActual: String? = ""
        var vendedorIdSocio: String = ""
        var fechaBajaSocio : LocalDate? = null;
        var NombreSocio : String = ""
        var estadoSocio: String? = ""
        var messageErrorDNI: Boolean = false
    }
}