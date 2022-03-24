package mx.edu.ittepic.ladm_u2_p2_loteria

import kotlin.properties.Delegates

class Carta(id:Int,img:Int, nombre:String, audio:Int){

    var id = 0
    var img = 0
    var nombre = ""
    var audio = 0

    init {
        this.id = id
        this.img = img
        this.nombre = nombre
        this.audio = audio
    }

    override fun toString(): String {
        return "Carta(id=$id, img=$img, nombre='$nombre', audio=$audio)"
    }


}