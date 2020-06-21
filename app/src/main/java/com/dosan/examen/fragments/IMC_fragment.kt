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
import com.dosan.examen.Crud.Historial
import com.dosan.examen.Crud.HistorialCrud
import com.dosan.examen.R
import com.google.android.material.snackbar.Snackbar
import kotlin.math.pow


class IMC_fragment : Fragment(), View.OnClickListener {
    var crud: HistorialCrud? = null

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
        val btnClear =
            view.findViewById(R.id.btnLimpiar) as Button
        btnClear.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnCalcular -> {

                crud = HistorialCrud(view!!.context)

                if (edAltura?.text.toString().isNotEmpty() && edPeso?.text.toString()
                        .isNotEmpty()
                ) {
                    val peso = edPeso?.text.toString().toFloat()
                    var altura = (edAltura?.text.toString().toFloat()).pow(2)
                    val resultado = peso / altura
                    hideKeyboard()
                    // Agregar a la base de das sqlite

                    crud?.newHistory(
                        Historial(
                            0,
                            peso.toString(),
                            altura.toString(),
                            resultado.toString(),
                            "IMC"
                        )
                    )


                    Snackbar.make(
                        view!!.findViewById(R.id.linearIMC),
                        "El IMC es $resultado usted tiene un imc ${bodyStatus(resultado)}",
                        Snackbar.LENGTH_LONG
                    ).show()
                } else {
                    hideKeyboard()
                    Snackbar.make(
                        view!!.findViewById(R.id.linearIMC),
                        "Por favor llena todos los campos",
                        Snackbar.LENGTH_LONG
                    ).show()
                }

            }
            R.id.btnLimpiar -> {
                edAltura?.text?.clear()
                edPeso?.text?.clear()

            }
        }

    }


    fun hideKeyboard() {
        val imm =
            activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view!!.windowToken, 0)
    }

    fun bodyStatus(imc: Float): String {
        var status = ""
        if (imc < 18.5) {
            status = "Insufeciencia ponderal"
        } else if (imc > 18.5 && imc < 29.9) {
            status = "Normal"
        } else if (imc > 25 && imc < 29.9) {
            status = "Preobesidad"
        } else if (imc > 30.0 && imc < 34.9) {
            status = "OBesidad de clase I"
        } else if (imc > 35.0 && imc < 39.9) {
            status = "OBesidad de clase II"
        } else if (imc > 40) {
            status = "OBesidad de clase III"
        }


        return status
    }
}