package com.dosan.examen.Crud

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dosan.examen.R
import com.google.android.material.snackbar.Snackbar

class HistorialAdapter(items: ArrayList<Historial>) :
    RecyclerView.Adapter<HistorialAdapter.ViewHolder>() {


    var items: ArrayList<Historial>? = null
    var viewHolder: ViewHolder? = null

    init {
        this.items = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista =
            LayoutInflater.from(parent.context).inflate(R.layout.items_historial, parent, false)
        viewHolder = ViewHolder(vista)
        return viewHolder!!
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = items?.get(position)

        holder.peso?.text = item?.peso +" Kg"
        holder.altura?.text = item?.altura+ " Cm"
        holder.tipo?.text = item?.tipo
        holder.resultado?.text = item?.resultado

    }

    override fun getItemCount(): Int {
        return items?.count()!!
    }

    fun removeItem(viewHolder: RecyclerView.ViewHolder) {
        items?.removeAt(viewHolder.adapterPosition)
        //notifyItemRemoved(viewHolder.adapterPosition)

        Snackbar.make(viewHolder.itemView, "Elemento eliminado", Snackbar.LENGTH_LONG).show()
        notifyItemRemoved(viewHolder.adapterPosition)
    }

    class ViewHolder(
        vista: View

    ) : RecyclerView.ViewHolder(vista),
        View.OnClickListener, View.OnLongClickListener {
        var vista = vista
        var peso: TextView? = null
        //var id: TextView? = null
        var altura: TextView? = null
        var resultado: TextView? = null
        var tipo: TextView? = null
        /*var listener: ClickListener? = null
        var longListener: LongClickListener? = null*/

        init {

            peso = vista.findViewById(R.id.tvPeso)
            altura = vista.findViewById(R.id.tvAltura)
            resultado = vista.findViewById(R.id.tvResultado)
            tipo = vista.findViewById(R.id.tvTipo)

            // agregando la variable para poder hacerle click
            //this.listener = listener
            //this.longListener = longClickListener
            // asignar el evento click normal
            //vista.setOnClickListener(this)
            //vista.setOnLongClickListener(this)
        }

        override fun onClick(v: View?) {

        }

        override fun onLongClick(v: View?): Boolean {
            return true
        }
    }


}