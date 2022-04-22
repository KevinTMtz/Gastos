package edu.itesm.gastos.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import edu.itesm.gastos.entities.Gasto

@Dao
interface GastoDao {
    @Query("SELECT * FROM Gasto")
    suspend fun getAllGastos(): List<Gasto>

    @Query("SELECT SUM(amount) FROM Gasto")
    suspend fun getGastosSum(): Double

    @Insert
    suspend fun insertGasto(gasto: Gasto)
}