package com.example.navigationview

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.navigationview.Usuario.Companion.JsonObjectsBuild
import com.google.android.material.snackbar.Snackbar
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener

class MainActivity : AppCompatActivity(),View.OnClickListener {
    lateinit var txtMensaje: TextView
    lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*val actionBar = supportActionBar
        actionBar!!.title = "UTEQ App"
        actionBar!!.subtitle="Primera aplicacion"*/

        toolbar = findViewById<Toolbar>(R.id.toolbar);
        toolbar!!.title="App UTEQ"
        setSupportActionBar(toolbar);

        txtMensaje=findViewById<TextView>(R.id.txtmensaje)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)}
    @SuppressLint("SetTextI18n")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.mnuBuscar -> {
                txtMensaje?.setText("Buscar");
                return true
            }
            R.id.mnuNuevo-> {
                txtMensaje?.setText("Nuevo");
                return true
            }
            R.id.mnuSetting -> {
                txtMensaje.setText("Settings")
                return true
            }
        }
        return super.onOptionsItemSelected(item)}


    override fun onClick(view: View) {
        var lista =
            "[{\"usuario\":\"EduardoG\",\"contrasena\":\"eduardo\",\"imagen\":\"usuario3\",\"permisos\":[\"Inicio\",\"Informacion\",\"Configuracion\"]},{\"usuario\":\"MarielaC\",\"contrasena\":\"pinocho\",\"imagen\":\"usuario2\",\"permisos\":[\"Inicio\",\"Registro\",\"Analisis\"]},{\"usuario\":\"AlfredoB\",\"contrasena\":\"pipipupucheck\",\"imagen\":\"usuario1\",\"permisos\":[\"Inicio\",\"Apertura\",\"Horarios\"]}]"
        try{
        val nombre: TextView = findViewById(R.id.txtcorreo)
        val contrasena: TextView = findViewById(R.id.txtpassword)
        val usuarios = JSONArray(lista)
        val listausuarios: ArrayList<Usuario> = JsonObjectsBuild(usuarios)
        val intent = Intent(this, MainActivity2::class.java)
        val bundle = Bundle()
        for (Usuario in listausuarios) {
            if (Usuario.usuario == nombre.text.toString()) {
                if (Usuario.contrasena == contrasena.text.toString()) {
                    bundle.putString("usuario", Usuario.usuario)
                    bundle.putString("imagen", Usuario.imagen)
                    bundle.putString("permisos", Usuario.permisos.toString())
                    intent.putExtras(bundle)
                    startActivity(intent)
                } else {
                    Toast.makeText(applicationContext, "Usuario o contraseña incorrecto", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
        } catch (ex: Exception) {
            Toast.makeText(applicationContext, "Error: " + ex.message, Toast.LENGTH_SHORT).show()
        }
    }



}