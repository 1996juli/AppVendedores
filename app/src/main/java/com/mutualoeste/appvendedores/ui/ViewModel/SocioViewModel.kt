package com.mutualoeste.appvendedores.ui.ViewModel

import android.app.PendingIntent.getActivity
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mutualoeste.appvendedores.data.Global
import com.mutualoeste.appvendedores.data.model.SocioModel
import com.mutualoeste.appvendedores.domain.GetSocioUseCase
import com.mutualoeste.appvendedores.domain.SendEmailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class SocioViewModel @Inject constructor(
    private val getSocioUseCase: GetSocioUseCase,
    private val sendEmailUseCase: SendEmailUseCase,
) : ViewModel(){

    val SocioModel = MutableLiveData<SocioModel?>()
    val isLoading = MutableLiveData<Boolean>(false)

    private val statusMessageEmail = MutableLiveData<Event<String>>()
    val message : LiveData<Event<String>>
        get() = statusMessageEmail

    fun onCreate( dni: String) {
        viewModelScope.launch {
            isLoading.postValue(true);
            val result = getSocioUseCase(dni)
            /*if (result.Documento == null){ Global.messageErrorDNI = true}*/
            SocioModel.postValue(result)
            if (result.FechaBaja != null){
                Global.fechaBajaSocio = LocalDate.parse(result.FechaBaja)
            } else
                Global.fechaBajaSocio = null;
                Global.estadoSocio = result.Activo.toString()
                Global.vendedorIdSocio = result.VendedorID.toString()
                Global.NombreSocio = result.NombreYapellido.toString()
                isLoading.postValue(false)
            }
    }

    fun SendEmailDerivacion (SocioNombre : String, idVendedorEmisor : String )  {
        viewModelScope.launch {
            val result = sendEmailUseCase(SocioNombre, idVendedorEmisor);
            if(result)
                statusMessageEmail.value = Event("Notificación enviada con éxito")
        }
    }

    /*@JvmName("getMessageErrorDni1")
    fun getMessageErrorDni() : Boolean {
        return Global.messageErrorDNI;
    }*/

}

/*Timer("SettingUp", false).schedule(500) {
    isLoading.postValue(true);
}*/




