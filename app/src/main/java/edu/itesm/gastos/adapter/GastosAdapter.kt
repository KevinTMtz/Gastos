package edu.itesm.gastos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.itesm.gastos.R
import edu.itesm.gastos.entities.Gasto

class GastosAdapter(private var gastos: List<Gasto>) :
    RecyclerView.Adapter<GastoViewHolder>() {

    fun setGastos(gastos: List<Gasto>){
        this.gastos = gastos
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GastoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return GastoViewHolder(layoutInflater.inflate(R.layout.gasto,
            parent, false))
    }

    override fun getItemCount(): Int = gastos.size


    override fun onBindViewHolder(holder: GastoViewHolder, position: Int) {
        val item = gastos[position]
        holder.bind(item)
    }
}