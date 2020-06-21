package com.dosan.examen.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.dosan.examen.R
import com.dosan.examen.fragments.ConversorFragment
import com.dosan.examen.fragments.HistorialFragment
import com.dosan.examen.fragments.IMC_fragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    var draweLayout: DrawerLayout? = null
    var navigationView: NavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        draweLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)

        setToolbar()
        showSelectFrame(IMC_fragment())

        navigationView?.setNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.imc -> {
                    //Toast.makeText(this, "item", Toast.LENGTH_LONG).show()
                    showSelectFrame(IMC_fragment())

                }
                R.id.convesor -> {
                    //Toast.makeText(this, "item", Toast.LENGTH_LONG).show()
                    showSelectFrame(ConversorFragment())
                }
                R.id.historial -> {
                    //Toast.makeText(this, "item", Toast.LENGTH_LONG).show()
                    showSelectFrame(HistorialFragment())
                }
            }
            //it.isChecked = true
            //supportActionBar?.title = title
            draweLayout?.closeDrawers()
            true
        }
    }

    fun setToolbar() {
        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = "Examen PSN"
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"))
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_home)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                draweLayout?.openDrawer(GravityCompat.START)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    // Metodo para cambiar entre fragments
    fun showSelectFrame(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.content_frame, fragment)
            .commit()

    }
}