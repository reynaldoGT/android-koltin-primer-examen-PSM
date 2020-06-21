package com.dosan.examen.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dosan.examen.Crud.Historial
import com.dosan.examen.Crud.HistorialAdapter
import com.dosan.examen.Crud.HistorialCrud
import com.dosan.examen.R


class HistorialFragment : Fragment() {
    var lista: RecyclerView? = null
    var adaptador: HistorialAdapter? = null
    var layout_Manager: RecyclerView.LayoutManager? = null
    var historial: ArrayList<Historial>? = null
    var crud: HistorialCrud? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_historial, container, false)

        lista = view.findViewById(R.id.lista)
        lista?.setHasFixedSize(true)

        layout_Manager = LinearLayoutManager(view.context)

        lista?.layoutManager = layout_Manager

        crud = HistorialCrud(view.context)

        historial = crud?.getHistorial()

        adaptador = HistorialAdapter(historial!!)

        lista?.adapter = adaptador

        return view
    }


}