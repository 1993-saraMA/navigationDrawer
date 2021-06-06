package com.example.navigationdrawer.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.replace
import com.example.navigationdrawer.R
import com.example.navigationdrawer.fragments.FirstFragment
import com.example.navigationdrawer.fragments.SecondFragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        // Se almacena el DrawerLayout que se encuentra en el activity_main.xml
        // y se le especifíca el tipo de dato (DrawerLayout)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)

        // La variable toggle se va a encargar de manejar la acción cuando el usuario da click
        // en el icono que despliega el menú (las tres lineas verticales contínuas)
        val toggle = ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name,R.string.close)
        toggle.isDrawerIndicatorEnabled = true
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        //
        val navigationView: NavigationView = findViewById(R.id.nav_menu)
        val fragment = supportFragmentManager.beginTransaction()
        fragment.replace(R.id.fragment_container,FirstFragment()).commit()
        navigationView.setNavigationItemSelectedListener { item ->

            when (item.itemId) {
                // nav_first_fragment es el id del item First Fragment del navigation_menu
                R.id.nav_first_fragment -> {
                    menuFragment(FirstFragment(),getString(R.string.first_fragment_label))
                    Toast.makeText(this, "First Fragment", Toast.LENGTH_SHORT).show()
                }
                // nav_second_fragment es el id del item First Fragment del navigation_menu
                R.id.nav_second_fragment -> {
                    menuFragment(SecondFragment(),getString(R.string.second_fragment_label))
                    Toast.makeText(this, "Second Fragment", Toast.LENGTH_SHORT).show()
                }
            }

            // Se encarga de cerrar el panel lateral de navegación una vez que damos click en el fragmento
            // al cuál queremos ir.
            drawerLayout.closeDrawer(GravityCompat.START)

            true
        }
    }

    // La función de menuFragment es redirigir a un fragmento específico y cambiar
    // el título de cada fragmento para que el usuario sepa donde se encuentra
    private fun menuFragment(frag: Fragment, title:String){
        supportActionBar?.title = title
        val fragment = supportFragmentManager.beginTransaction()
        fragment.replace(R.id.fragment_container,frag).commit()
    }
}