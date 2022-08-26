package com.mutualoeste.appvendedores.ui.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mutualoeste.appvendedores.domain.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val LoginUseCase: LoginUseCase,
) : ViewModel(){

    fun onCreate( clave : String) : LiveData<Int> {
        val resultado = MutableLiveData<Int>()
        viewModelScope.launch {
           val login = LoginUseCase(clave)
            resultado.postValue(login)
        }
        return resultado;
    }
}