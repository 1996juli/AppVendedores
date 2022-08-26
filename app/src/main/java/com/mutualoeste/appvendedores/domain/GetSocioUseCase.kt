package com.mutualoeste.appvendedores.domain
import com.mutualoeste.appvendedores.data.SocioRepository
import javax.inject.Inject

class GetSocioUseCase @Inject constructor(private val repository: SocioRepository){
    suspend operator fun invoke( dni : String) = repository.GetSocio(dni)
}

class LoginUseCase @Inject constructor(private val repository: SocioRepository){
    suspend operator fun invoke( clave : String) = repository.Login(clave)
}

class SendEmailUseCase @Inject constructor(private val repository: SocioRepository){
    suspend operator fun invoke( SocioNombre : String, idVendedorEmisor : String ) = repository.SendEmail(SocioNombre, idVendedorEmisor)
}



