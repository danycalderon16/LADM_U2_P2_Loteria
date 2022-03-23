package mx.edu.ittepic.ladm_u2_p2_loteria

import java.util.*
import kotlin.random.Random

class Hilo(activity: MainActivity) : Thread() {

    var cartas = arrayListOf<Carta>()
    var cartasSalidas = arrayListOf<Int>()
    var cartasSobrantes = arrayListOf<Int>()

    var seguir = true
    var activity = activity

    init {
        iniciarCartas()
    }

    private fun iniciarCartas() {
        cartas.add(Carta(0, R.drawable.icono7, "instagram", 0))
        cartas.add(Carta(1, R.drawable.icono1, "facebook", 0))
        cartas.add(Carta(2, R.drawable.icono2, "itunes", 0))
        cartas.add(Carta(3, R.drawable.icono3, "whatsapp", 0))
        cartas.add(Carta(4, R.drawable.icono4, "amazon", 0))
        cartas.add(Carta(5, R.drawable.icono5, "youtube", 0))
        cartas.add(Carta(6, R.drawable.icono6, "twitter", 0))
    }

    override fun run() {
        super.run()

        while (true) {
            if (seguir) {
                if (cartasSalidas.size == cartas.size)
                    activity.runOnUiThread{
                        activity.binding.name.text = "Se acabo"
                    }
                else {
                    var i = getRandomId(Random.nextInt(1, cartas.size))
                    cartasSalidas.add(i)
                    activity.runOnUiThread {
                        activity.binding.name.text = "$i - ${cartas[i].nombre}"
                        activity.binding.imgCarta.setImageResource(cartas[i].img)
                    }
                }
            }
            else
                mostrarSobrenates()

            sleep(2000L)
        }
    }

    fun getRandomId(n: Int): Int {
        var i = Random.nextInt(0, cartas.size)
        return if (!(i in cartasSalidas)) i else getRandomId(Random.nextInt(1, cartas.size))
    }

    fun cambiarEstado() {
        seguir = !seguir
    }

    fun mostrarSobrenates(){
        var i = cartas.size-cartasSalidas.size
        activity.runOnUiThread {
            activity.binding.name.text = "linea 65 $i"
        }

    }
}

