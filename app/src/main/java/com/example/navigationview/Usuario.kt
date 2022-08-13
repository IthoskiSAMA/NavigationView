package com.example.navigationview

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class Usuario (objeto: JSONObject){
        var usuario: String? = null
        var contrasena: String? = null
        var imagen: String? = null
        var permisos: JSONArray ? = null
    companion object{
        @Throws(JSONException::class)
        fun JsonObjectsBuild(datos: JSONArray): java.util.ArrayList<Usuario> {
            val usuarios: ArrayList<Usuario> = ArrayList()
            var i = 0
            while (i < datos.length()) {
                usuarios.add(Usuario(datos.getJSONObject(i)))
                i++
            }
            return usuarios
        }
    }
    init {
        usuario = objeto.getString("usuario")
        contrasena = objeto.getString("contrasena")
        imagen = objeto.getString("imagen")
        permisos = objeto.getJSONArray("permisos")
    }
}