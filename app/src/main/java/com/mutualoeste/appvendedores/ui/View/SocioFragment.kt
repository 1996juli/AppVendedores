package com.mutualoeste.appvendedores.ui.View

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.mutualoeste.appvendedores.R
import com.mutualoeste.appvendedores.data.Global
import com.mutualoeste.appvendedores.databinding.FragmentSocio2Binding
import com.mutualoeste.appvendedores.ui.ViewModel.SocioViewModel
import java.time.LocalDate
import java.util.*
import kotlin.concurrent.schedule


class SocioFragment : Fragment() {

    private val socioViewModel: SocioViewModel by activityViewModels()
    private var _binding : FragmentSocio2Binding? = null;
    private val binding get() = _binding!!;

    private lateinit var nombreView : TextView
    private lateinit var estadoView : TextView
    private lateinit var motivoBView : TextView
    private lateinit var planView : TextView
    private lateinit var cuotaView : TextView
    private lateinit var vendedorView : TextView
    private lateinit var zonaView : TextView
    private lateinit var telefonoView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSocio2Binding.inflate(inflater, container, false)
        nombreView = binding.root.findViewById(R.id.NombreValue);
        estadoView = binding.root.findViewById(R.id.EstadoValue);
        motivoBView = binding.root.findViewById(R.id.MotivoValue);
        planView = binding.root.findViewById(R.id.PlanValue);
        cuotaView = binding.root.findViewById(R.id.CuotaValue);
        vendedorView = binding.root.findViewById(R.id.VendedorValue);
        zonaView = binding.root.findViewById(R.id.ZonaValue);
        telefonoView = binding.root.findViewById(R.id.TelefonoValue);

        /*if(socioViewModel.getMessageErrorDni()){
            Toast.makeText( this@SocioFragment.requireActivity(), "No existe un socio asociado a ese número de documento" , Toast.LENGTH_SHORT).show();
            Global.messageErrorDNI = false;
        }*/

        socioViewModel.SocioModel.observe(viewLifecycleOwner, Observer {
            if (it != null ) {
                if (it.Documento == null) {
                    binding.card.visibility = View.GONE
                    Toast.makeText( this@SocioFragment.requireActivity(), "No existe un socio asociado a ese número de documento" , Toast.LENGTH_SHORT).show();
                } else
                    binding.card.visibility = View.VISIBLE
                    configView()
            }
        })

        binding.BTNDerivar.setOnClickListener{ sendDerivacion() };
        return binding.root;
    }

    fun configView(){
        if(isVendedorSocio()) showSocioGreen() else ShowSocioRed()
    }

    fun sendDerivacion(){
        socioViewModel.SendEmailDerivacion(Global.NombreSocio, Global.vendedorIdSocio)
        socioViewModel.message.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(this@SocioFragment.requireActivity(), it , Toast.LENGTH_SHORT).show();
            }
        })
    }

    fun isVendedorSocio(): Boolean {

        val idVendedorActual = Global.vendedorIdActual;
        val estadoSocio = Global.estadoSocio;
        val idVendedorSocio = Global.vendedorIdSocio;
        val fechaBajaSocio = Global.fechaBajaSocio;

        if (fechaBajaSocio != null)
            return !(estadoSocio == "Inactivo" && idVendedorActual != idVendedorSocio && LocalDate.now() <= fechaBajaSocio.plusDays(30))
        else return true;
    }

    fun showSocioGreen(){
        socioViewModel.SocioModel.observe(viewLifecycleOwner) {
            if (it != null) {nombreView.text = it.NombreYapellido} else "-";
            if (it != null) {estadoView.text = it.Activo} else "-";
            if (it != null) {motivoBView.text = it.MotivoBaja} else "-";
            if (it != null) {planView.text = it.Plan} else "-";
            if (it != null) { cuotaView.text = it.Cuota} else "-";
            if (it != null) { vendedorView.text = it.Vendedor} else "-";
            if (it != null) { zonaView.text = it.Zona + " - " + it.NombreZona} else "-";
            if (it != null) { telefonoView.text = it.Telefono} else "";
            binding.BTNDerivar.visibility = View.GONE
            binding.card.layoutParams.height = 885
            binding.card.setCardBackgroundColor(Color.rgb(183,228,199))
        }
    }

    fun ShowSocioRed(){
            socioViewModel.SocioModel.observe(viewLifecycleOwner) {
                if (it != null) {nombreView.text = it.NombreYapellido} else "-";
                if (it != null) {estadoView.text = (it.Activo)} else "-";
                if (it != null) {motivoBView.text = it.MotivoBaja} else "-";
                if (it != null) { planView.text = it.Plan} else "-";
                if (it != null) {cuotaView.text = it.Cuota} else "-";
                if (it != null) { vendedorView.text = it.Vendedor} else "-";
                if (it != null) { zonaView.text = it.Zona + " - " + it.NombreZona} else "-";
                if (it != null) { telefonoView.text = it.Telefono} else "-";
                binding.card.layoutParams.height = 885
                binding.BTNDerivar.visibility = View.VISIBLE
                binding.card.setCardBackgroundColor(Color.rgb(251,170,153))
        }
    }
}


//return !(estadoSocio == "Inactivo" && idVendedorActual != idVendedorSocio && LocalDate.now() < fechaBajaSocio.plusDays(30))
/*socioViewModel.SocioModel.observe(viewLifecycleOwner) {
    vendedorIdBD = it.VendedorID;
    fechaBajaBD = LocalDate.parse(it.FechaBaja);
    estadoBD = it.Activo.toString()
}*/

/*if( estadoSocio == "Inactivo" && idVendedorActual == idVendedorSocio && LocalDate.now() < fechaBajaSocio.plusDays(30)){
                return true
            } else if (estadoSocio == "Inactivo" && idVendedorActual == idVendedorSocio && LocalDate.now() > fechaBajaSocio.plusDays(30)){
                return true
            }else if (estadoSocio == "Inactivo" && idVendedorActual != idVendedorSocio && LocalDate.now() < fechaBajaSocio.plusDays(30)){
                return false
            }else if (estadoSocio == "Inactivo" && idVendedorActual != idVendedorSocio && LocalDate.now() > fechaBajaSocio.plusDays(30)){
                return true
            } else {
                return true
            }*/

/*socioViewModel.SocioModel.observe(viewLifecycleOwner, Observer{
    Global.fechaBajaSocio = LocalDate.parse(it.FechaBaja)
    Global.estadoSocio = it.Activo.toString()
    Global.vendedorIdSocio = it.VendedorID
    Global.NombreSocio = it.NombreYapellido.toString()
})*/