package mx.edu.ittepic.ladm_u2_p2_loteria

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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

        mostrarBarajeo()

        binding.name.text = "Barajeando"

        Glide.with(this)
            .load(R.raw.barajeo)
            .into(binding.imgCarta)
        var hilo = Hilo(this)

        binding.btnEmpezar.setOnClickListener {
            hilo.start()
            binding.btnEmpezar.isEnabled = false
            binding.btnDetener.isEnabled = true
            binding.btnPausar.isEnabled = true
            binding.btnBarajear.isEnabled = true
        }

        /**Buenas*/
        binding.btnDetener.setOnClickListener {
            if (hilo.jugando && !hilo.pausar) {
                if (hilo.revisar < 2)
                    hilo.revisar = hilo.revisar + 1
                Log.i(
                    "Pausar-detener-revisar",
                    hilo.pausar.toString() + " - " + hilo.detener.toString() + "- " + hilo.revisar
                )
                hilo.detenerJuego()

            }
        }

        /**Pausar*/
        binding.btnPausar.setOnClickListener {
            hilo.cambiarPausar()
            //Log.i("Pausar-Verificar",hilo.pausar.toString()+" - "+hilo.detener.toString())
            (hilo.i..hilo.cartas.size - 1).forEach {
                Log.i("Sobrantes", hilo.cartas[it].toString())
            }
        }
        /**Barajear*/
        binding.btnBarajear.setOnClickListener {

            Log.i("Cartas salidas", "${hilo.cartasSalidas.size}")
            Log.i("Cartas sobrantes", "${hilo.cartasSobrantes.size}")
            if (!hilo.pausar)
                hilo.cambiarPausar()
            AlertDialog.Builder(this)
                .setTitle("Barajear")
                .setMessage("??Est?? seguro de reiniciar el juego?")
                .setPositiveButton("S??") { d, i ->
                    hilo.reiniciar()
                    mostrarBarajeo()
                    d.dismiss()
                }
                .setNegativeButton("Cancelar") { d, i ->
                    //hilo.detenerJuego()
                    hilo.cambiarPausar()
                    d.dismiss()
                }
                .show()
        }

    }

    fun mostrarBarajeo() = GlobalScope.launch {
        runOnUiThread {
            Glide.with(baseContext)
                .load(R.raw.barajeo)
                .into(binding.imgCarta)
            val audio = MediaPlayer.create(baseContext, R.raw.audbarajeo)
            audio.start()
        }
        delay(1000L)
    }
}