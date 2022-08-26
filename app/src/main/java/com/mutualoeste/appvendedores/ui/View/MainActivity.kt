package com.mutualoeste.appvendedores.ui.View
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.mutualoeste.appvendedores.R
import com.mutualoeste.appvendedores.data.Global
import com.mutualoeste.appvendedores.databinding.ActivityLoginBinding
import com.mutualoeste.appvendedores.databinding.ActivityMainBinding
import com.mutualoeste.appvendedores.ui.ViewModel.SocioViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.*
import kotlin.concurrent.schedule

@AndroidEntryPoint
class MainActivity : AppCompatActivity() , OnFragmentActionsListener {

    private lateinit var binding: ActivityMainBinding
    private val socioViewModel: SocioViewModel by viewModels()

    //Para extraer los datos traidos desde otra activity
    //val idVendedorIntent : Intent = intent;
    //val IdVendedor = idVendedorIntent.getIntExtra("idVendedor", 0);

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        binding.loading.visibility = View.GONE;

        socioViewModel.isLoading.observe(this, Observer {
            if(it){
                binding.fragmetSocioContainer.visibility = View.GONE
                binding.loading.visibility = View.VISIBLE
            } else {
                binding.loading.visibility = View.GONE
                binding.fragmetSocioContainer.visibility = View.VISIBLE
            }
        })

        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                searchByName(query!!.lowercase())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true;
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean{
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.salir ->{
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun searchByName(dni: String) {
        Global.fechaBajaSocio = null
        Global.estadoSocio = ""
        Global.vendedorIdSocio = ""
        Global.NombreSocio = ""
        socioViewModel.onCreate(dni);
        val fragmentTransaction : FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragmetSocioContainer, SocioFragment())
        fragmentTransaction.commit();
    }
}
