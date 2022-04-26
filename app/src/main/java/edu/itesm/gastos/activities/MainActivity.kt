package edu.itesm.gastos.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import edu.itesm.gastos.R
import edu.itesm.gastos.adapter.GastosAdapter
import edu.itesm.gastos.dao.GastoDao
import edu.itesm.gastos.database.GastosDB
import edu.itesm.gastos.databinding.ActivityMainBinding
import edu.itesm.gastos.entities.Gasto
import edu.itesm.gastos.mvvm.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var gastoDao: GastoDao
    private lateinit var  gastos: List<Gasto>
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: GastosAdapter
    private lateinit var viewModel : MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = Room.databaseBuilder(this@MainActivity, GastosDB::class.java, "gastos").build()
        gastoDao = db.gastoDao()

        initRecycler()
        initViewModel()

        binding.addButton.setOnClickListener {
            addGasto()
        }
    }

    private fun initRecycler(){
        gastos = mutableListOf<Gasto>()
        adapter = GastosAdapter(gastos)
        binding.gastos.layoutManager = LinearLayoutManager(this)
        binding.gastos.adapter = adapter
    }

    private fun initViewModel(){
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        viewModel.getLiveDataObserver().observe(this, Observer {
            if(it.isNotEmpty()){
                adapter.setGastos(it)
                adapter.notifyDataSetChanged()
            }
        })

        viewModel.getSumObserver().observe(this) {
            if (it != null) {
                Toast.makeText(this, "Total Sum: ${it.toString()}", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.getGastos(gastoDao)

    }

    private fun addGasto() {
        val description = binding.textDescription.text.toString()
        val amount = binding.textAmount.text.toString()

        if (description == "" || amount == "") {
            Toast.makeText(this, "Input all info", Toast.LENGTH_SHORT).show()
            return
        }

        viewModel.addGasto(
            gastoDao,
            description,
            amount.toDouble()
        )

        binding.textDescription.text.clear()
        binding.textAmount.text.clear()

        Toast.makeText(this, "Added expense", Toast.LENGTH_SHORT).show()
    }
}