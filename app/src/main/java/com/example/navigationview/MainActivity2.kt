package com.example.navigationview

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.view.get
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.navigationview.Usuario.Companion.JsonObjectsBuild
import com.google.android.material.navigation.NavigationView
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener

class MainActivity2 : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navHarder: View
    lateinit var nav_view: NavigationView
    lateinit var nombre: TextView
    lateinit var imagen: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        toolbar = findViewById<Toolbar>(R.id.toolbar);
        toolbar!!.title="App UTEQ"
        setSupportActionBar(toolbar)
        getSupportActionBar()?.setHomeAsUpIndicator(R.drawable.iconmenu);
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        nav_view=findViewById(R.id.nav_view)
        navHarder = nav_view.getHeaderView(0)
        nombre = navHarder.findViewById(R.id.txtnombre)
        imagen = navHarder.findViewById(R.id.profile_image)
        val bundel = intent.extras
        nombre.text= bundel?.getString("usuario")
        var idimagen = resources.getIdentifier(bundel?.getString("imagen"), "drawable", packageName)
        imagen.setImageResource(idimagen)
        val permisos=JSONArray(bundel?.getString("permisos"))
        nav_view.menu.clear()
        for(i in 0 until permisos.length()){
            nav_view.menu.add(permisos.get(i).toString())
            nav_view.menu.getItem(i).setIcon(R.drawable.iconmenu)
            var item: MenuItem= nav_view.menu.add(permisos.get(i).toString())
            item.setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener {
                var fragment: Fragment? = null
                when (item.title){
                    "Inicio"->{
                        fragment = Inicio()
                    }
                    "Analisis"->{
                        fragment = Analisis()
                    }
                    "Apertura"->{
                        fragment = Apertura()
                    }
                    "Configuracion"->{
                        fragment = Configuracion()
                    }
                    "Horarios"->{
                        fragment = Horarios()
                    }
                    "Informacion"->{
                        fragment = Informacion()
                    }
                }
                if(fragment != null){
                    getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frame,fragment)
                        .commit()
                    item.setChecked(true)
                    getSupportActionBar()?.setTitle(item.getTitle());
                }
                val drawerlayaout: DrawerLayout= findViewById(R.id.drawer_layout)
                drawerlayaout.closeDrawer(GravityCompat.START)
                true
            })
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
                drawerLayout?.openDrawer(GravityCompat.START)
                return true
            }
        }
        return super.onOptionsItemSelected(item)}

}