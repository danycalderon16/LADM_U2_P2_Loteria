package mx.edu.ittepic.ladm_u2_p2_loteria

import android.util.Log
import com.bumptech.glide.Glide
import java.util.*
import kotlin.random.Random

class Hilo(activity: MainActivity) : Thread() {

    var cartas = arrayListOf<Carta>()
    var cartasSalidas = arrayListOf<Int>()
    var cartasSobrantes = arrayListOf<Int>()

    var jugando = true // empezar
    var detener = false // alguien gano
    var barajear = false // reinicar juego
    var pausar = false // pausa juego TRUE = puasado  |  FALSE = detenido
    var activity = activity

    init {
        iniciarCartas()
    }

    override fun run() {
        super.run()

        while (true) {
            if (jugando) {
                if (pausar)
                    activity.runOnUiThread{
                        activity.binding.name.text = "Pausado"
                        activity.binding.btnPausar.text = "Reanudar"
                    }
                else {
                    val i = getRandomId(Random.nextInt(1, cartas.size), cartasSalidas.size)
                        activity.binding.btnPausar.text = "Pausar"
                    activity.runOnUiThread {
                        if (i == 0) {
                            /**
                             * Salieron todas las cartas
                             * */
                            Log.i("============","Linea: "+39)
                            activity.binding.name.text = "Nadie Gano. Barajenado"
                            Glide.with(activity)
                                .load(R.raw.barajeo)
                                .into( activity.binding.imgCarta)
                        }
                        else {
                            cartasSalidas.add(i)
                            Glide.with(activity)
                                .load(cartas[i].img)
                                .into( activity.binding.imgCarta)
                            activity.binding.name.text = "$i - ${cartas[i].id}"
                            //activity.binding.imgCarta.setImageResource(cartas[i].img)
                        }
                    }
                }
            }
            else
                mostrarSobrenates()

            sleep(200L)
        }
    }

    fun getRandomId(i: Int, tope:Int): Int {
        return if (tope == cartas.size-1) 0 else if (!(i in cartasSalidas)) i else getRandomId(Random.nextInt(1, cartas.size), cartasSalidas.size)
    }

    fun cambiarEstado() {
        detener = !detener
    }

    fun mostrarSobrenates(){
        var i = cartas.size-cartasSalidas.size
        activity.runOnUiThread {
            activity.binding.name.text = "linea 65 $i"
        }

    }

    fun cambiarPausar(){
        pausar = !pausar
    }

    private fun iniciarCartas() {
        cartas.add(Carta(1, R.drawable.img1, "El gallo", 0))
        cartas.add(Carta(2, R.drawable.img2, "El diablo", 0))
        cartas.add(Carta(3, R.drawable.img3, "La dama", 0))
        cartas.add(Carta(4, R.drawable.img4, "El catrín", 0))
        cartas.add(Carta(5, R.drawable.img5, "El paraguas", 0))
        cartas.add(Carta(6, R.drawable.img6, "La sirena", 0))
        cartas.add(Carta(7, R.drawable.img7, "La escalera", 0))
        cartas.add(Carta(8, R.drawable.img8, "La botela", 0))
        cartas.add(Carta(9, R.drawable.img9, "EL barril", 0))
        cartas.add(Carta(10, R.drawable.img10, "El árbol", 0))
        cartas.add(Carta(11, R.drawable.img11, "El melón", 0))
        cartas.add(Carta(12, R.drawable.img12, "El valiente", 0))
        cartas.add(Carta(13, R.drawable.img13, "El gorrito", 0))
        cartas.add(Carta(14, R.drawable.img14, "La muerte", 0))
        cartas.add(Carta(15, R.drawable.img15, "La pera", 0))
        cartas.add(Carta(16, R.drawable.img16, "La bandera", 0))
        cartas.add(Carta(17, R.drawable.img17, "El bandoloón", 0))
        cartas.add(Carta(18, R.drawable.img18, "El violoncello", 0))
        cartas.add(Carta(19, R.drawable.img19, "La garza", 0))
        cartas.add(Carta(20, R.drawable.img20, "El pájaro", 0))
        cartas.add(Carta(21, R.drawable.img21, "La mano", 0))
        cartas.add(Carta(22, R.drawable.img22, "La bota", 0))
        cartas.add(Carta(23, R.drawable.img23, "La luna", 0))
        cartas.add(Carta(24, R.drawable.img24, "El cotorro", 0))
        cartas.add(Carta(25, R.drawable.img25, "El borracho", 0))
        cartas.add(Carta(26, R.drawable.img26, "El negrito", 0))
        cartas.add(Carta(27, R.drawable.img27, "El corazón", 0))
        cartas.add(Carta(28, R.drawable.img28, "La sandía", 0))
        cartas.add(Carta(29, R.drawable.img29, "El tambor", 0))
        cartas.add(Carta(30, R.drawable.img30, "El camarón", 0))
        cartas.add(Carta(31, R.drawable.img31, "La jaras", 0))
        cartas.add(Carta(32, R.drawable.img32, "El músico", 0))
        cartas.add(Carta(33, R.drawable.img33, "La araña", 0))
        cartas.add(Carta(34, R.drawable.img34, "El soldado", 0))
        cartas.add(Carta(35, R.drawable.img35, "La estrella", 0))
        cartas.add(Carta(36, R.drawable.img36, "El cazo", 0))
        cartas.add(Carta(37, R.drawable.img37, "El mundo", 0))
        cartas.add(Carta(38, R.drawable.img38, "El apache", 0))
        cartas.add(Carta(39, R.drawable.img39, "El nopal", 0))
        cartas.add(Carta(40, R.drawable.img40, "El alacrán", 0))
        cartas.add(Carta(41, R.drawable.img41, "La rosa", 0))
        cartas.add(Carta(42, R.drawable.img42, "La calavera", 0))
        cartas.add(Carta(43, R.drawable.img43, "La campana", 0))
        cartas.add(Carta(44, R.drawable.img44, "El canarito", 0))
        cartas.add(Carta(45, R.drawable.img45, "El venado", 0))
        cartas.add(Carta(46, R.drawable.img46, "El sol", 0))
        cartas.add(Carta(47, R.drawable.img47, "La corona", 0))
        cartas.add(Carta(48, R.drawable.img48, "La chalupa", 0))
        cartas.add(Carta(49, R.drawable.img49, "El pino", 0))
        cartas.add(Carta(50, R.drawable.img50, "El pescado", 0))
        cartas.add(Carta(51, R.drawable.img51, "La palma", 0))
        cartas.add(Carta(52, R.drawable.img52, "La maceta", 0))
        cartas.add(Carta(53, R.drawable.img53, "El arpa", 0))
        cartas.add(Carta(54, R.drawable.img54, "La rana", 0))
    }
}

