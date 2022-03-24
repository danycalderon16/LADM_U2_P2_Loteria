package mx.edu.ittepic.ladm_u2_p2_loteria

import android.media.MediaPlayer
import android.util.Log
import com.bumptech.glide.Glide
import java.util.*
import kotlin.random.Random

class Hilo(activity: MainActivity) : Thread() {

    var cartas = arrayListOf<Carta>()
    var cartasSalidas = arrayListOf<Int>()
    var cartasSobrantes = arrayListOf<Carta>()

    var jugando = true // empezar
    var detener = false // alguien gano
    var barajear = true // reinicar juego
    var pausar = false // pausa juego TRUE = puasado  |  FALSE = detenido
    var activity = activity
    var revisar = 0
    var i = 0

    var frases = arrayOf("", "Fuera", "Listos", "En sus marcas")
    var cuenta = 3

    init {
        iniciarCartas()
        cartas.shuffle()
        cartasSobrantes = cartas
    }

    override fun run() {
        super.run()

        while (true) {
            if (jugando) {
                if (barajear && cuenta != 0) {
                    activity.runOnUiThread {
                        activity.binding.name.text = "${frases[cuenta--]}"

                    }
                } else {
                    if (pausar && !detener)
                        activity.runOnUiThread {
                            activity.binding.name.text = "Pausado"
                            activity.binding.btnPausar.text = "Reanudar"

                        }
                    else {
                        if (detener) {
                            activity.runOnUiThread {
                                activity.binding.btnDetener.text = "VERIFICAR CARTAS"
                                Glide.with(activity)
                                    .load(R.drawable.buenas)
                                    .into(activity.binding.imgCarta)
                            }
                            if(revisar==2){
                                if (i<cartas.size-1)
                                    i++
                                activity.runOnUiThread {
                                    activity.binding.name.text = "VERIFICANDO CARTAS $i"
                                    Glide.with(activity)
                                        .load(cartas[i].img)
                                        .into(activity.binding.imgCarta)
                                }
                            }
                        } else {
                            activity.runOnUiThread {
                                activity.binding.btnPausar.text = "Pausar"
                                if (i < cartas.size) {
                                    //Log.i("============", "if 49: " + i)
                                    Glide.with(activity)
                                        .load(cartas[i].img)
                                        .into(activity.binding.imgCarta)
                                    activity.binding.name.text = "index $i "
                                    //val mp = MediaPlayer.create(activity, cartas[i].audio)
                                    //mp.start()
                                    i++
                                } else {
                                    /**
                                     * Salieron todas las cartas
                                     * */
                                  //  Log.i("============", "else 59: " + i + "-" + cartas.size)
                                    activity.binding.name.text = "Nadie Gano. Barajenado"
                                    Glide.with(activity)
                                        .load(R.raw.barajeo)
                                        .into(activity.binding.imgCarta)
                                }
                            }
                        }
                    }
                }
            } else
                mostrarSobrenates()

            sleep(500L)
        }
    }

    fun eliminarCarta(carta: Carta) {
        var index = 0

/*        (0..cartasSobrantes.size).forEach {
            if(cartasSobrantes[it-1].id == carta.id )
                index = it-1
        }
  */      Log.i("Carta", carta.toString() + " index${index}")
        cartasSobrantes.remove(carta)

//        cartasSobrantes.removeIf {it==carta}
    }

    fun getRandomId(i: Int, tope: Int): Int {
        return if (tope == cartas.size - 1) 0 else if (!(i in cartasSalidas)) i else getRandomId(
            Random.nextInt(1, cartas.size),
            cartasSalidas.size
        )
    }

    fun detenerJuego() {
        detener = true
    }

    fun reiniciar() {
        cartas.shuffle()
        cuenta = 3
        barajear = true
        pausar = false
        i = 0
        revisar = 0
        detener = false
    }

    fun mostrarSobrenates() {
        var i = cartas.size - cartasSalidas.size
        activity.runOnUiThread {
            activity.binding.name.text = "linea 65 $i"
        }
    }

    fun cambiarPausar() {
        pausar = !pausar
    }

    fun seEstaJugando(): Boolean {
        return jugando
    }

