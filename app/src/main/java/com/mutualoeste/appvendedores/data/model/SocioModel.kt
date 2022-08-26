package com.mutualoeste.appvendedores.data.model

import com.google.gson.annotations.SerializedName
import java.sql.Date
import java.util.*

data class SocioModel(
    @SerializedName("socioID") val SocioID: Int?,
    @SerializedName("nombreYApellido") val NombreYapellido: String?,
    @SerializedName("dni") val Documento: String?,
    @SerializedName("titular") val Titular: String?,
    @SerializedName("activo") val Activo: String?,
    @SerializedName("fechaBaja") val FechaBaja: String?,
    @SerializedName("motivoBaja") val MotivoBaja: String?,
    @SerializedName("plan") val Plan: String?,
    @SerializedName("cuota") val Cuota: String?,
    @SerializedName("zona") val Zona: String?,
    @SerializedName("nombreZona") val NombreZona: String?,
    @SerializedName("telefono") val Telefono: String?,
    @SerializedName("vendedor") val Vendedor: String?,
    @SerializedName("vendedorID") val VendedorID: String?,
)



