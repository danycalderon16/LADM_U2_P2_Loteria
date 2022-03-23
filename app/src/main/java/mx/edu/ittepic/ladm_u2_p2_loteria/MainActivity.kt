package mx.edu.ittepic.ladm_u2_p2_loteria

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import mx.edu.ittepic.ladm_u2_p2_loteria.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.name.text = "Barajeando"

        Glide.with(this)
            .load(R.raw.barajeo)
            .into( binding.imgCarta)
        var hilo = Hilo(this)

        binding.btnEmpezar.setOnClickListener {
            hilo.start()
        }

        binding.btnDetener.setOnClickListener {
            hilo.cambiarEstado()
        }

        binding.btnPausar.setOnClickListener {
            hilo.cambiarPausar()
        }
    }
}