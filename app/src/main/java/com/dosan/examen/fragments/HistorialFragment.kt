package com.dosan.examen.fragments

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dosan.examen.Crud.Historial
import com.dosan.examen.Crud.HistorialAdapter
import com.dosan.examen.Crud.HistorialCrud
import com.dosan.examen.R


class HistorialFragment : Fragment(), View.OnClickListener {
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
        buttons_functions(view)


        lista?.layoutManager = layout_Manager

        crud = HistorialCrud(view.context)

        historial = crud?.getHistorial()

        adaptador = HistorialAdapter(historial!!)

        // definir el color y el icono en el swipe
        val colorDrawableBackground = ColorDrawable(Color.parseColor("#b71c1c"))
        val deleteIcon = ContextCompat.getDrawable(view.context, R.drawable.i_delete)!!

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, position: Int) {

                val listPosition = viewHolder.adapterPosition
                val idItem = historial!![listPosition].id
                //Toast.makeText(view.context,historial!![listPosition].id.toString() , Toast.LENGTH_SHORT).show()
                crud?.deleteItem(idItem)
                (adaptador as HistorialAdapter).removeItem(viewHolder)
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                val itemView = viewHolder.itemView
                val iconMarginVertical =
                    (viewHolder.itemView.height - deleteIcon.intrinsicHeight) / 2

                if (dX > 0) {
                    colorDrawableBackground.setBounds(
                        itemView.left,
                        itemView.top,
                        dX.toInt(),
                        itemView.bottom
                    )
                    deleteIcon.setBounds(
                        itemView.left + iconMarginVertical,
                        itemView.top + iconMarginVertical,
                        itemView.left + iconMarginVertical + deleteIcon.intrinsicWidth,
                        itemView.bottom - iconMarginVertical
                    )
                } else {
                    colorDrawableBackground.setBounds(
                        itemView.right + dX.toInt(),
                        itemView.top,
                        itemView.right,
                        itemView.bottom
                    )
                    deleteIcon.setBounds(
                        itemView.right - iconMarginVertical - deleteIcon.intrinsicWidth,
                        itemView.top + iconMarginVertical,
                        itemView.right - iconMarginVertical,
                        itemView.bottom - iconMarginVertical
                    )
                    deleteIcon.level = 0
                }

                colorDrawableBackground.draw(c)

                c.save()

                if (dX > 0)
                    c.clipRect(itemView.left, itemView.top, dX.toInt(), itemView.bottom)
                else
                    c.clipRect(
                        itemView.right + dX.toInt(),
                        itemView.top,
                        itemView.right,
                        itemView.bottom
                    )

                deleteIcon.draw(c)

                c.restore()

                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
            }
        }


        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(lista)

        lista?.adapter = adaptador

        return view
    }


    fun buttons_functions(view: View) {
        val btDeleteAll =
            view.findViewById(R.id.btnDeleteAll) as Button
        btDeleteAll.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnDeleteAll -> {
                Toast.makeText(view?.context, "Historial borrado", Toast.LENGTH_LONG).show()
                crud = HistorialCrud(view!!.context)
                crud?.deleteHistorial()
                refresh()
            }

        }
    }


    fun refresh() {
        val fragmentTransaction = activity!!.getSupportFragmentManager().beginTransaction()
        fragmentTransaction.replace(R.id.content_frame, IMC_fragment())
        fragmentTransaction.commit()
    }


}