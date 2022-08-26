package com.mutualoeste.appvendedores.ui.View
import com.mutualoeste.appvendedores.ui.ViewModel.LoginViewModel
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mutualoeste.appvendedores.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import com.mutualoeste.appvendedores.data.Global

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        binding.btnlogin.setOnClickListener {
            loginViewModel.onCreate(binding.clave.text.toString()).observe(this , Observer {
                if(it != 0){
                    startActivity(Intent(this, MainActivity::class.java));
                    //.putExtra("idVendedor", it)) --> para pasar datos de un activity a otro activity
                    //SharedApp.prefs.name = it.toString();
                    Global.vendedorIdActual = it.toString();
                    binding.clave.text = null
                } else {
                    Toast.makeText(this, "Hubo un error", Toast.LENGTH_LONG).show()
                }
            });
        }
    }
}