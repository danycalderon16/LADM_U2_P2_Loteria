package mx.edu.ittepic.ladm_u2_p2_loteria

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mx.edu.ittepic.ladm_u2_p2_loteria.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rutina2daPlanoAsincrono()

        binding.name.text = "Barajeando"

        Glide.with(this)
            .load(R.raw.barajeo)
            .into(binding.imgCarta)
        var hilo = Hilo(this)

        binding.btnEmpezar.setOnClickListener {
            hilo.start()
            binding.btnEmpezar.isEnabled = false
        }

        /**Loteria*/
        binding.btnDetener.setOnClickListener {
            hilo.cambiarEstado()
        }

        /**Pausar*/
        binding.btnPausar.setOnClickListener {
            hilo.cambiarPausar()
        }
        /**Barajear*/
        binding.btnBarajear.setOnClickListener {

            Log.i("Cartas salidas", "${hilo.cartasSalidas.size}")
            Log.i("Cartas sobrantes", "${hilo.cartasSobrantes.size}")
            if (!hilo.pausar)
                hilo.cambiarPausar()
            AlertDialog.Builder(this)
                .setTitle("Barajear")
                .setMessage("¿Está seguro de reiniciar el juego?")
                .setPositiveButton("Sí") { d, i ->
                    hilo.reiniciar()
                    rutina2daPlanoAsincrono()
                    d.dismiss()
                }
                .setNegativeButton("Cancelar") { d, i ->
                    hilo.cambiarEstado()
                    d.dismiss()
                }
                .show()
        }

    }

    fun rutina2daPlanoAsincrono() = GlobalScope.launch {

        runOnUiThread {
            Glide.with(baseContext)
                .load(R.raw.barajeo)
                .into( binding.imgCarta)
        }
        delay(1000L)

    }
}