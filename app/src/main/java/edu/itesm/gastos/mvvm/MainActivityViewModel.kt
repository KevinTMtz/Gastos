package edu.itesm.gastos.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.itesm.gastos.dao.GastoDao
import edu.itesm.gastos.entities.Gasto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivityViewModel : ViewModel(){
    var liveData: MutableLiveData<List<Gasto>>
    var sum: MutableLiveData<Double>

    init {
        liveData = MutableLiveData()
        sum = MutableLiveData()
    }

    fun getLiveDataObserver(): MutableLiveData<List<Gasto>>{
        return liveData
    }

    fun getSumObserver(): MutableLiveData<Double> {
        return sum
    }

    fun getGastos(gastoDao: GastoDao){
        CoroutineScope(Dispatchers.IO).launch {
            /*
            for (i in 0..99) {
                gastoDao.insertGasto(Gasto(0, "Gasto ${i}", Random.nextDouble() * 100))
            }
            */

            liveData.postValue(gastoDao.getAllGastos())
            sum.postValue(gastoDao.getGastosSum())
        }
    }

    fun addGasto(gastoDao: GastoDao, desciption: String, amount: Double) {
        CoroutineScope(Dispatchers.IO).launch {
            gastoDao.insertGasto(Gasto(0, desciption, amount))

            liveData.postValue(gastoDao.getAllGastos())

            sum.postValue(gastoDao.getGastosSum())
        }
    }
}