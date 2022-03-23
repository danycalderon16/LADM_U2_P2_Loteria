package mx.edu.ittepic.ladm_u2_p2_loteria

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.edu.ittepic.ladm_u2_p2_loteria.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var hilo = Hilo(this)

        binding.btnEmpezar.setOnClickListener {
            hilo.start()
        }

        binding.btnDetener.setOnClickListener {
            hilo.cambiarEstado()
        }
    }
}