package com.example.test.data.db.dao

import androidx.room.*
import com.example.test.data.db.entities.RatesEntity
import kotlinx.coroutines.flow.Flow

/**
 * Describes interaction with the [RatesEntity] table of the database
 *
 * @author Evgen K.
 */
@Dao
interface RatesDao {

    /**
     * Add [RatesEntity] object to database
     *
     * @param rates current rates item that will be added to database
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRates(rates: RatesEntity)

    /**
     * Remove [RatesEntity] object from database
     *
     * @param rates current rates item that will be remove from database
     */
    @Delete
    suspend fun deleteRates(rates: RatesEntity)

    /**
     * Receive [RatesEntity] objects from database
     *
     * @return nullable Flow<[RatesEntity]>
     */
    @Query("SELECT * FROM ratesEntity")
    fun getRates(): Flow<RatesEntity?>

}