    private fun iniciarCartas() {
        cartas.add(Carta(1, R.drawable.img1, "El gallo", R.raw.aud1))
        cartas.add(Carta(2, R.drawable.img2, "El diablo", R.raw.aud2))
        cartas.add(Carta(3, R.drawable.img3, "La dama", R.raw.aud3))
        cartas.add(Carta(4, R.drawable.img4, "El catrín", R.raw.aud4))
        cartas.add(Carta(5, R.drawable.img5, "El paraguas", R.raw.aud1))
        cartas.add(Carta(6, R.drawable.img6, "La sirena", R.raw.aud4))
        cartas.add(Carta(7, R.drawable.img7, "La escalera", R.raw.aud1))
        cartas.add(Carta(8, R.drawable.img8, "La botela", R.raw.aud4))
        cartas.add(Carta(9, R.drawable.img9, "EL barril", R.raw.aud1))
        cartas.add(Carta(10, R.drawable.img10, "El árbol", R.raw.aud3))
        cartas.add(Carta(11, R.drawable.img11, "El melón", R.raw.aud2))
        cartas.add(Carta(12, R.drawable.img12, "El valiente", R.raw.aud1))
        cartas.add(Carta(13, R.drawable.img13, "El gorrito", R.raw.aud3))
        cartas.add(Carta(14, R.drawable.img14, "La muerte", R.raw.aud4))
        cartas.add(Carta(15, R.drawable.img15, "La pera", R.raw.aud2))
        cartas.add(Carta(16, R.drawable.img16, "La bandera", R.raw.aud1))
        cartas.add(Carta(17, R.drawable.img17, "El bandoloón", R.raw.aud2))
        cartas.add(Carta(18, R.drawable.img18, "El violoncello", R.raw.aud3))
        cartas.add(Carta(19, R.drawable.img19, "La garza", R.raw.aud4))
        cartas.add(Carta(20, R.drawable.img20, "El pájaro", R.raw.aud3))
        cartas.add(Carta(21, R.drawable.img21, "La mano", R.raw.aud2))
        cartas.add(Carta(22, R.drawable.img22, "La bota", R.raw.aud1))
        cartas.add(Carta(23, R.drawable.img23, "La luna", R.raw.aud4))
        cartas.add(Carta(24, R.drawable.img24, "El cotorro", R.raw.aud3))
        cartas.add(Carta(25, R.drawable.img25, "El borracho", R.raw.aud1))
        cartas.add(Carta(26, R.drawable.img26, "El negrito", R.raw.aud1))
        cartas.add(Carta(27, R.drawable.img27, "El corazón", R.raw.aud1))
        cartas.add(Carta(28, R.drawable.img28, "La sandía", R.raw.aud1))
        cartas.add(Carta(29, R.drawable.img29, "El tambor", R.raw.aud1))
        cartas.add(Carta(30, R.drawable.img30, "El camarón", R.raw.aud1))
        cartas.add(Carta(31, R.drawable.img31, "La jaras", R.raw.aud1))
        cartas.add(Carta(32, R.drawable.img32, "El músico", R.raw.aud1))
        cartas.add(Carta(33, R.drawable.img33, "La araña", R.raw.aud1))
        cartas.add(Carta(34, R.drawable.img34, "El soldado", R.raw.aud1))
        cartas.add(Carta(35, R.drawable.img35, "La estrella", R.raw.aud1))
        cartas.add(Carta(36, R.drawable.img36, "El cazo", R.raw.aud1))
        cartas.add(Carta(37, R.drawable.img37, "El mundo", R.raw.aud1))
        cartas.add(Carta(38, R.drawable.img38, "El apache", R.raw.aud1))
        cartas.add(Carta(39, R.drawable.img39, "El nopal", R.raw.aud1))
        cartas.add(Carta(40, R.drawable.img40, "El alacrán", R.raw.aud1))
        cartas.add(Carta(41, R.drawable.img41, "La rosa", R.raw.aud1))
        cartas.add(Carta(42, R.drawable.img42, "La calavera", R.raw.aud1))
        cartas.add(Carta(43, R.drawable.img43, "La campana", R.raw.aud1))
        cartas.add(Carta(44, R.drawable.img44, "El canarito", R.raw.aud1))
        cartas.add(Carta(45, R.drawable.img45, "El venado", R.raw.aud1))
        cartas.add(Carta(46, R.drawable.img46, "El sol", R.raw.aud1))
        cartas.add(Carta(47, R.drawable.img47, "La corona", R.raw.aud1))
        cartas.add(Carta(48, R.drawable.img48, "La chalupa", R.raw.aud1))
        cartas.add(Carta(49, R.drawable.img49, "El pino", R.raw.aud1))
        cartas.add(Carta(50, R.drawable.img50, "El pescado", R.raw.aud1))
        cartas.add(Carta(51, R.drawable.img51, "La palma", R.raw.aud1))
        cartas.add(Carta(52, R.drawable.img52, "La maceta", R.raw.aud1))
        cartas.add(Carta(53, R.drawable.img53, "El arpa", R.raw.aud1))
        cartas.add(Carta(54, R.drawable.img54, "La rana", R.raw.aud1))
    }
}

