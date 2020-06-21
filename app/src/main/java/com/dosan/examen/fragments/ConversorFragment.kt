package com.dosan.examen.fragments

import android.content.Context
import android.icu.text.Transliterator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.fragment.app.Fragment
import com.dosan.examen.Crud.HistorialCrud
import com.dosan.examen.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_conversor.*


class ConversorFragment : Fragment(), View.OnClickListener {
    var edTextMetros: EditText? = null
    var spinner: Spinner? = null
    var btnMetros: Button? = null

    // para hacer el crud
    var crud: HistorialCrud? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        edTextMetros = view?.findViewById(R.id.edMetros)
        edTextMetros?.setText("1")
        btnMetros = view?.findViewById(R.id.btnConvertir)

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_conversor, container, false)
        buttons_functions(view)
        spinner = view.findViewById(R.id.spinner)
        var itemsList = arrayOf(
            "Cm (Centímetros)",
            "Dm (Decímetros)",
            "Mm (Milímetros)",
            "Dm (Decámetros)",
            "Km (Kilómetros)",
            "Hm( Hectómetros))"
        )

        val dataAdapter: ArrayAdapter<String> = ArrayAdapter(
            view.context,
            android.R.layout.simple_spinner_item, itemsList
        )
        dataAdapter.setDropDownViewResource(android.R.layout.simple_selectable_list_item)
        spinner?.adapter = dataAdapter


        return view
    }

    fun buttons_functions(view: View) {
        val btnConvertir =
            view.findViewById(R.id.btnConvertir) as Button
        btnConvertir.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnConvertir -> {
                val selecction = spinner?.selectedItemPosition
                //Toast.makeText(context, selecction.toString(), Toast.LENGTH_LONG).show()
                opciones(selecction!!)
            }
        }
    }

    fun opciones(position: Int) {
        edTextMetros = view?.findViewById(R.id.edMetros)
        var resultado = 0.0
        var mensaje = ""
        if (edTextMetros?.text!!.isNotEmpty()) {
            when (position) {
                0 -> {
                    resultado = edTextMetros?.text.toString().toDouble() * 100
                    mensaje =
                        "${edTextMetros?.text.toString()
                            .toDouble()} equivale a  $resultado Centimetros"
                }
                1 -> {
                    resultado = edTextMetros?.text.toString().toDouble() / 10
                    mensaje =
                        "${edTextMetros?.text.toString()
                            .toDouble()} equivale a $resultado Decimietros"
                }
                2 -> {
                    resultado = edTextMetros?.text.toString().toDouble() / 1000
                    mensaje =
                        "${edTextMetros?.text.toString()
                            .toDouble()} equivale a $resultado milimetros"
                }
                3 -> {
                    resultado = edTextMetros?.text.toString().toDouble() / 10
                    mensaje =
                        "${edTextMetros?.text.toString()
                            .toDouble()} equivale a $resultado decametros"
                }
                4 -> {
                    resultado = edTextMetros?.text.toString().toDouble() / 1000
                    mensaje =
                        "${edTextMetros?.text.toString()
                            .toDouble()} equivale a $resultado kilometros"
                }
                5 -> {
                    resultado = edTextMetros?.text.toString().toDouble() / 0.5
                    mensaje =
                        "${edTextMetros?.text.toString()
                            .toDouble()} equivale a $resultado hectometros"
                }
            }
            hideKeyboard()
            Snackbar.make(
                view!!.findViewById(R.id.linearConversor),
                mensaje,
                Snackbar.LENGTH_LONG
            ).show()
        } else {
            Toast.makeText(context, "Por favor ingresa un número", Toast.LENGTH_LONG).show()
        }
        //Toast.makeText(context, mensaje, Toast.LENGTH_LONG).show()
    }

    fun hideKeyboard() {
        val imm =
            activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view!!.windowToken, 0)
    }
}