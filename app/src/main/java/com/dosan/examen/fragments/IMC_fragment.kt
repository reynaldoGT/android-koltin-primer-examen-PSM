package com.dosan.examen.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.dosan.examen.R
import com.google.android.material.snackbar.Snackbar
import kotlin.math.pow


class IMC_fragment : Fragment(), View.OnClickListener {
    var edAltura: EditText? = null
    var edPeso: EditText? = null
    var btnCalcular: Button? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.imc_fragment, container, false)
        buttons_functions(view)
        edAltura = view.findViewById(R.id.edAltura)
        edPeso = view.findViewById(R.id.edPeso)
        btnCalcular = view.findViewById(R.id.btnCalcular)

        return view
    }

    fun buttons_functions(view: View) {
        val btnCall =
            view.findViewById(R.id.btnCalcular) as Button
        btnCall.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnCalcular -> {
                val peso = edPeso?.text.toString().toFloat()
                var altura = (edAltura?.text.toString().toFloat()).pow(2)

                val resultado = peso / altura
                ocultarTeclado()
                Snackbar.make(
                    view!!.findViewById(R.id.linearIMC),
                    "El IMC de $resultado",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }

    }


    fun ocultarTeclado() {
        val imm =
            activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view!!.windowToken, 0)
    }
}