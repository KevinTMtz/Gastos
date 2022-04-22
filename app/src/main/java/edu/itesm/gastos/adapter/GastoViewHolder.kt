package edu.itesm.gastos.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import edu.itesm.gastos.databinding.GastoBinding
import edu.itesm.gastos.entities.Gasto

class GastoViewHolder (view: View): RecyclerView.ViewHolder(view) {
    private val binding = GastoBinding.bind(view)

    fun bind(gasto: Gasto){
        binding.concepto.text = gasto.description
        binding.monto.text = "${ gasto.amount }"
    }
}